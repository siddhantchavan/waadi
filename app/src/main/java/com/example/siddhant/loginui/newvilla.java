package com.example.siddhant.loginui;

public class newvilla  {
    private String villaname;
    private String villaprice;
    private String url;


    private String rating;



    public newvilla(String villaname, String villaprice,String url,String rating) {
        this.villaname = villaname;
        this.villaprice=villaprice;
        this.url=url;
        this.rating=rating;
    }

    public String getRating() {
        return rating;
    }

    public String getVillaName() {
        return villaname;
    }

    public String getVillaprice() { return villaprice;}

    public String getUrl() { return url; }

    public void setImg (String img){this.url = url;}

}
