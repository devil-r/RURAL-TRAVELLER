package com.example.souhardkataria.ruralt;

//Souhard Kataria (17CO147) --- Start

import java.io.Serializable;

public class Visit implements Serializable {

        public Visit(){}
        int PlaceId,Distance_Village,User_Rating;
        String Name,Location;

        public Visit(int placeId, int distance_Village, int user_Rating, String name, String location) {
            PlaceId = placeId;
            Distance_Village = distance_Village;
            User_Rating = user_Rating;
            Name = name;
            Location = location;
        }


}

//Souhard Kataria (17CO147) --- Start
