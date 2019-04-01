package com.example.souhardkataria.ruralt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.concurrent.Executor;


// Tushar (17CO149) -- start
public class TravelGuideAdapter extends BaseAdapter {
    ArrayList<register_users> lvList;
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<String> arrayID;
    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    DatabaseReference myRef;
    public TravelGuideAdapter(ArrayList<register_users> lvList, Context context,ArrayList<String> arrayID) {
        this.lvList = lvList;
        this.context = context;
        this.arrayID=arrayID;
        mAuth=FirebaseAuth.getInstance();
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        try {
            database=FirebaseDatabase.getInstance();
            myRef=database.getReference("registers");
        }catch (Exception e)
        {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public int getCount() {
        return lvList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1=layoutInflater.inflate(R.layout.listcomp,viewGroup,false);
        TextView name=view1.findViewById(R.id.Tname);
        TextView village=view1.findViewById(R.id.Tvillage);
        Button accept=view1.findViewById(R.id.Taccept);
        Button reject=view1.findViewById(R.id.Treject);
        //final String id=arrayID.get(i);
        //final register_users obj=lvList.get(i);
        /*accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.child("Authenticated").child(id).setValue(obj);
                myRef.child("registers").child(id).removeValue();
                Toast.makeText(context,"Onclick Travel ",Toast.LENGTH_LONG).show();
                //Code for delete
                //myRef.child("registers")
            }
        });*/

        class butOn implements View.OnClickListener
        {
            register_users obj;
            String id;
            butOn(String id,register_users obj)
            {
                super();
                this.id=id;
                this.obj=obj;
            }

            @Override
            public void onClick(View view) {
                //Log.i("CHECK","ONclick id: "+id+" variable: "+obj.name);
                //myRef.child("Authenticated").child(obj.id).setValue(obj);
                myRef.child(obj.id).setValue(null);
                Toast.makeText(context,"Onclick Travel ",Toast.LENGTH_LONG).show();
                //new
                try {
                    mAuth.createUserWithEmailAndPassword(obj.email, "1234567");
                           /* .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        //Log.d(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        //new2
                                        //myRef.child("registers").child(obj.name).removeValue();
                                        //updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        // Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(context, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }

                                    // ...
                                }
                            });*/
                }catch (Exception e)
                {
                    Toast.makeText(context,"Auth Problem: "+e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        }
        class bb implements View.OnClickListener {
            register_users obj;
            String id;

            bb(String id, register_users obj) {
                super();
                this.id = id;
                this.obj = obj;
            }

            @Override
            public void onClick(View view) {
                //Log.i("CHECK","ONclick id: "+id+" variable: "+obj.name);
                //myRef.child("Authenticated").child(obj.id).setValue(obj);
                myRef.child(obj.id).setValue(null);
                Toast.makeText(context, "Onclick Travel ", Toast.LENGTH_LONG).show();

            }
        }
        butOn bt=new butOn(arrayID.get(i),lvList.get(i));
        Log.i("CHECK","id: "+arrayID.get(i)+" variable: "+lvList.get(i).name);
        accept.setOnClickListener(bt);
        bb bc=new bb(arrayID.get(i),lvList.get(i));
        reject.setOnClickListener(bc);
        name.setText(lvList.get(i).name);
        village.setText(lvList.get(i).village);
        // Toast.makeText(context ,lvList.get(i).name,Toast.LENGTH_LONG).show();
        return view1;
    }

}

// Tushar (17CO149) -- start
