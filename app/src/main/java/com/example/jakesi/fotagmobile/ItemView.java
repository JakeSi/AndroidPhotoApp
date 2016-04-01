package com.example.jakesi.fotagmobile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.InputStream;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by jakesi on 16-03-31.
 */
public class ItemView extends RelativeLayout implements Observer {
    private ImageView imageView;
    private Item m;

    public static ItemView inflate(ViewGroup parent) {
        return (ItemView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_cell, parent, false);
    }

    public ItemView(Context c) {
        this(c, null);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public ItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setItem(Item item) {
        // TODO: set up image URL
        m = item;
        imageView = (ImageView) findViewById(R.id.imageView);
        new GetImageFromUrlTask(this.imageView).execute("https://pixabay.com/static/uploads/photo/2015/10/01/21/39/background-image-967820_960_720.jpg");
//        imageView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), FullViewActivity.class);
//                intent.putExtra("image", modelImage.resourceName);
//                getContext().startActivity(intent);
//            }
//        });
    }

    public ImageView getImageView () {
        return imageView;
    }


    @Override
    public void update(Observable observable, Object data) {
//        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.contentmain);
//        viewGroup.removeAllViews();
//
//        if (model.collection.size() == 0) {
//            pictures = new ArrayList<ViewImage>();
//        }
//
//        else if (model.collection.size() > pictures.size()) {
//            for (int i = pictures.size(); i < model.collection.size(); i++ ) {
//                ViewImage imageView = new ViewImage(getContext(), model.collection.get(i), model);
//                pictures.add(imageView);
//            }
//        }
//
//        for (int i = 0; i < model.collection.size(); i++) {
//            if (model.collection.get(i).rating >= model.filter) {
//                viewGroup.addView(pictures.get(i));
//            }
//        }
    }
}
