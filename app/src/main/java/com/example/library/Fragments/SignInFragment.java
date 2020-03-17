package com.example.library.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.library.FileViewModel;
import com.example.library.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;


public class SignInFragment extends Fragment {
    EditText emailEt, passwordEt;
    Button signIn;
    FileViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        emailEt = view.findViewById(R.id.loginEmailET);
        passwordEt = view.findViewById(R.id.loginPasswordET);
        signIn = view.findViewById(R.id.signInbut);


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if ((emailEt.getText() != null) && (passwordEt.getText() != null)) {
                    String logEmail = emailEt.getText().toString().trim();
                    String logPass = passwordEt.getText().toString().trim();
                    viewModel.loginUser(logEmail, logPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_levelFragment);
                        }
                    });

                } else {


                }
            }
        });


        return view;
    }
}
