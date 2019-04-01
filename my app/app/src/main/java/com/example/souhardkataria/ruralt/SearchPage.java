package com.example.souhardkataria.ruralt;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

public class SearchPage extends AppCompatActivity {
    EditText village;
    ImageButton btn;
    ListView lv;
    DatabaseReference mRef;
    TextView status;
    ArrayList<Village> array=new ArrayList<>();
    ArrayList<String> ids=new ArrayList<>();
    ArrayList<Village> ADParray=new ArrayList<>();
    ArrayList<String> ADPids=new ArrayList<>();
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        village=findViewById(R.id.village_name);
        btn=findViewById(R.id.imageView8);
        lv=findViewById(R.id.searchResult);
        mRef=FirebaseDatabase.getInstance().getReference("Villages");
        status=findViewById(R.id.statusBar);
        status.setText("retrieving data from database!");
        adapter = new Adapter();
        lv.setAdapter(adapter);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                array.clear();
                ids.clear();
                for(DataSnapshot x:dataSnapshot.getChildren())
                {
                    ids.add(x.getKey());
                    array.add(x.child("Object").getValue(Village.class));
                    //Toast.makeText(getApplicationContext(),x.getKey(),Toast.LENGTH_LONG).show();
                }
                status.setText("Ready to search");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key=village.getText().toString().trim();
                ArrayList<Double> simArray=new ArrayList<>();
                ADPids.clear();
                ADParray.clear();
                simArray.clear();
                if(key.equals(""))
                {
                    village.setError("Enter some text here!");
                    adapter.notifyDataSetChanged();
                }else
                {
                    StringSimilarity obj=new StringSimilarity();
                    status.setText("Searching...");
                    int i=0;
                    for(String x:ids)
                    {
                        Double cs=obj.similarity(key,x.trim());
                        if(cs>0.4)
                        {
                            ADPids.add(x);
                            ADParray.add(array.get(i));
                            simArray.add(cs);
                        }
                        i++;
                    }
                    if(ADPids.size()==0)
                    {
                        status.setText("No village Found!");
                        adapter.notifyDataSetChanged();
                    }
                    else {
                        int u,v;
                        for(u=0;u<simArray.size();u++)
                        {
                            for (v=0;v<simArray.size()-1;v++)
                            {
                                if(simArray.get(v)<simArray.get(v+1))
                                {
                                    Collections.swap(ADPids,v,v+1);
                                    Collections.swap(ADParray,v,v+1);
                                    Collections.swap(simArray,v,v+1);
                                }
                            }
                        }
                        status.setText("Result!");
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    class Adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return ADPids.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            Context context=getApplicationContext();
            view = getLayoutInflater().inflate(R.layout.village_list_element,viewGroup,false);
            TextView vil=view.findViewById(R.id.villageBox);
            TextView loc=view.findViewById(R.id.locBox);
            LinearLayout ll=view.findViewById(R.id.touch);
            vil.setText(ADPids.get(i));
            loc.setText(ADParray.get(i).District+", "+ADParray.get(i).State);
            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in=new Intent(getApplicationContext(),VillageHome.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    in.putExtra("Village",ADPids.get(i));
                    startActivity(in);
                }
            });
            return view;
        }
    }
    public class StringSimilarity {

        public double similarity(String s1, String s2) {
            String longer = s1, shorter = s2;
            if (s1.length() < s2.length()) {
                longer = s2; shorter = s1;
            }
            int longerLength = longer.length();
            if (longerLength == 0) { return 1.0; }
            return (longerLength - editDistance(longer, shorter)) / (double) longerLength;

        }

        public int editDistance(String s1, String s2) {
            s1 = s1.toLowerCase();
            s2 = s2.toLowerCase();

            int[] costs = new int[s2.length() + 1];
            for (int i = 0; i <= s1.length(); i++) {
                int lastValue = i;
                for (int j = 0; j <= s2.length(); j++) {
                    if (i == 0)
                        costs[j] = j;
                    else {
                        if (j > 0) {
                            int newValue = costs[j - 1];
                            if (s1.charAt(i - 1) != s2.charAt(j - 1))
                                newValue = Math.min(Math.min(newValue, lastValue),
                                        costs[j]) + 1;
                            costs[j - 1] = lastValue;
                            lastValue = newValue;
                        }
                    }
                }
                if (i > 0)
                    costs[s2.length()] = lastValue;
            }
            return costs[s2.length()];
        }
    }
}
