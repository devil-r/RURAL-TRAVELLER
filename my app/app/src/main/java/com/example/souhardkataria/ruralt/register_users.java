package com.example.souhardkataria.ruralt;

import java.io.Serializable;
import java.math.BigInteger;

public class register_users  implements Serializable {
    String name;
    String email;
    String phno;
    String dob;
    String gender;
    String village;
    String address;
    String about;
    String yre;
    String id;
    public register_users()
    {

    }
    public register_users(String name,String email,String phno,String dob,String gender,String village,String address,String about,
                          String yre,String id)
    {
        this.name=name;
        this.email=email;
        this.phno=phno;
        this.dob=dob;
        this.gender=gender;
        this.village=village;
        this.address=address;
        this.about=about;
        this.yre=yre;
        this.id=id;
    }

}