package com.rileygrant.calculate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Grant on 9/25/2016.
 */

public class TextViewAdapter extends BaseAdapter {
    private Context context;
    private final String[] textViewValues;

    public TextViewAdapter(Context context, String[] textViewValues){
        this.context = context;
        this.textViewValues = textViewValues;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        if (convertView == null){
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.item,null);

            TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
            textView.setText(textViewValues[position]);
        }
        else{
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount(){
        return textViewValues.length;
    }

    @Override
    public Object getItem(int position){
        return null;
    }

    @Override
    public long getItemId(int position){
        return 0;
    }
}
