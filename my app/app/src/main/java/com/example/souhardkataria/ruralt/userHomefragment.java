package com.example.souhardkataria.ruralt;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
//Parth (17CO215) -- start


public class userHomefragment extends Fragment {
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    TextView name;
    ListView listPackage;
    ArrayList<PackageClass> array=new ArrayList<>();
    ArrayList<String> ids=new ArrayList<>();
    ArrayList<Bitmap> imgs=new ArrayList<>();
    PackageAdapter adapter;
    ProgressBar pb;
    public userHomefragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_user_homefragment, container, false);
        name=view.findViewById(R.id.nameString);
        mAuth = FirebaseAuth.getInstance();
        pb=view.findViewById(R.id.progressBar2);
        pb.setVisibility(View.VISIBLE);
        listPackage=view.findViewById(R.id.listPackages);
        adapter = new PackageAdapter();
        listPackage.setAdapter(adapter);

        mRef= FirebaseDatabase.getInstance().getReference("Packages");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        name.setText("Hi!, "+user.getEmail());
        try {
            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    array.clear();
                    ids.clear();
                    imgs.clear();
                    for (DataSnapshot i : dataSnapshot.getChildren()) {
                        array.add(i.getValue(PackageClass.class));
                        ids.add(i.getKey());
                        imgs.add(null);
                    }
                    //Toast.makeText(getContext(),array.get(2).name+"",Toast.LENGTH_LONG).show();
                    adapter.notifyDataSetChanged();
                    pb.setVisibility(View.GONE);
                   // Toast.makeText(getContext(),adapter.getCount()+"",Toast.LENGTH_LONG).show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }catch (Exception e)
        {
            Log.i("None",e.getMessage());
        }

        return view;
    }

    class PackageAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return array.size();
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
        public View getView(int i, View view, ViewGroup viewGroup) {
            Context context=getContext();
            view = ((FragmentActivity) context).getLayoutInflater().inflate(R.layout.package_element, viewGroup, false);
            TextView name=view.findViewById(R.id.name1);
            TextView duration=view.findViewById(R.id.duration);
            TextView rate=view.findViewById(R.id.rate);
            CardView ll=view.findViewById(R.id.ll);
            ImageView im=view.findViewById(R.id.imageView9);
            name.setText(array.get(i).name);
            duration.setText(array.get(i).Duration);
            rate.setText("Rs. "+array.get(i).Rate);

            if(imgs.get(i)!=null)
                im.setImageBitmap(imgs.get(i));
            else {
                DownloadImageFromInternet dwn = new DownloadImageFromInternet(im);
                dwn.execute(array.get(i).Image);
                imgs.set(i, dwn.bimage);
            }

            final Intent in=new Intent(context,packages.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            in.putExtra("Village",ids.get(i));
            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(in);
                }
            });
            return view;
        }

        private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
            ImageView imageView;
            Bitmap bimage;

            public DownloadImageFromInternet(ImageView imageView) {
                this.imageView = imageView;
                }

            protected Bitmap doInBackground(String... urls) {
                String imageURL = urls[0];
                bimage = null;
                try {
                    InputStream in = new java.net.URL(imageURL).openStream();
                    bimage = BitmapFactory.decodeStream(in);

                } catch (Exception e) {
                    Log.e("Error Message", e.getMessage());
                    e.printStackTrace();
                }
                return bimage;
            }

            protected void onPostExecute(Bitmap result) {
                imageView.setImageBitmap(result);
            }
        }

    }
}

//Parth (17CO215) -- start