package com.example.ict652;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ict652.UtilsService.UtilsService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.ErrorListener;

public class RegisterActivity extends AppCompatActivity {

    Button openHome;

    private String username, email, password;

    UtilsService utilService;

    private EditText etName, etEmail, etPasssword;
    private Button loginbtn, regisbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //openHome = (Button) findViewById(R.id.button);

        loginbtn = findViewById(R.id.loginbtn);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPasssword = findViewById(R.id.etPassword);
        regisbtn = findViewById(R.id.regisbtn);
        utilService = new UtilsService();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        regisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utilService.hideKeyboard(view, RegisterActivity.this);
                username = etName.getText().toString();
                email = etEmail.getText().toString();
                password = etPasssword.getText().toString();

                if(validate(view)) {
                    registerUser(view);
                }
            }
        });
    }

    private void registerUser(View view) {


        final HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("email", email);
        params.put("password", password);

        String apiKey = "https://taskmanagementapi-001.herokuapp.com/api/todo/auth/register";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                apiKey, new JSONObject(params), new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getBoolean("success")) {
                        String token = response.getString("token");
                        Toast.makeText(RegisterActivity.this, token, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, FrontpageActivity.class));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                NetworkResponse response = error.networkResponse;
                if(error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data, HttpHeaderParser.parseCharset(response.headers,  "utf-8"));

                        JSONObject obj = new JSONObject(res);
                        Toast.makeText(RegisterActivity.this, obj.getString("msg"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | UnsupportedEncodingException je) {
                        je.printStackTrace();

                    }
                }

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");

                return params;
            }
        };

        int socketTime = 3000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTime,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(policy);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }



    public boolean validate(View view) {
        boolean isValid;

        if(!TextUtils.isEmpty(username)) {
            if(!TextUtils.isEmpty(email)) {
                if(!TextUtils.isEmpty(password)) {
                    isValid = true;
                } else {
                    utilService.showSnackBar(view,"please enter password....");
                    isValid = false;
                }
            } else {
                utilService.showSnackBar(view,"please enter email....");
                isValid = false;
            }
        } else {
            utilService.showSnackBar(view,"please enter name....");
            isValid = false;
        }

        return  isValid;
    }

  //  public void openPro(View view){
     //   Intent intent = new Intent(this, HomeActivity2.class);
     //   startActivity(intent);
   // }

}