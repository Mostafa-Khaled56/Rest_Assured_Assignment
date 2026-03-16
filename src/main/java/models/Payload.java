package models;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;

// Class to represent the JSON request body for the User API
public class Payload
{
    //Encapsulates the fields that sending to the server
    @JsonProperty("name")
    private String name;
    @JsonProperty("job")
    private String job;


    public Payload() {}

    public Payload(String name, String job)
    {
        this.name = name;
        this.job  = job;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }
}
