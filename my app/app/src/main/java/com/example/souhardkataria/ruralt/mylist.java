package com.example.souhardkataria.ruralt;

//Tushar (17CO149) -- start
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

public class mylist extends ArrayAdapter<pakage_noti> {
    ArrayList<pakage_noti>c;
    public mylist(Context context, ArrayList<pakage_noti> n)
    {
        super(context,0,n);
        c=n;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        View listitemview=convertView;
        if(listitemview==null)
        {
            listitemview=LayoutInflater.from(getContext()).inflate(R.layout.mylist,parent,false);
        }
        pakage_noti n=getItem(position);

        TextView t=(TextView) listitemview.findViewById(R.id.Tname);
        t.setText(n.name);
        return listitemview;
    }


}