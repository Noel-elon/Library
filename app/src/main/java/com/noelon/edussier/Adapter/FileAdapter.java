package com.noelon.edussier.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.noelon.edussier.FileViewModel;
import com.noelon.edussier.Models.File;
import com.noelon.edussier.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileHolder> {
    List<String> files;
    LayoutInflater layoutInflater;
    public static String fileName;
    FileViewModel fileViewModel = new FileViewModel();

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
        fileName = files.get(position);
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
        public void onClick(final View view) {
            String levelName = CourseAdapter.bundle.getString("Level");
            String courseName = CourseAdapter.bundle.getString("CourseName");

            fileViewModel.getFileObject(levelName, courseName, fileName).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                File file = new File();

                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                    file = task.getResult().toObject(File.class);
                    String fileUrl = file.getFileUrl();

                    Bundle bundle = new Bundle();
                    bundle.putString("URL", fileUrl);

                    Intent intent = new Intent();
                    intent.setType(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(fileUrl));

                    view.getContext().startActivity(intent);

                    //Navigation.findNavController(view).navigate(R.id.action_fileFragment_to_openFileFragment, bundle);
                }
            });

        }
    }
}
