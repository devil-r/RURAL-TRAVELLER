package com.example.souhardkataria.ruralt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

// Parth (17CO215) & Akash-- start

public class fragment_village extends android.support.v4.app.Fragment {
    String str;


    public interface CLICK
    {
        public void Villagestart(String str);
    }

    public CLICK click;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_villages,null);

        final TextView[] txt = {view.findViewById(R.id.textView5),view.findViewById(R.id.textView10),view.findViewById(R.id.textView7),view.findViewById(R.id.textView8),
                view.findViewById(R.id.textView9),view.findViewById(R.id.textView6),view.findViewById(R.id.textView11),view.findViewById(R.id.textView12)};


        txt[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String st = txt[0].getText().toString();
                click.Villagestart(st);
            }
        });

        txt[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String st = txt[1].getText().toString();
                click.Villagestart(st);
            }
        });

        txt[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String st = txt[2].getText().toString();
                click.Villagestart(st);
            }
        });

        txt[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String st = txt[3].getText().toString();
                click.Villagestart(st);
            }
        });

        txt[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String st = txt[4].getText().toString();
                click.Villagestart(st);
            }
        });

        txt[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String st = txt[5].getText().toString();
                click.Villagestart(st);
            }
        });
        txt[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String st = txt[6].getText().toString();
                click.Villagestart(st);
            }
        });
        txt[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String st = txt[7].getText().toString();
                click.Villagestart(st);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity)context;
        try{
            click = (CLICK)activity;
        }
        catch(Exception e){
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

// Parth (17CO215) & Akash-- start