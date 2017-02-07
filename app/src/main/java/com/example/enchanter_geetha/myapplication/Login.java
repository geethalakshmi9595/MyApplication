package com.example.enchanter_geetha.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    TextView textView;
    EditText email,pass;
    Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView=(TextView)findViewById(R.id.t2);
        Intent intent = getIntent();
        String s1= getIntent().getStringExtra("getData");
        textView.setText(s1);
        email=(EditText)findViewById(R.id.email
        );
        pass=(EditText)findViewById(R.id.password);
        b1=(Button)findViewById(R.id.but);
        b3=(Button)findViewById(R.id.bu3);
        b2=(Button)findViewById(R.id.bu2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(Login.this,MainActivity.class);
                startActivity(intent1);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t1=email.getText().toString();
                String t2=pass.getText().toString();
                if(t1.equals("")&& t2.equals("")) {
                    Toast.makeText(Login.this, "please enter all fields", Toast.LENGTH_LONG).show();
                }
                else
                {
                    getallme(t1,t2);
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
             Intent n=new Intent(Login.this,Adminlogin.class) ;
                startActivity(n);
            }
        });

    }
    public void getallme(final String t1, final String t2)
    {
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, MyUrl_controller.loginform ,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject responseObj = new JSONObject(response);
                    boolean success = responseObj.getBoolean("success");
//Toast.makeText(MainActivity.this,"jikx",Toast.LENGTH_LONG).show();
                    if (success)
                    {
                        String name = responseObj.getString("name");
                        String number = responseObj.getString("phoneno");
                        String email = responseObj.getString("email");
                        //String type = responseObj.getString("type");
                        Intent intent=new Intent(Login.this,Adminlogin.class);
                        intent.putExtra("name",name);
                        intent.putExtra("num",number);
                        intent.putExtra("email",email);
                        //intent.putExtra("type",type);
                        startActivity(intent);

                    }
                    else {
                        Toast.makeText(Login.this,
                                "something went wrong please try again!!!",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("na1",t1);
                params.put("na2",t2);
                return params;
            }
        };
        Second.getInstance().addToRequestQueue(stringRequest);
    }

}

