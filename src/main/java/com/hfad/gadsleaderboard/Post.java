package com.hfad.gadsleaderboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("entry.1877115667")
    @Expose
    private String firstName;
    @SerializedName("entry.2006916086")
    @Expose
    private String lastName;
    @SerializedName("entry.1824927963")
    @Expose
    private String emailAddress;
    @SerializedName("entry.284483984")
    @Expose
    private String projectLink;



    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public String getEmailAddress(){
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress){
        this.emailAddress=emailAddress;
    }
    public String getProjectLink(){
        return projectLink;
    }
    public void setProjectLink(String projectLink){
        this.projectLink=projectLink;
    }
}
