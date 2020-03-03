package com.example.library.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;
import com.example.library.UploadFileFragment;

import java.util.ArrayList;
import java.util.List;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.ViewHolder> {
public List<String> levels;

    public LevelAdapter(List<String> levels) {
        this.levels = levels;
    }

    private LayoutInflater layoutInflater;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.level_item, parent, false );
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

            levelnameTv = itemView.findViewById(R.id.levelnameTV);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Toast.makeText(view.getContext(), levels.get(position), Toast.LENGTH_SHORT).show();
        }
    }
}

