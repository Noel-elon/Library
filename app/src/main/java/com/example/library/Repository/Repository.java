package com.example.library.Repository;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;


import com.example.library.Models.Course;
import com.example.library.Models.File;
import com.example.library.Models.Level;
import com.example.library.Fragments.UploadFileFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class Repository {
    Level level = UploadFileFragment.level;
    Course course = UploadFileFragment.subject;
    File file = UploadFileFragment.file;


    private static FirebaseStorage storage = FirebaseStorage.getInstance();
    private static FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private static final String COLLECTION_NAME = "Uploads";
    private static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    public Repository() {
    }


    public static CollectionReference mainColRef() {

        return firestore.collection(COLLECTION_NAME);
    }

    public Task<AuthResult> registerUser(String email, String password) {
        Task<AuthResult> result = firebaseAuth.createUserWithEmailAndPassword(email, password);
        return result;
    }

    public Task<AuthResult> loginUser(String email, String password) {
        Task<AuthResult> result = firebaseAuth.signInWithEmailAndPassword(email, password);
        return result;
    }


    public Task<UploadTask.TaskSnapshot> uploadFile(Uri fileUri) {
        StorageReference storageReference = storage.getReference();

        Log.d("THE level", level.getLevelname());
        Log.d("THE course", course.getCourseName());
        Log.d("THE file", file.getFileName());
        Task<UploadTask.TaskSnapshot> uploadFile = storageReference.child(level.getLevelname())
                .child(course.getCourseName())
                .child(file.getFileName())
                .putFile(fileUri);
        return uploadFile;
    }

    public Task<Uri> getDownloadUrl() {
        StorageReference storageReference = storage.getReference();
        StorageReference downloadref = storageReference
                .child(level.getLevelname())
                .child(course.getCourseName())
                .child(file.getFileName());
        Task<Uri> downloadUrl = downloadref.getDownloadUrl();
        return downloadUrl;

    }

    public void uploadLevel(Level level) {

        Repository.mainColRef()
                .document(level.getLevelname())
                .collection(level.getLevelname())
                .document(course.getCourseName()).set(course).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

        Repository.mainColRef()
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

    public static Task<QuerySnapshot> getCourses(final String levelname) {
        return Repository.mainColRef().document(levelname).collection(levelname).get();


    }


    public static Task<QuerySnapshot> getFiles(String levelname, String courseName) {
        return Repository.mainColRef().document(levelname).collection(levelname).document(courseName).collection(courseName).get();
    }

    public static Task<DocumentSnapshot> getFileObject(String levelname, String courseName, String fileName) {
        return Repository.mainColRef().document(levelname).collection(levelname).document(courseName).collection(courseName).document(fileName).get();
    }


}
