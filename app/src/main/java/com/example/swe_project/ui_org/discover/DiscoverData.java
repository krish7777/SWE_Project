package com.example.swe_project.ui_org.discover;

public class DiscoverData {
    String title,distance, name, phone, description,donation_id,donor_id;

    DiscoverData( String donation_id,String donor_id,String aa, String a,String b,String c,String d){
        this.donation_id=donation_id;
        this.donor_id=donor_id;
        this.description=d;
        this.title=aa;
        this.distance=a;
        this.name=b;
        this.phone=c;
    }
}
