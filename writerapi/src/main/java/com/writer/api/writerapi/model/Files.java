package com.writer.api.writerapi.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Files {
    private static final Logger LOG = Logger.getLogger(Files.class.getName());

    // Package private for tests
    static final String STRING_FORMAT = "File [id=%d, name=%s, numFrames=%d]";

    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("numFrames") private int numFrames;
    @JsonProperty("frames") private String[] frames;
    @JsonProperty("approved") boolean approved;

    public Files(@JsonProperty("id") int id, @JsonProperty("name") String name,
                @JsonProperty("numFrames") int numFrames, @JsonProperty("frames") String[] frames) {
        this.id = id;
        this.name = name;
        this.numFrames = numFrames;
        for (int i = 0; i < numFrames; i++){
            this.frames[i] = frames[i];
        }
        this.approved = false;
    }


    public int getId(){return this.id;}

    public void setName(String name) {this.name = name;}

    public String getName() {return this.name;}

    public String[] getFrames(){return this.frames;}

    public int getNumFrames(){return this.numFrames;}

    

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT,id,name,numFrames);
    }
}