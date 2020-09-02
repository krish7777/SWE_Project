package com.example.swe_project.ui.profile;

public class DonorData {
    String name;
    String email;
    String contactNumber;
    String moneyRaised;
    String peopleFed;
    String profilePicPath;


    DonorData(String name,String email,String contactNumber,String moneyRaised,String peopleFed,String profilePicPath){
        this.name=name;
        this.email=email;
        this.contactNumber=contactNumber;
        this.profilePicPath="";
        this.moneyRaised=moneyRaised;
        this.peopleFed=peopleFed;
        this.profilePicPath=profilePicPath;
    }
}
