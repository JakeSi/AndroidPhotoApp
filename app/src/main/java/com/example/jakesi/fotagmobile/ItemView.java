package com.example.jakesi.fotagmobile;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;


/**
 * Created by jakesi on 16-03-31.
 */
public class ItemView extends RelativeLayout{
    private ImageView imageView;
    private Item m;
    private RatingBar ratingBar;

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

    public void setItem(final Item item) {
        // TODO: set up image URL
        m = item;
        imageView = (ImageView) findViewById(R.id.imageView);
        addListenerOnRatingBar();
        if(!item.getImageUrl().isEmpty()){
            new GetImageFromUrlTask(this.imageView).execute(item.getImageUrl());
        }else if(!item.getResourceName().isEmpty()){
            imageView.setImageResource(getResources().getIdentifier(item.getResourceName(), "drawable", getContext().getPackageName()));
        }
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FullScreenActivity.class);

                intent.putExtra("image", item.getResourceName());
                intent.putExtra("url", item.getImageUrl());
                getContext().startActivity(intent);
            }
        });
    }

    public ImageView getImageView () {
        return imageView;
    }

    public void addListenerOnRatingBar() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setRating(m.getRating());

        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBarChangeListener());
    }

    class RatingBarChangeListener implements  RatingBar.OnRatingBarChangeListener {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            m.setRating((int)rating);
        }
    }
}
