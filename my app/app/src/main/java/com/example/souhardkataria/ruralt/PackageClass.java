package com.example.souhardkataria.ruralt;

//Parth (17CO215) -- start

public class PackageClass {
    String Description;
    String Duration;
    String Guide;
    String Image;
    String Overview;
    String Rate;
    String name;

    public PackageClass() {
    }

    public PackageClass(String description, String duration, String guide, String image, String overview, String rate, String name) {
        Description = description;
        Duration = duration;
        Guide = guide;
        Image = image;
        Overview = overview;
        Rate = rate;
        this.name = name;
    }
}
