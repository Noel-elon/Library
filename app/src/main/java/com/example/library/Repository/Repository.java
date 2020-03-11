package com.example.library.Repository;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;


import com.example.library.FireStoreLiveData;
import com.example.library.Models.Course;
import com.example.library.Models.File;
import com.example.library.Models.Level;
import com.example.library.UploadFileFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


public class Repository {
    Level level = UploadFileFragment.level;
    Course course = UploadFileFragment.subject;
    File file = UploadFileFragment.file;


    List<String> files;
    MutableLiveData<List<String>> mutableLiveDataCourse = new MutableLiveData<>();
    List<String> courses = new ArrayList<>();

    FirebaseStorage storage = FirebaseStorage.getInstance();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();


    public Repository() {
    }


    public String uploadFile(Uri fileUri) {
        StorageReference storageReference = storage.getReference();

        Log.d("THE level", level.getLevelname());
        Log.d("THE course", course.getCourseName());
        Log.d("THE file", file.getFileName());
        storageReference.child(level.getLevelname()).child(course.getCourseName()).child(file.getFileName()).putFile(fileUri);
        StorageReference downloadref = storageReference.child(level.getLevelname()).child(course.getCourseName()).child(file.getFileName());
        String downloadUrl = downloadref.getDownloadUrl().toString();
        return downloadUrl;

    }

    public void uploadLevel(Level level) {


        firestore.collection("Uploads")
                .document(level.getLevelname())
                .collection(level.getLevelname())
                .document(course.getCourseName()).set(course).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });


        firestore.collection("Uploads")
                .document(level.getLevelname())
                .collection(level.getLevelname())
                .document(course.getCourseName())
                .collection(course.getCourseName())
                .document(file.getFileName()).set(file).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });


    }

    public LiveData<List<String>> getCourses(final String levelname) {
        CollectionReference collectionReference = firestore.collection("Uploads").document(levelname).collection(levelname);
        FireStoreLiveData fireStoreLiveData = new FireStoreLiveData(collectionReference, String.class);

        return fireStoreLiveData;


    }


    public List<String> getFiles(String levelname, String courseName) {
        firestore.collection("Uploads").document(levelname)
                .collection(levelname)
                .document(courseName)
                .collection(courseName)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot snapshot : task.getResult()) {
                    String filename = snapshot.toString();
                    files.add(filename);
                }

            }
        });

        return files;

    }

    public String getFileUrl(File file) {

        String url = file.getFileUrl();
        return url;
    }


}
