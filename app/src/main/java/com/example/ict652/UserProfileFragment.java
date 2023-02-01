package com.example.ict652;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class UserProfileFragment extends Fragment {

    private Button pass_btn;
    private TextView email_txtview,password_txtview,username_txtview;
    private String token;
    private String user_json_str;
    JSONObject jsonObject;
    private String idVal;

    public UserProfileFragment() {
        // Required empty public constructor
    }


    public static UserProfileFragment newInstance(String param1, String param2) {
        UserProfileFragment fragment = new UserProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_user_profile, container, false);
        View view = inflater.inflate(R.layout.fragment_user_profile,container, false);
        pass_btn = view.findViewById(R.id.pass_btn);
        email_txtview = view.findViewById(R.id.email_profile_view);
        username_txtview = view.findViewById(R.id.username_profile_view);
        password_txtview = view.findViewById(R.id.password_profile_view);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        email_txtview.setText(sharedPreferences .getString("email", ""));
        username_txtview.setText(sharedPreferences .getString("username", ""));
        password_txtview.setText(sharedPreferences .getString("password", ""));
        token = sharedPreferences.getString("token","");
        user_json_str = sharedPreferences.getString("user","");
        try {
            jsonObject = new JSONObject(user_json_str);
            idVal = jsonObject.getString("_id");
            username_txtview.setText(jsonObject.getString("username"));
        }catch (JSONException err){
            Log.d("Error", err.toString());
        }

        pass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });

        return view;

    }

    public void showAlertDialog(){
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.custom_profile_layout, null);

        final EditText new_pass_field = alertLayout.findViewById(R.id.new_pass);

        final AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(alertLayout)
                .setTitle("Edit Password")
                .setPositiveButton("Done", null)
                .setNegativeButton("Cancel", null)
                .create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button button = ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String new_pass = new_pass_field.getText().toString();
                        if(!TextUtils.isEmpty(new_pass)){
                            updatePassword(idVal,new_pass);
                            Toast.makeText(getActivity(), "Changed!", Toast.LENGTH_SHORT).show();

                            dialog.dismiss();

                        } else {
                            Toast.makeText(getActivity(), "Please enter password ...", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

        dialog.show();

    }
    private void updatePassword(String id,String password) {
        String url = "https://taskmanagementapi-001.herokuapp.com/api/todo/auth/profile/"+id;

        HashMap<String, String> body = new HashMap<>();
        body.put("password", password);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT,
                url, new JSONObject(body), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getBoolean("success")) {
                        password_txtview.setText(password);
                        Toast.makeText(getActivity(), "Added Successfully", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getActivity(), obj.getString("msg"), Toast.LENGTH_SHORT).show();
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
                headers.put("Authorization", token);
                return headers;
            }
        };

        // set retry policy
        int socketTime = 3000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTime,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(policy);

        // request add
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);
    }
}