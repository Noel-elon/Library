package com.noelon.edussier.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.noelon.edussier.Adapter.CourseAdapter;
import com.noelon.edussier.FileViewModel;
import com.noelon.edussier.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class CourseFragment extends Fragment {
    FileViewModel fileViewModel;
    CourseAdapter adapter;
    List<String> courses;
    RecyclerView recyclerView;
    public String level;
    public static Bundle bundle;
    ProgressBar progressBar;


    public CourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fileViewModel = ViewModelProviders.of(getActivity()).get(FileViewModel.class);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course, container, false);
        progressBar = view.findViewById(R.id.courseProgBar);
        progressBar.setVisibility(view.VISIBLE);
        level = getArguments().getString("level");

        bundle = new Bundle();
        bundle.putString("Level", level);


        recyclerView = view.findViewById(R.id.courseRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        fileViewModel.getCourses(level).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                courses = new ArrayList<>();
                for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                    String courseName = documentSnapshot.getId();
                    courses.add(courseName);
                }
                adapter = new CourseAdapter(courses);
                progressBar.setVisibility(View.GONE);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Log.d("CourseinFrag", String.valueOf(courses.size()));
            }
        });


        return view;
    }
}
