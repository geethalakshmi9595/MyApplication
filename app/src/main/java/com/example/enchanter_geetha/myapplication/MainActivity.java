package com.example.enchanter_geetha.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText name,mail,phone,pass;
    String s1,s2,s3,s4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name=(EditText)findViewById(R.id.name);
        mail=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.number);
        pass=(EditText)findViewById(R.id.password);

        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                s1=name.getText().toString();
                s2=mail.getText().toString();
                s3=phone.getText().toString();
                s4=pass.getText().toString();

                if(s1.equals("")&& s2.equals("")&&s3.equals("")&& s4.equals("")) {
                    Toast.makeText(MainActivity.this, "please enter all fields", Toast.LENGTH_LONG).show();
                }
                else {
                    insertme(s1, s2, s3, s4);
                    Intent i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);
                }
            }
        });


    }
    public void insertme(final String s1,final String s2,final String s3,final String s4)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://geetha1995.tk/ins.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params =new HashMap<String, String>();
                params.put("na",s1);
                params.put("xx",s2);
                params.put("dd",s3);
                params.put("pas",s4);

                return params;

            }
        };
        Second.getInstance().addToRequestQueue(stringRequest);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}