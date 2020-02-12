package com.example.siddhant.loginui;
import java.util.List;

public class agency {
    private String ownername;
    private String agencyname;
    private String password1;
    private String rno2;

    public String getPassword() {
        return password1;
    }

    public void setPassword(String password) {
        this.password1 = password;
    }

    public String getRno(){return rno2;}
    public void setRno(String rno2){this.rno2=rno2;}


    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getAgencyname() {
        return agencyname;
    }

    public void setAgencyname(String agencyname) {
        this.agencyname = agencyname;
    }

    public String getAgencyaddress() {
        return agencyaddress;
    }

    public void setAgencyaddress(String agencyaddress) {
        this.agencyaddress = agencyaddress;
    }

    public String getAgencyphone() {
        return agencyphone;
    }

    public void setAgencyphone(String agencyphone) {
        this.agencyphone = agencyphone;
    }

    private String agencyaddress;
    private String agencyphone;



}