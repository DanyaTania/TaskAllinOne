package com.example.dto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TextReader {
    private static String FileNomenclatureName;
    private static File Directory;
    private static File Path;


    public TextReader() {

    }

    public TextReader(String path, String file) {
        this.setDirectory(path);
        this.setFileNomenclatureName(file);
        this.setPath();

    }

    public void setDirectory(String path) {

        this.Directory=new File(path);
    }

    public static File getDirectory() {
        return Directory;
    }
    public void setFileNomenclatureName(String fileNomenclatureName) {

        this.FileNomenclatureName=fileNomenclatureName;
    }
    public static String getFileNomenclatureName(){
        return FileNomenclatureName;
    }


    public void setPath(){
        this.Path=new File(this.getDirectory(), this.getFileNomenclatureName());
    }
    public static File getPath(){
        return Path;
    }


    public List<String> takeAll(){

        List<String> allLines=null;

        try {
            allLines = Files.readAllLines(Paths.get(String.valueOf(getPath())));

        } catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return allLines;
    }

}

