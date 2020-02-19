package com.example.library.DAOs;

import com.example.library.Models.File;

import java.util.List;

public interface Filedao {

    public void setFile(File file);

    public List<File> getFiles();

    public String getFileUrl();


}
