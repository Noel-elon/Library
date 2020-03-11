package com.example.library;

import android.os.Bundle;

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

import java.util.ArrayList;
import java.util.List;


public class CourseFragment extends Fragment {
    FileViewModel fileViewModel;
    CourseAdapter adapter;
    MutableLiveData<List<String>> courses;
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



        fileViewModel.getCourses(level).observe(getActivity(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                recyclerView = getView().findViewById(R.id.courseRecycler);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                adapter = new CourseAdapter(strings);
                recyclerView.setAdapter(adapter);


            }
        });

        //adapter.notifyDataSetChanged();


        return view;
    }
}
