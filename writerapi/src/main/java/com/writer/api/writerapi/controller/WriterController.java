package com.writer.api.writerapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.writer.api.writerapi.model.Files;
import com.writer.api.writerapi.persistence.FileDAO;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



@RestController
@RequestMapping("")
public class WriterController{
    private static final Logger LOG = Logger.getLogger(WriterController.class.getName());
    private FileDAO fileDao;


    public WriterController(FileDAO fileDao) {
        this.fileDao = fileDao;
    }

	@GetMapping("/")
	public ResponseEntity<Boolean> start(){
		return new ResponseEntity<>(HttpStatus.OK);
	}

    @GetMapping("/files/{id}")
    public ResponseEntity<Files> getFile(@PathVariable int id) {
        LOG.info("GET /files/" + id);
        try {
            Files file = fileDao.getFile(id);
            if (file != null)
                return new ResponseEntity<Files>(file,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/files")
    public ResponseEntity<Files[]> getFiles() {
        LOG.info("GET /files");

        try{
	    Files[] files = fileDao.getFiles();
	    if (files != null){
		return new ResponseEntity<Files[]>(files,HttpStatus.OK);
	  }
	    else{
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	catch(IOException e){
       	    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @GetMapping("/files/{name}")
    public ResponseEntity<Files[]> searchFiles(@RequestParam String name) {
        LOG.info("GET /files/?name="+name);
        try{
	    Files[] files = fileDao.findFiles(name);
	    if (files != null){
		return new ResponseEntity<Files[]>(files,HttpStatus.OK);
	    }
	    else{
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	catch (IOException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @PostMapping("/files")
    public ResponseEntity<Files> createFile(@RequestBody Files file) {
        LOG.info("POST /files " + file);
	try{
	    Files file_tmp = fileDao.createFile(file);
            if(file_tmp != null){
	        return new ResponseEntity<Files>(file_tmp,HttpStatus.OK);
	    }
	    else{
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
    	catch(IOException e){
	    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @PutMapping("/files/")
    public ResponseEntity<Files> updateFile(@RequestBody Files file) {
        LOG.info("PUT /files " + file);
        try{
	    Files file_tmp = fileDao.updateFile(file);
	    if(file_tmp != null){
	        return new ResponseEntity<Files>(file_tmp,HttpStatus.OK);
	    }
	    else{
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	catch(IOException e){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @DeleteMapping("/{files/id}")
    public ResponseEntity<Files> deleteFile(@PathVariable int id) {
        LOG.info("DELETE /files/" + id);

        try{
	    boolean deleted = fileDao.deleteFile(id);
	    if(deleted){
	        return new ResponseEntity<>(HttpStatus.OK);
	    }
	    else{
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	catch(IOException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
}
