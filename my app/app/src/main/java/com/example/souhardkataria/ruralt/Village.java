package com.example.souhardkataria.ruralt;

//Avdhesh (17CO210)

public class Village {

    String VillageId,
            Name,
            State,
            District,
            General_Weather_Conditions,
            Nearest_Railway_Station,
            Nearest_Airport,
            Nearest_City;
    int user_rating,pincode;


    public Village() {
    }

    public Village(String villageId, String name, String state, String district, String general_Weather_Conditions, String nearest_Railway_Station, String nearest_Airport, String nearest_City, int user_rating, int pincode) {
        VillageId = villageId;
        Name = name;
        State = state;
        District = district;
        General_Weather_Conditions = general_Weather_Conditions;
        Nearest_Railway_Station = nearest_Railway_Station;
        Nearest_Airport = nearest_Airport;
        Nearest_City = nearest_City;
        this.user_rating = user_rating;
        this.pincode = pincode;
    }

}






