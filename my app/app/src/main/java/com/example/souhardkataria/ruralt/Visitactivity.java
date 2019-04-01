package com.example.souhardkataria.ruralt;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Visitactivity extends AppCompatActivity {

    DatabaseReference ref;
    static List<Visit> list = new ArrayList<>();
    static  List<String> lst = new ArrayList<>();
    RecyclerView recyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitactivity);

        recyclerView = findViewById(R.id.RecVisit);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //String string = getIntent().getStringExtra("Village");

            ref = FirebaseDatabase.getInstance().getReference().child("Villages").child(VillageHome.str).child("Visit");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {

                        list.clear();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            list.add(ds.getValue(Visit.class));
                        }
                        lst.clear();
                        for (Visit s : list)
                            lst.add(s.Name);
                        RecycleAdapterVisit adapter = new RecycleAdapterVisit(getApplicationContext());
                        recyclerView.setAdapter(adapter);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


       // if(list.isEmpty())
         //   Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();



    }

    class RecycleAdapterVisit extends RecyclerView.Adapter<RecycleAdapterVisit.MyViewHolder>{

        Context context;
        public RecycleAdapterVisit(Context context)
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
            viewHolder.view.setText(Visitactivity.lst.get(i));
            viewHolder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.visit));
        }

        @Override
        public int getItemCount() {
            return StayActivity.lst.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView view;
            ImageView img ;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                view = itemView.findViewById(R.id.Textst);
                img = itemView.findViewById(R.id.StayImage);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int i = getAdapterPosition();
                Visit stay = list.get(i);
                Intent intent = new Intent(Visitactivity.this, Displayvisit.class);
                intent.putExtra("Obj",stay);
                startActivity(intent);
            }

        }
    }
}