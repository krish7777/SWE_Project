package com.example.swe_project.ui.home;

public class HomeData {
    String description, donate;
    final int imageResource;

    public HomeData(int img, String title, String desc){
        this.imageResource=img;
        this.donate=title;
        this.description=desc;
    }

}
