package com.example.library;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.library.Adapter.CourseAdapter;
import com.example.library.Adapter.LevelAdapter;
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


    public CourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fileViewModel = new FileViewModel();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course, container, false);

        String level = getArguments().getString("level");
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
                recyclerView.setAdapter(adapter);

                Log.d("CourseinFrag", String.valueOf(courses.size()));
            }
        });


        //adapter.notifyDataSetChanged();


        return view;
    }
}
