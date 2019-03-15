package com.example.souhardkataria.ruralt;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class userExploreFragment extends Fragment implements View.OnClickListener {
    CardView c1,c2,c3;
    ImageButton search;
    public userExploreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_user_explore, container, false);
        c1=(CardView) view.findViewById(R.id.card1);
        c2=(CardView) view.findViewById(R.id.card2);
        c3=(CardView) view.findViewById(R.id.card3);
        c1.setOnClickListener(this);c2.setOnClickListener(this);c3.setOnClickListener(this);
        search=view.findViewById(R.id.searchIntent);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to search page
                Intent in=new Intent(getContext(),SearchPage.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card1:
                Intent intent1=new Intent(getActivity(),VillageHome.class);
                intent1.putExtra("Village","Landour");
                startActivity(intent1);
                break;
            case R.id.card2:
                Intent intent2=new Intent(getActivity(),VillageHome.class);
                intent2.putExtra("Village","Malana");
                startActivity(intent2);
                break;
            case R.id.card3:
                Intent intent3=new Intent(getActivity(),VillageHome.class);
                intent3.putExtra("Village","Diskit");
                startActivity(intent3);
                break;
        }
    }
}
