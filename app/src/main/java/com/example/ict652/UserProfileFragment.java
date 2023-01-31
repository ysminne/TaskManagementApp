package com.example.ict652;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserProfileFragment extends Fragment {

    private Button pass_btn;



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

                        } else {
                            Toast.makeText(getActivity(), "Please enter password ...", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        dialog.show();

    }
}