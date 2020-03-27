package com.noelon.edussier.Adapter;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.noelon.edussier.FileViewModel;
import com.noelon.edussier.Models.File;
import com.noelon.edussier.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

import es.dmoral.toasty.Toasty;

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
                    String url = file.getFileUrl();

                    Bundle bundle = new Bundle();
                    bundle.putString("URL", url);

                    try {

                        Uri uri = Uri.parse(url);

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        if (url.toString().contains(".doc") || url.toString().contains(".docx")) {
                            // Word document
                            intent.setDataAndType(uri, "application/msword");
                        } else if (url.toString().contains(".pdf")) {
                            // PDF file
                            intent.setDataAndType(uri, "application/pdf");
                        } else if (url.toString().contains(".ppt") || url.toString().contains(".pptx")) {
                            // Powerpoint file
                            intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
                        } else if (url.toString().contains(".xls") || url.toString().contains(".xlsx")) {
                            // Excel file
                            intent.setDataAndType(uri, "application/vnd.ms-excel");
                        } else if (url.toString().contains(".zip")) {
                            // ZIP file
                            intent.setDataAndType(uri, "application/zip");
                        } else if (url.toString().contains(".rar")) {
                            // RAR file
                            intent.setDataAndType(uri, "application/x-rar-compressed");
                        } else if (url.toString().contains(".rtf")) {
                            // RTF file
                            intent.setDataAndType(uri, "application/rtf");
                        } else if (url.toString().contains(".wav") || url.toString().contains(".mp3")) {
                            // WAV audio file
                            intent.setDataAndType(uri, "audio/x-wav");
                        } else if (url.toString().contains(".gif")) {
                            // GIF file
                            intent.setDataAndType(uri, "image/gif");
                        } else if (url.toString().contains(".jpg") || url.toString().contains(".jpeg") || url.toString().contains(".png")) {
                            // JPG file
                            intent.setDataAndType(uri, "image/jpeg");
                        } else if (url.toString().contains(".txt")) {
                            // Text file
                            intent.setDataAndType(uri, "text/plain");
                        } else if (url.toString().contains(".3gp") || url.toString().contains(".mpg") ||
                                url.toString().contains(".mpeg") || url.toString().contains(".mpe") || url.toString().contains(".mp4") || url.toString().contains(".avi")) {
                            // Video files
                            intent.setDataAndType(uri, "video/*");
                        } else {
                            intent.setDataAndType(uri, "*/*");
                        }

                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        view.getContext().startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toasty.error(view.getContext(), "No application found which can open the file", Toast.LENGTH_SHORT).show();
                    }

                    //Navigation.findNavController(view).navigate(R.id.action_fileFragment_to_openFileFragment, bundle);
                }
            });

        }
    }
}
