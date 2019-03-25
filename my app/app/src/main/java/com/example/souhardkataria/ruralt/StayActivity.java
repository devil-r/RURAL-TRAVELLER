package com.example.souhardkataria.ruralt;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
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
import java.util.zip.Inflater;

public class StayActivity extends AppCompatActivity {

    DatabaseReference ref;
    static List<Stay> list = new ArrayList<>();
    static  List<String> lst = new ArrayList<>();
    RecyclerView recyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stay);

        recyclerView = findViewById(R.id.RecStay);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(StayActivity.this,2);
        recyclerView.setLayoutManager(layoutManager);
        //String string = getIntent().getStringExtra("Village");
        try {
            ref = FirebaseDatabase.getInstance().getReference().child("Villages").child(VillageHome.str).child("Stay");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    list.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        list.add(ds.getValue(Stay.class));
                    }
                    lst.clear();
                    for (Stay s : list)
                        lst.add(s.Name);
                    RecycleAdapter adapter = new RecycleAdapter(getApplicationContext());
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

    class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder>{

        Context context;
        public RecycleAdapter(Context context)
        {
            this.context = context;
        }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = (View)LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stay,viewGroup,false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
          viewHolder.view.setText(StayActivity.lst.get(i));
          viewHolder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.hotel));
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
                view.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int i = getAdapterPosition();
                Stay stay = list.get(i);
                Intent intent = new Intent(StayActivity.this, DisplayStay.class);
                intent.putExtra("Obj",stay);
                startActivity(intent);
            }

        }
    }
}
