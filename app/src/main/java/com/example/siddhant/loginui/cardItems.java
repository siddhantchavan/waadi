package com.example.siddhant.loginui;

public class cardItems {
    private String name;
    private Long price;
    private String url;
    private Double rating;
    private String loc;


    private String nearby;

    public cardItems(String name, Long price,String url,Double rating,String loc,String nearby) {
        this.name = name;
        this.price=price;
        this.url=url;
        this.loc=loc;
        this.rating=rating;
        this.nearby=nearby;
    }

    public String getNearby() {
        return nearby;
    }

    public void setNearby(String nearby) {
        this.nearby = nearby;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Double getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public Long getprice() { return price;}

    public String getUrl() { return url; }

    public void setImg (String img){this.url = url;}
}
