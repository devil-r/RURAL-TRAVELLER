package com.example.souhardkataria.ruralt;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//Tushar (17CO149) -- start
/**
 * A simple {@link Fragment} subclass.
 */
public class Homeguide extends Fragment {

    CardView c;
    public Homeguide() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_homeguide, container, false);
        c=view.findViewById(R.id.card);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getActivity(),packages.class);
                intent1.putExtra("Village","Landour");
                startActivity(intent1);
            }
        });
        return  view;
    }

}

//Tushar (17CO149) -- end