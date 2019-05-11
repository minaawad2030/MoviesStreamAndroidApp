 package com.example.testapp;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

 public class Home extends AppCompatActivity {

    private RecyclerView rc;
    private GridLayoutManager gridLayoutManager;
    private TextView tv_name;
    private RecyclerAdapter adapter;
    private List<MyData> data_List;
    private RequestQueue requestQueue;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rc=(RecyclerView) findViewById(R.id.rec_view);
        tv_name=(TextView)findViewById(R.id.tv_name);
        rc.setHasFixedSize(true);
        data_List=new ArrayList<>();

        SharedPreferences sharedPreferences=getSharedPreferences("pref",MODE_PRIVATE);
        name=sharedPreferences.getString("name","");
        tv_name.setText("Welcome, "+name);
        //Load JSON DATA From server
        loadDataFromServer(0);
        gridLayoutManager=new GridLayoutManager(this,2);
        rc.setLayoutManager(gridLayoutManager);

        rc.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(gridLayoutManager.findLastCompletelyVisibleItemPosition()==data_List.size()-1){
                    loadDataFromServer(data_List.get(data_List.size()-1).getId());
                }
            }
        });

    }

     private void loadDataFromServer(int id) {
         String SERVER_URL="http://172.31.247.30//movieApp//LoadMovies.php?id="+id;
         final ProgressDialog progressDialog=new ProgressDialog(Home.this);
         progressDialog.setMessage("Please Wait...");
         progressDialog.show();
         StringRequest stringRequest=new StringRequest(Request.Method.GET, SERVER_URL, new Response.Listener<String>() {
             @Override
             public void onResponse(String response) {
                 progressDialog.dismiss();
                 try {
                     JSONObject jsonObj= new JSONObject(response);
                     JSONArray jsonArray= jsonObj.getJSONArray("movies");
                     for(int i=0;i<jsonArray.length();i++){
                         JSONObject jsonObject= jsonArray.getJSONObject(i);
                         int id=jsonObject.getInt("id");
                         String title=jsonObject.getString("Name");
                         String description=jsonObject.getString("Description");
                         String image=jsonObject.getString("Image");
                         String video=jsonObject.getString("video");
                         MyData data=new MyData(image,description,id,title,video);
                         data_List.add(data);
                     }
                     adapter=new RecyclerAdapter(getApplicationContext(),data_List);
                     rc.setAdapter(adapter);
                 } catch (JSONException e) {
                     e.printStackTrace();
                 }

             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 progressDialog.dismiss();
                 Toast.makeText(Home.this, " "+error.toString(), Toast.LENGTH_SHORT).show();
             }
         });
         requestQueue=Volley.newRequestQueue(Home.this);
         requestQueue.add(stringRequest);
         requestQueue.start();
     }
 }
