package com.example.jakesi.fotagmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by jakesi on 16-04-01.
 */
public class FullScreenActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullscreen_layout);


        ImageView imageView = (ImageView) findViewById(R.id.image);
        Intent intent = getIntent();
        String resourceName = intent.getStringExtra("image");
        String url = intent.getStringExtra("url");
        if(!url.isEmpty()){
            new GetImageFromUrlTask(imageView).execute(url);
        }else if(!resourceName.isEmpty()){
            imageView.setImageResource(getResources().getIdentifier(resourceName, "drawable", getPackageName()));
        }



    }
}
