package com.example.library.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.library.Adapter.FileAdapter;
import com.example.library.FileViewModel;
import com.example.library.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class FileFragment extends Fragment {
    List<String> files;
    RecyclerView recyclerView;
    FileAdapter adapter;
    ProgressBar progressBar;

    public FileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_file, container, false);
        recyclerView = view.findViewById(R.id.fileRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        String levelName = getArguments().getString("Level");
        String courseName = getArguments().getString("CourseName");
        progressBar = view.findViewById(R.id.fileProgBar);
        progressBar.setVisibility(View.VISIBLE);

        Log.d("LevelINFile frag", levelName + courseName);

        final FileViewModel fileViewModel = new FileViewModel();

        fileViewModel.getFiles(levelName, courseName).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                files = new ArrayList<>();
           for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
               String fileName = documentSnapshot.getId();
               files.add(fileName);
           }
           adapter = new FileAdapter(files);
           progressBar.setVisibility(View.GONE);
           recyclerView.setAdapter(adapter);
           adapter.notifyDataSetChanged();

                Log.d("FileCount", String.valueOf(files.size()));


            }
        });


        return view;
    }
}
