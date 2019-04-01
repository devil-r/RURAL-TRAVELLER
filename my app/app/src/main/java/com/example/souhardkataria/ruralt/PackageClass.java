package com.example.souhardkataria.ruralt;

//Parth (17CO215) -- start

public class PackageClass {
    public String Description;
    public String Duration;
    public String Guide;
    public String Image;
    public String Overview;
    public String Rate;
    public String name;

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
