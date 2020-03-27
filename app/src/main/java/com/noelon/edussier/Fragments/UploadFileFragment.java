package com.noelon.edussier.Fragments;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.OpenableColumns;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.noelon.edussier.FileViewModel;
import com.noelon.edussier.Models.Course;
import com.noelon.edussier.Models.File;
import com.noelon.edussier.Models.Level;
import com.noelon.edussier.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;


public class UploadFileFragment extends Fragment {
    Spinner levelspinner;
    EditText courseName;
    TextView fileName;
    Button upload;
           FloatingActionButton uploadFile;
    int FILE_SELECT_CODE = 0;
    Uri uri;
    FileViewModel fileViewModel;
    public static Course subject;
    public static Level level;
    public static File file;
    String name;
    String selectedLevel;
    public static List<String> spinnerArray;
    ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootview = inflater.inflate(R.layout.fragment_upload_file, container, false);

        levelspinner = rootview.findViewById(R.id.spinnerLevel);
        courseName = rootview.findViewById(R.id.courseET);
        fileName = rootview.findViewById(R.id.filenameTV);
        upload = rootview.findViewById(R.id.uploadBut);
        uploadFile = rootview.findViewById(R.id.uploadFileBut);
        file = new File();
        subject = new Course();
        level = new Level();
        fileViewModel = new FileViewModel();
        progressBar = rootview.findViewById(R.id.uploadProgBar);
        progressBar.setVisibility(View.INVISIBLE);

        spinnerArray = new ArrayList<String>();
        spinnerArray.add("Year one");
        spinnerArray.add("Year two");
        spinnerArray.add("Year three");
        spinnerArray.add("Year four");
        spinnerArray.add("Year five");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        levelspinner.setAdapter(adapter);
        levelspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedLevel = levelspinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;

            }
        });


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFile();
            }
        });

        uploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
//                Log.d("THE URI", uri.toString());
             //   Log.d("THE Name", name);
                String coursenames = courseName.getText().toString();

                if (!(name == null || TextUtils.isEmpty(coursenames)) ) {


                    file.setFileName(name);


                    subject.setCourseName(courseName.getText().toString());


                    level.setLevel(selectedLevel);

                    fileViewModel.uploadFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()) {
                                progressBar.setVisibility(View.GONE);
                                Toasty.success(getContext(), "File Uploaded Successfully", Toast.LENGTH_SHORT, true).show();
                                courseName.setText("");
                                courseName.clearFocus();
                                fileName.setText("");
                                fileViewModel.getDownloadURL().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        String url = task.getResult().toString();
                                        Log.d("THE fetched url", url);
                                        file.setFileUrl(url);
                                        subject.setFile(file);
                                        level.setSubject(subject);


                                        fileViewModel.uploadLevel(level);
                                    }
                                });

                            } else {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toasty.error(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT, true).show();
                            }
                        }
                    });


                }else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toasty.warning(getContext(), "Course name/File name is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return rootview;


    }


    private void selectFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(getActivity(), "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {

                    // Get the Uri of the selected file
                    uri = data.getData();
                    name = getFilename(uri);
                    fileName.setText(name);
                    Log.d("THE URI", uri.toString());


                }
                break;
        }
    }


    public String getFilename(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;

    }

    public static List<String> getArray() {
        return spinnerArray;
    }
}
