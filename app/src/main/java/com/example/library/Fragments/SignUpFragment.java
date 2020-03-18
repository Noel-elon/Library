package com.example.library.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.FileViewModel;
import com.example.library.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignUpFragment extends Fragment {
    EditText name, password, email;
    Button signUp;
    FileViewModel viewModel;
    TextView signIn;
    FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        name = view.findViewById(R.id.nameET);
        password = view.findViewById(R.id.passwordET);
        email = view.findViewById(R.id.emailET);
        signUp = view.findViewById(R.id.signUpbut);
        signIn = view.findViewById(R.id.signintext);
        viewModel = new FileViewModel();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_signInFragment);
            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (email.getText() != null && (name.getText() != null) && (password.getText() != null)) {
                    String emailString = email.getText().toString();
                    String pword = password.getText().toString();
                    Log.d("Email & Pass:  ", emailString + pword);
                    viewModel.registerUser(emailString, pword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_levelFragment);
                            } else {
                                Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {


                }
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (auth.getCurrentUser() != null) {
            Navigation.findNavController(getView()).navigate(R.id.action_signUpFragment_to_levelFragment);
        }
    }
}
