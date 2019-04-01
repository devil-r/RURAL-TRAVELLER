package com.example.souhardkataria.ruralt;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        int img = getArguments().getInt("Image");
        String url = getArguments().getString("Url");
        ImageView imageView = (ImageView)view;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),img);
        imageView.setImageBitmap(bitmap);
        imageView.setAdjustViewBounds(true);
        Glide.with(getContext()).load(url).into(imageView);
        return view;
    }

}
