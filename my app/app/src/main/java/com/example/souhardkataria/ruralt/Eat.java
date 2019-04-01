package com.example.souhardkataria.ruralt;

//Souhard Kataria (17CO147) --- Start

import java.io.Serializable;

public class Eat implements Serializable {

    public Eat(){}
    public int id,rating;
    public String Name,Address;
    public long Contact;

    public Eat(int id, int rating, String name, String address, long contact) {
        this.id = id;
        this.rating = rating;
        Name = name;
        Address = address;
        Contact = contact;
    }


}

//Souhard Kataria (17CO147) --- end