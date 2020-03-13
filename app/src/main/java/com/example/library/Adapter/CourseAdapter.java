package com.example.library.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.Fragments.CourseFragment;
import com.example.library.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseHolder> {
    List<String> courses;
    LayoutInflater layoutInflater;
    public static Bundle bundle;


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

    public class CourseHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView courseNameTV;

        public CourseHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            courseNameTV = itemView.findViewById(R.id.courseTV);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            String courseName = courses.get(position);
            bundle = CourseFragment.bundle;
            bundle.putString("CourseName", courseName);
            Navigation.findNavController(view).navigate(R.id.action_courseFragment_to_fileFragment, bundle);

        }
    }
}
