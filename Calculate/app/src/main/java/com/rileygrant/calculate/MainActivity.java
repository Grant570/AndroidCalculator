package com.rileygrant.calculate;
import android.app.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final String[] nums = new String[]{
            "1","2","3","4","5","6","7","8","9","0"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gv = (GridView)findViewById(R.id.gridview);
        //gv.setAdapter(new ButtonAdapter(this));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nums);
        gv.setAdapter(adapter);
        //String[] test = {"test"};
        //gv.setAdapter(new TextViewAdapter(this,test));

       gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Toast.makeText(getApplicationContext(),
                       ((TextView)view).getText(), Toast.LENGTH_SHORT).show();

           }
       });
    }
}
