package com.example.library;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.library.Adapter.LevelAdapter;

import java.util.ArrayList;
import java.util.List;


public class LevelFragment extends Fragment {
   public static List<String> levels;
    LevelAdapter levelAdapter;


    public LevelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        levels = new ArrayList<>();

        levels.add("Year one");
        levels.add("Year two");
        levels.add("Year three");
        levels.add("Year four");
        levels.add("Year five");



        View view = inflater.inflate(R.layout.fragment_level, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.levelRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        levelAdapter = new LevelAdapter(levels);
        recyclerView.setAdapter(levelAdapter);

        return view;
    }
}
