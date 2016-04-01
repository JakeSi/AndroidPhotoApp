package com.example.jakesi.fotagmobile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

/**
 * Created by jakesi on 16-03-31.
 */
public class ItemAdapter extends ArrayAdapter<Item> implements Filterable {

    private Filter filter;
    private ArrayList<Item> original = new ArrayList();
    private ArrayList<Item> filtered = new ArrayList();

    public ItemAdapter(Context c, ArrayList<Item> items) {
        super(c, 0, items);
        if(!items.isEmpty()){
            this.original = new ArrayList(items);
        }
    }

    public void addItem(Item item){
        original.add(item);
        add(item);
    }

    public void clearItems(){
        original.clear();
        clear();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemView itemView = (ItemView)convertView;
        if (null == itemView)
            itemView = ItemView.inflate(parent);
        itemView.setItem(getItem(position));

        return itemView;

    }

    @Override
    public Filter getFilter()
    {
        if (filter == null)
            filter = new RatingFilter();

        return filter;
    }

    public ArrayList<Item> getOriginalList() {
        return original;
    }

    public ArrayList<Item> getFilteredList() {
        return filtered;
    }

    private class RatingFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint)
        {
            FilterResults results = new FilterResults();
            int filter = Integer.valueOf(constraint.toString());

            final ArrayList<Item> list = new ArrayList<Item>(original);
            final ArrayList<Item> newList = new ArrayList<Item>();
            int count = list.size();

            for (int i=0; i < count; i++)
            {
                final Item item = list.get(i);

                if (item.getRating() >= filter || item.getRating() == 0)
                {
                    newList.add(item);
                }
            }
            results.values = newList;
            results.count = newList.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filtered = (ArrayList<Item>)results.values;
            if(null == filtered){
                return;
            }

            clear();
            int count = filtered.size();
            for (int i=0; i<count; i++)
            {
                Item item = (Item) filtered.get(i);
                add(item);
            }
        }

    }
}
