package com.example.library.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.library.FileViewModel;
import com.example.library.R;


public class SignUpFragment extends Fragment {
    EditText name, password, email;
    Button signUp;
    FileViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        name = view.findViewById(R.id.nameET);
        password = view.findViewById(R.id.passwordET);
        email = view.findViewById(R.id.emailET);
        signUp = view.findViewById(R.id.signUpbut);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText() != null && (name.getText() != null) && (password.getText() != null)) {


                } else {


                }
            }
        });

        return view;
    }
}
