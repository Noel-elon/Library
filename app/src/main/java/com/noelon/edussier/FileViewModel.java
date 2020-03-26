package com.noelon.edussier;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.noelon.edussier.Models.Level;
import com.noelon.edussier.Repository.Repository;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.UploadTask;

import java.util.List;
import java.util.Map;

public class FileViewModel extends ViewModel {
    private Repository repository = new Repository();
    LiveData<List<String>> courses;
    List<String> files;

    public FileViewModel() {
    }


    public Task<AuthResult> registerUser(String email, String password) {
        Task<AuthResult> result = repository.registerUser(email, password);
        return result;
    }

    public void saveUserName(FirebaseUser user, Map name){
        repository.saveUsername(user, name);
    }

    public Task<AuthResult> loginUser(String email, String password) {
        Task<AuthResult> result = repository.loginUser(email, password);
        return result;
    }


    public Task<UploadTask.TaskSnapshot> uploadFile(Uri fileUri) {
        Task<UploadTask.TaskSnapshot> upload = repository.uploadFile(fileUri);
        return upload;
    }

    public Task<Uri> getDownloadURL() {
        Task<Uri> url = repository.getDownloadUrl();
        return url;

    }

    public void uploadLevel(Level level) {
        repository.uploadLevel(level);
    }

    public Task<QuerySnapshot> getCourses(String level) {

        return Repository.getCourses(level);
    }

    public Task<QuerySnapshot> getFiles(String level, String course) {
        return Repository.getFiles(level, course);
    }

    public Task<DocumentSnapshot> getFileObject(String level, String course, String file) {

        return Repository.getFileObject(level, course, file);
    }


}
