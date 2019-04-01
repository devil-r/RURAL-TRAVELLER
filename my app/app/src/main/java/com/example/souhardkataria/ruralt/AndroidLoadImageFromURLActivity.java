package com.example.souhardkataria.ruralt;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

// Aashay  (17CO201) -- start

public class AndroidLoadImageFromURLActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);

        // Loader image - will be shown before loading image
        int loader = R.drawable.loader;

        // Image View to show
        ImageView image = findViewById(R.id.imageView3);

        // Image url
        String image_url = "https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjq7snx1PTgAhVG6KQKHQliAAoQjRx6BAgBEAU&url=%2Furl%3Fsa%3Di%26source%3Dimages%26cd%3D%26ved%3D%26url%3Dhttps%253A%252F%252Fwww.tripoto.com%252Ftrip%252Fmy-1st-solo-trip-the-journey-to-welsh-town-landour-584ee8c5ba3c2%26psig%3DAOvVaw1o6ceN2MNSCNJWQjnfc2ub%26ust%3D1552207280429376&psig=AOvVaw1o6ceN2MNSCNJWQjnfc2ub&ust=1552207280429376";

        // ImageLoader class instance
        ImageLoader imgLoader = new ImageLoader(getApplicationContext());

        // whenever you want to load an image from url
        // call DisplayImage function
        // url - image url to load
        // loader - loader image, will be displayed before getting image
        // image - ImageView
        imgLoader.DisplayImage(image_url, loader, image);
    }
}

// Parth Dodiya (17CO215) -- start

