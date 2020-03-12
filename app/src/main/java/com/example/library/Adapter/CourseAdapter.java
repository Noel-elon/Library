package com.example.library.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.Models.Course;
import com.example.library.R;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseHolder> {
    List<String> courses;
    LayoutInflater layoutInflater;


    public CourseAdapter(List<String> courses) {
        this.courses = courses;

    }

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.course_item, parent, false);
        return new CourseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHolder holder, int position) {
        String courseName = courses.get(position);
        holder.courseNameTV.setText(courseName);

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class CourseHolder extends RecyclerView.ViewHolder {
        TextView courseNameTV;

        public CourseHolder(@NonNull View itemView) {
            super(itemView);

            courseNameTV = itemView.findViewById(R.id.courseTV);
        }
    }
}
