package com.example.siddhant.loginui;

import java.util.List;

public class newpicnic {
    private String SpotName;
    private String Description;
    private String Address;
    private List<String> img_url;

    public newpicnic(String spotname,String description,String address,List<String>imgurl) {
        this.SpotName = spotname;
        this.Description = description;
        this.Address = address;
        this.img_url=imgurl;
    }
    public newpicnic() {
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

    public List<String> getImg_url() {
        return img_url;
    }

    public void setImg_url(List<String> img_url) {
        this.img_url = img_url;
    }
}
