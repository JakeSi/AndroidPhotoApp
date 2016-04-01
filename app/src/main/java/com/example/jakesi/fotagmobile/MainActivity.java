package com.example.jakesi.fotagmobile;

import android.app.AlertDialog;;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RatingBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ItemAdapter ia;
    private EditText userInput;
    final Context context = this;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(null);

        ArrayList<Item> items;
        int rating;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addListenerOnRatingBar();

        GridView gridView = (GridView) findViewById(R.id.gridView);

        if(null == savedInstanceState) {
            items = new ArrayList<Item>();
            rating = 0;
        }else{
            items = savedInstanceState.getParcelableArrayList("original");
            rating = savedInstanceState.getInt("rating");
        }

        ia = new ItemAdapter(context, items);
        gridView.setAdapter(ia);
        ratingBar.setRating(rating);
        ia.notifyDataSetChanged();
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("original",ia.getOriginalList());
//        outState.putParcelableArrayList("original",ia.getOriginalList());
        outState.putInt("rating",(int)ratingBar.getRating());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            ia.clearItems();
            ia.notifyDataSetChanged();
        }else if(id == R.id.action_load) {
            Item img;
            for(int i = 1; i <= 10 ;i++){
                img = new Item("","image_"+i,(int)(Math.random()*6 - 1));
                ia.addItem(img);
            }
            ia.notifyDataSetChanged();
        }else if(id == R.id.action_search) {
            LayoutInflater li = LayoutInflater.from(context);
            View popupView = li.inflate(R.layout.popup_view, null);

            // set dialog message
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            userInput = (EditText) popupView.findViewById(R.id.editTextSearch);

            alertDialogBuilder.setView(popupView);

            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setPositiveButton("OK",new SearchOkListender());
            alertDialogBuilder.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void addListenerOnRatingBar() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBarChangeListener());
    }

    class SearchOkListender implements DialogInterface.OnClickListener{
        public void onClick(DialogInterface dialog, int id) {
            String url = userInput.getText().toString();
            if (!url.isEmpty()) {
                Item item = new Item(url, "", 0);
                ia.addItem(item);

                ia.notifyDataSetChanged();
            }
        }
    }

    class RatingBarChangeListener implements  RatingBar.OnRatingBarChangeListener {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            ia.getFilter().filter(String.valueOf((int)rating));
        }
    }
}