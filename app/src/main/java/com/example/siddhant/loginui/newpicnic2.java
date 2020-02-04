package com.example.siddhant.loginui;

public class newpicnic2 {
    private String SpotName;
    private String Description;
    private String Address;

    public newpicnic2(String spotname,String description,String address) {
        this.SpotName = spotname;
        this.Description=description;
        this.Address=address;
    }

    public newpicnic2() {
    }

    public String getSpotName() {
        return SpotName;
    }

    public void setSpotName(String spotName) {
        SpotName = spotName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
