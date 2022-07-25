package com.writer.api.writerapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.writer.api.writerapi.model.Files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component
public class FileFileDAO implements FileDAO {
    private static final Logger LOG = Logger.getLogger(FileFileDAO.class.getName());
    Map<Integer,Files> files;   

    private ObjectMapper objectMapper;  
                                        
    private static int nextId;  
    private String filename;    


    public FileFileDAO(@Value("${files.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

 
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }


    private Files[] getFilesArray() {
        return getFilesArray(null);
    }


    private Files[] getFilesArray(String containsText) { // if containsText == null, no filter
        ArrayList<Files> fileArrayList = new ArrayList<>();

        for (Files file : files.values()) {
            if (containsText == null || file.getName().contains(containsText)) {
                fileArrayList.add(file);
            }
        }

        Files[] fileArray = new Files[fileArrayList.size()];
        fileArrayList.toArray(fileArray);
        return fileArray;
    }


    private boolean save() throws IOException {
        Files[] fileArray = getFilesArray();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),fileArray);
        return true;
    }


    private boolean load() throws IOException {
        files = new TreeMap<>();
        nextId = 0;

        Files[] fileArray = objectMapper.readValue(new File(filename),Files[].class);

        for (Files file : fileArray) {
            files.put(file.getId(),file);
            if (file.getId() > nextId)
                nextId = file.getId();
        }
        // Make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Files[] getFiles() {
        synchronized(files) {
            return getFilesArray();
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Files[] findFiles(String user) {
        synchronized(files) {
            return getFilesArray(user);
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Files getFile(int id) {
        synchronized(files) {
            if (files.containsKey(id))
                return files.get(id);
            else
                return null;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Files createFile(Files file) throws IOException{
        synchronized(files) {

            Files newFile = new Files(nextId(),file.getName(), file.getNumFrames(), file.getFrames(), file.getUser());
            files.put(newFile.getId(),newFile);
            save(); // may throw an IOException
            return newFile;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Files updateFile(Files file) throws IOException {
        synchronized(files) {
            if (files.containsKey(file.getId()) == false)
                return null;

            files.put(file.getId(),file);
            save(); // may throw an IOException
            return file;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public boolean deleteFile(int id) throws IOException {
        synchronized(files) {
            if (files.containsKey(id)) {
                files.remove(id);
                return save();
            }
            else
                return false;
        }
    }
}
