package com.rileygrant.calculate;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

/**
 * Created by Grant on 9/25/2016.
 */

public class ButtonAdapter extends BaseAdapter {
    private Context mContext;

    public ButtonAdapter(Context c){
        this.mContext = c;
    }
    public int getCount(){
        return 10;
    }
    public Object getItem(int position) {
        return null;
    }
    public long getItemId(int postion){
        return 0;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        Button button;
        if(convertView == null){
            button = new Button(mContext);
            button.setLayoutParams(new GridView.LayoutParams(85,85));
            button.setPadding(8,8,8,8);
        }
        else{
            button = (Button) convertView;
        }
        button.setText(Integer.toString(position));
        return button;
    }
}
