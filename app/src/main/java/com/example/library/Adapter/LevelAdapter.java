package com.example.library.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.FileViewModel;
import com.example.library.Models.Course;
import com.example.library.Models.Level;
import com.example.library.R;

import java.util.ArrayList;
import java.util.List;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.ViewHolder> {
    public List<String> levels;
   public static Level level;
    public static List<String> courses = new ArrayList<>();
    public FileViewModel fileViewModel = new FileViewModel();


    public LevelAdapter(List<String> levels) {
        this.levels = levels;
    }

    private LayoutInflater layoutInflater;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.level_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String level = levels.get(position);
        holder.levelnameTv.setText(level);
    }

    @Override
    public int getItemCount() {
        return levels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView levelnameTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            levelnameTv = itemView.findViewById(R.id.levelnameTV);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            courses = fileViewModel.getCourses(levels.get(position));
            String levelname = levels.get(position);
            Bundle bundle = new Bundle();
            bundle.putString("level", levelname);
            Navigation.findNavController(view).navigate(R.id.action_levelFragment_to_courseFragment, bundle);

            int coursesize = courses.size();

            Toast.makeText(view.getContext(), levels.get(position) + String.valueOf(coursesize), Toast.LENGTH_SHORT).show();

        }
    }
}

