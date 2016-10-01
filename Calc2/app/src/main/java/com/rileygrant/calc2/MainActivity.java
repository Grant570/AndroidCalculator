package com.rileygrant.calc2;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private final static String[] contentsPort = new String[]{
            "AC","(",")","*",
            "7","8","9","/",
            "4","5","6","-",
            "1","2","3","+",
            "0",".","(-)","="
    };
    private final static String[] contentsLand = new String[]{
            "AC","(",")","*", "/","-",
            "0","1","2","3","4","+",
            "5","6","7","8","9"
            ,".","(-)","="
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridLayout gridLayout = (GridLayout)findViewById(R.id.grid_layout);
        final TextView tv = (TextView)findViewById(R.id.text_view);
        int orientation = getWindowManager().getDefaultDisplay().getRotation();
        String contents[] = contentsPort;
        //if portrait
        if(orientation == 0){
            gridLayout.setRowCount(5);
            gridLayout.setColumnCount(4);
        }
        else{
            contents = contentsLand;
            gridLayout.setRowCount(3);
            gridLayout.setColumnCount(6);
        }
        for(int i = 0; i < contents.length; i++){
            Button btn = new Button(getApplicationContext());
            btn.setText(contents[i]);
            gridLayout.addView(btn);
            btn.setId(i);
            final String content = contents[i];
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(content == "="){
                        if(tv.getText().toString().length() >0 && tv.getText().toString().substring(tv.length()-1).matches("\\d|\\)")&& finalCheckGood(tv.getText().toString())){
                            //tv.setText("evaluate");
                            //float result = Evaluate(tv.getText().toString());
                            String[] test = Evaluate(tv.getText().toString());
                            String concat = "";
                            for(int i = 0; i< test.length; i++){
                                concat = concat+test[i]+"\n";
                            }
                            tv.setText(concat);
                        }
                        else{
                            //add backspace that reverts to last input
                            tv.setText("Syntax Error");
                        }
                        //tv.setText("Evaluate");
                    }
                    else if(content == "AC"){
                        tv.setText("");
                    }
                    else{
                        tv.setText(isValid(tv.getText().toString(),content));
                    }
                }
            });
        }
    }
    private String isValid(String existing, String btnContent){
        //if it's a number
        if(btnContent.matches("\\d")){
            return existing+ btnContent;
        }
        //if negative
        else if(btnContent.equals("(-)")){
            if(existing.length() == 0 ){
                return  "(-1)*";
            }
            else if((existing.length()> 0 && existing.substring(existing.length()-1).matches("\\+|-|\\*|/|\\)|\\("))){
                if(existing.substring(existing.length()-1).matches("\\d")){
                    return existing + "*(-1)*";
                }
                else if(existing.substring(existing.length()-1).equals("(")){
                    return existing + "(-1)*";
                }
                else{
                    return existing + "(-1)*";
                }
            }
        }
       else if(btnContent.equals(".")){
            if(existing.isEmpty() || existing.substring(existing.length()-1).matches("[-+*/]|\\)")){
                return existing+  "0.";
            }
            //look at the end only $
            Pattern pattern = Pattern.compile("(\\.\\d+)$");
            Matcher matcher = pattern.matcher(existing);
            //if there is a . there is a decimal
            //so negate!
            if(!matcher.find() && !existing.substring(existing.length()-1).equals(".")){
                return existing+ ".";
            }
        }
        else if(btnContent.matches("\\+|-|\\*|/")){
            if(existing.length() > 0 && existing.substring(existing.length()-1).matches("\\d|\\)")){
                return existing + btnContent;
            }
        }
        else if(btnContent.matches("\\(|\\)")){
            if(btnContent.equals("(")){
                return existing + "(";
            }
            else if(btnContent.equals(")")&& existing.length()> 0 && existing.substring(existing.length()-1).matches("\\d")){
                return existing + ")";
            }
        }
        return existing;
    }
    //checking for parentheses
    private boolean finalCheckGood(String str){
        int tracker = 0;
        for (char c: str.toCharArray())
        {
            if(c == '('){
                tracker++;
            }
            else if(c == ')'){
                tracker--;
            }
        }
        if(tracker == 0){
            return true;
        }
        return false;
    }
    //evaluate the input
    private String[] Evaluate(String str) {
        str = str.replace("(-)","(-1)*");
        str = str.replace('X','*');
        //Stack<String> ops = new Stack<>();
        Stack<Float> numStack = new Stack<>();
        String[] nums = str.split("\\+|-|\\*|/|\\(-1\\)\\*|\\(|\\)");
        for (int i = 0; i< nums.length; i++){
            numStack.push(Float.parseFloat(nums[i]));
        }
        return nums;
        }
    }

