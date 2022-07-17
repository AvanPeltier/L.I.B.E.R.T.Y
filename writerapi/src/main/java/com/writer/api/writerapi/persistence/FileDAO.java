package com.writer.api.writerapi.persistence;

import java.io.IOException;

import com.writer.api.writerapi.model.Files;


public interface FileDAO {

    Files[] getFiles() throws IOException;

    Files[] findFiles(String containsText) throws IOException;

    Files getFile(int id) throws IOException;

    Files createFile(Files file) throws IOException;

    Files updateFile(Files file) throws IOException;

    boolean deleteFile(int id) throws IOException;
}
