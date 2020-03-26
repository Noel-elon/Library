package com.example.library.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.FileViewModel;
import com.example.library.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;


public class SignUpFragment extends Fragment {
    EditText name, password, email;
    Button signUp;
    FileViewModel viewModel;
    public String PREF_NAME = "NAME_PREF";
    public String PREF_TAG = "NAME";
    TextView signIn;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user;
    ProgressBar progressBar;
    SharedPreferences preferences;
    SharedPreferences.Editor prefEditor;


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
        progressBar = view.findViewById(R.id.signUpprog);
        progressBar.setVisibility(View.INVISIBLE);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_signInFragment);
            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String emailString = email.getText().toString();
                final String nameString = name.getText().toString();
                String pwordString = password.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                if (!(TextUtils.isEmpty(emailString) || TextUtils.isEmpty(nameString) || TextUtils.isEmpty(pwordString))) {
                    if (Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()) {

                        Log.d("Email & Pass:  ", emailString + pwordString);
                        viewModel.registerUser(emailString, pwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.INVISIBLE);
                                if (task.isSuccessful()) {
                                    Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_levelFragment);
                                } else {
                                    Toasty.error(getContext(), task.getException().getMessage(), Toasty.LENGTH_SHORT).show();
                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toasty.warning(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toasty.warning(getContext(), "Input a valid email address", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toasty.warning(getContext(), "Fill all the boxes please!", Toast.LENGTH_SHORT).show();

                }

                preferences = getActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
                prefEditor = preferences.edit();
                prefEditor.putString(PREF_TAG, nameString);
                prefEditor.apply();

            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!(auth.getCurrentUser() == null)) {
            Log.d("UID  ", auth.getCurrentUser().getUid());
            Navigation.findNavController(getView()).navigate(R.id.action_signUpFragment_to_levelFragment);
        } else {

        }

    }

}
