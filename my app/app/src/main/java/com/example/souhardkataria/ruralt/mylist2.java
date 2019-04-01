package com.example.souhardkataria.ruralt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

//Tushar (17CO149) -- start

public class mylist2 extends ArrayAdapter<notify_guide> {
    ArrayList<notify_guide> c;
    public mylist2(Context context, ArrayList<notify_guide> n)
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
            listitemview= LayoutInflater.from(getContext()).inflate(R.layout.mylist2,parent,false);
        }
        notify_guide n=getItem(position);

        TextView t=(TextView) listitemview.findViewById(R.id.Tname);
        t.setText(n.notification);
        return listitemview;
    }
}