package com.example.library;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.library.Adapter.CourseAdapter;
import com.example.library.Adapter.LevelAdapter;

import java.util.ArrayList;
import java.util.List;


public class CourseFragment extends Fragment {
    FileViewModel fileViewModel;
    CourseAdapter adapter;
    List<String> courses;


    public CourseFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fileViewModel = new FileViewModel();
        courses = new ArrayList<>();
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_course, container, false);

        String level = getArguments().getString("level");

        courses = fileViewModel.getCourses(level);
        Log.d("The course=>>", courses.get(0));

        RecyclerView recyclerView = view.findViewById(R.id.levelRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CourseAdapter(courses);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
