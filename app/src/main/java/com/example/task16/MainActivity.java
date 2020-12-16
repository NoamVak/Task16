package com.example.task16;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    int count=0;
    TextView countV;
    EditText name;
    String nameInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countV=(TextView)findViewById(R.id.countV);
        name=(EditText)findViewById(R.id.name);

        RetToApp();

    }

    public void RetToApp(){
        SharedPreferences prefs_name=getSharedPreferences("NAME_AND_COUNT",MODE_PRIVATE);
        if(prefs_name.getInt("cond",0)==1){
            count=prefs_name.getInt("count",-1);
            countV.setText(" "+ count);
            name.setText(prefs_name.getString("username","none"));
        }
    }

    public void increaseCount(View view) {
        countV.setText(" "+count++);
    }

    public void resetCount(View view) {
        count=0;
        countV.setText(" "+count);
    }

    public void exit_app(View view) {
        SharedPreferences prefs_name=getSharedPreferences("NAME_AND_COUNT",MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs_name.edit();
        editor.putString("username", nameInput);
        editor.putInt("count",count);
        editor.putInt("cond",1);
        editor.commit();

        finish();


    }

    public void submit(View view) {
        nameInput= name.getText().toString();
    }
}