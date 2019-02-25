package com.example.souhardkataria.ruralt;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Eatactivity extends AppCompatActivity {

    DatabaseReference ref;
    static List<Eat> list = new ArrayList<>();
    static  List<String> lst = new ArrayList<>();
    RecyclerView recyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitactivity);

        recyclerView = findViewById(R.id.RecVisit);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(Eatactivity.this,2);
        recyclerView.setLayoutManager(layoutManager);
        //String string = getIntent().getStringExtra("Village");
        try {
            ref = FirebaseDatabase.getInstance().getReference().child("Villages").child(VillageHome.str).child("Eat");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {

                        list.clear();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            list.add(ds.getValue(Eat.class));
                        }
                        lst.clear();
                        for (Eat s : list)
                            lst.add(s.Name);
                        RecycleAdapterEat adapter = new RecycleAdapterEat(getApplicationContext());
                        recyclerView.setAdapter(adapter);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }catch (Exception e)

        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        if(list.isEmpty())
            Toast.makeText(this, "No place specified", Toast.LENGTH_SHORT).show();



    }

    class RecycleAdapterEat extends RecyclerView.Adapter<RecycleAdapterEat.MyViewHolder>{

        Context context;
        public RecycleAdapterEat(Context context)
        {
            this.context = context;
        }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stay,viewGroup,false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
            viewHolder.view.setText(Eatactivity.lst.get(i));
        }

        @Override
        public int getItemCount() {
            return Eatactivity.lst.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView view;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                view = itemView.findViewById(R.id.Textst);
                view.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int i = getAdapterPosition();
                Eat stay = list.get(i);
                Intent intent = new Intent(Eatactivity.this, Displayeat.class);
                intent.putExtra("Obj",stay);
                startActivity(intent);
            }

        }
    }
}