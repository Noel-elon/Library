package com.noelon.edussier.Models;

public class File {
    private String fileUrl;
    private String fileName;

    public File( String fileName, String fileUrl) {
        this.fileUrl = fileUrl;
        this.fileName = fileName;
    }

    public File(){}

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
