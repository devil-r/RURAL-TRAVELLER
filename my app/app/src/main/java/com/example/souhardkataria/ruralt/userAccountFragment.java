package com.example.souhardkataria.ruralt;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class userAccountFragment extends Fragment {


    public userAccountFragment() {
        // Required empty public constructor
    }
    String old;
    DatabaseReference Wallet= FirebaseDatabase.getInstance().getReference("Wallet");
    String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view =inflater.inflate(R.layout.fragment_user_account, container, false);

        Button changepass  = ( Button )view.findViewById(R.id.changepass);
        changepass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), change_pass.class);
                startActivity(i);

            }


        });


         Button logout  = ( Button )view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Rural_Traveller.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();

            }


        });

        Button details  = ( Button )view.findViewById(R.id.account_details);
        details.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), display_profile.class);
                startActivity(i);

            }


        });

        Button Wishlist = view.findViewById(R.id.favourites);
        Wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),myAccount_wishlist.class));
            }
        });

        Button FAQ = view.findViewById(R.id.faq);
        FAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Faq.class));

            }
        });
        Button wallet=view.findViewById(R.id.wallet);
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent I=new Intent(getActivity(),wallet.class);
                startActivity(I);
            }
        });
        Button Forum = view.findViewById(R.id.forum);
        Forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Forum.class));

            }
        });
        Button My_packeges = view.findViewById(R.id.my_packages);
        My_packeges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),my_account_mypackages.class));

            }
        });

        return view;

    }

}
