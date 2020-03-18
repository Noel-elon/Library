package com.example.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       // UploadFileFragment uploadFileFragment = new UploadFileFragment();
        //getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, uploadFileFragment).commit();


      //  LevelFragment levelFragment = new LevelFragment();
        //getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,levelFragment).commit();

    }

}
