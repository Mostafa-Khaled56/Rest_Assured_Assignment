package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Class to handle the received response
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    private String name;
    private String job;
    private String id;
    private String createdAt;

    public Response()
    {
        // Required for JSON deserialization by Jackson
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getJob()
    {
        return job;
    }

    public void setJob(String job)
    {
        this.job = job;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
