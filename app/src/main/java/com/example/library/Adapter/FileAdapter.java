package com.example.library.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.Models.File;
import com.example.library.R;

import java.util.ArrayList;
import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileHolder> {
    List<String> files;
    LayoutInflater layoutInflater;

    public FileAdapter(List<String> files) {
        this.files = files;
    }

    @NonNull
    @Override
    public FileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.file_item, parent, false);


        return new FileHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FileHolder holder, int position) {
        String fileName = files.get(position);
        holder.fileNameTV.setText(fileName);

    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    public class FileHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fileNameTV;

        public FileHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            fileNameTV = itemView.findViewById(R.id.fileTV);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
