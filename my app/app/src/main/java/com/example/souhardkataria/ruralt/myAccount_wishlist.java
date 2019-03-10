package com.example.souhardkataria.ruralt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.Menu;
import android.view.MenuItem;

public class myAccount_wishlist extends AppCompatActivity {

        String [] Array={};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_wishlist);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.activity_my_account_wishlist,Array);
        ListView listView = this.findViewById(R.id.list_myaccount);
        listView.setAdapter(adapter);


    }
}
