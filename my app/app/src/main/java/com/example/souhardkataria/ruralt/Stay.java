package com.example.souhardkataria.ruralt;
import java.io.Serializable;

public class Stay implements Serializable {


    public Stay(){}
    int StayId,rating;
    long  Contact;
    String Name,Address;

    public Stay(int stayId, int rating, long contact, String name, String address) {
        StayId = stayId;
        this.rating = rating;
        Contact = contact;
        Name = name;
        Address = address;
    }


}
