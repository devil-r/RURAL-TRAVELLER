package com.example.souhardkataria.ruralt;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class userHomefragment extends Fragment {
    FirebaseAuth mAuth;
    TextView name;
    public userHomefragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_user_homefragment, container, false);
        name=view.findViewById(R.id.nameString);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email="Hi, Human";
        if (user != null) {
            email = user.getEmail().toString();
            String uid = user.getUid();

        }
        name.setText("Hi, "+email);
        return view;
    }

}
