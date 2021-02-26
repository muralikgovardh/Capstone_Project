package com.tavant.fileupload.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@Data
public class Root
{
    private List<Results> results;

    private String version;

    private int responseCode;

    public void setResults(List<Results> results){
        this.results = results;
    }
    public List<Results> getResults(){
        return this.results;
    }
    public void setVersion(String version){
        this.version = version;
    }
    public String getVersion(){
        return this.version;
    }
    public void setResponseCode(int responseCode){
        this.responseCode = responseCode;
    }
    public int getResponseCode(){
        return this.responseCode;
    }
}
