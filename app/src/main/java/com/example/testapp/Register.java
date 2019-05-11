package com.example.testapp;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class Register extends AppCompatActivity {

    EditText et_name,et_email,et_username,et_pass;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_name=(EditText) findViewById(R.id.et_register_name);
        et_email=(EditText)findViewById(R.id.et_register_email);
        et_username=(EditText)findViewById(R.id.et_register_username);
        et_pass=(EditText) findViewById(R.id.et_register_password);
        btn_register=(Button) findViewById(R.id.btn_register_register);

        final String SERVER_URL="http://172.31.247.30/movieApp//Register.php";
        final RequestQueue requestQueue= Volley.newRequestQueue(Register.this);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog=new ProgressDialog(Register.this);
                progressDialog.setMessage("Please Wait...");
                progressDialog.show();
                String name=et_name.getText().toString();
                String pass=et_pass.getText().toString();
                String email=et_email.getText().toString();
                String username=et_username.getText().toString();

                StringRequest stringRequest=new StringRequest(Request.Method.POST, SERVER_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                           // Toast.makeText(Register.this, response, Toast.LENGTH_SHORT).show();
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getBoolean("register")) {
                                Toast.makeText(Register.this, "Registration Successfully !!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Register.this, Login.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException ex){
                            ex.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(Register.this, "Error "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<>();
                        params.put("username",et_username.getText().toString());
                        params.put("password",et_pass.getText().toString());
                        params.put("name",et_name.getText().toString());
                        params.put("email",et_email.getText().toString());
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
                requestQueue.start();
            }
        });
    }
}
