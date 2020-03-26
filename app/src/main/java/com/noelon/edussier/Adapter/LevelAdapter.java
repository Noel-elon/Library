package com.noelon.edussier.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.noelon.edussier.FileViewModel;
import com.noelon.edussier.Models.Level;
import com.noelon.edussier.R;

import java.util.List;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.ViewHolder> {
    public List<String> levels;
   public static Level level;
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
            String levelname = levels.get(position);
            Bundle bundle = new Bundle();
            bundle.putString("level", levelname);
            Navigation.findNavController(view).navigate(R.id.action_levelFragment_to_courseFragment, bundle);



        }
    }
}

