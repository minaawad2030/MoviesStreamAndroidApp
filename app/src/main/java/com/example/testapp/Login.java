package com.example.testapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Login extends AppCompatActivity {

    EditText et_username,et_pass;
    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_username=(EditText) findViewById(R.id.et_username);
        et_pass=(EditText) findViewById(R.id.et_password);
        btn_login=(Button) findViewById(R.id.btn_login);
        final String SERVER_URL="http://172.31.247.30//movieApp//Login.php";
        final RequestQueue loginQueue= Volley.newRequestQueue(Login.this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog=new ProgressDialog(Login.this);
                progressDialog.setMessage("Please Wait...");
                progressDialog.show();
                final String username=et_username.getText().toString();
                final String pass=et_pass.getText().toString();
                StringRequest stringRequest=new StringRequest(Request.Method.POST, SERVER_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                           // Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                            JSONObject jsonObject=new JSONObject(response);
                            if(jsonObject.getBoolean("login"))
                            {
                                String name=jsonObject.getString("name");
                                SharedPreferences sharedPref = getSharedPreferences("pref",MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                //Toast.makeText(Login.this, name, Toast.LENGTH_LONG).show();
                                editor.putBoolean("Registered",true);
                                editor.putString("name", name);
                                editor.commit();
                                Intent intent=new Intent(Login.this,Home.class);
                                startActivity(intent);
                                finish();
                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(Login.this, " "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<>();
                        params.put("username",username);
                        params.put("password",pass);
                        return params;
                    }
                };
                loginQueue.add(stringRequest);
                loginQueue.start();
            }
        });
    }
}
