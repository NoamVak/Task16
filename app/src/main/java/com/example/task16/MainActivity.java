package com.example.task16;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 *
 * @author Noam Vaknin <noamvak765@gmail.com>
 * @version 1.6 (current version number of program) - I don't know exactly what to do here
 * @since 21 /8/2016 (the date of the package the class was added)
 */
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
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        String choice=item.getTitle().toString();
        if(choice.equals("NextPrevAct")){
            Intent res=new Intent(this,CreditAct.class);
            startActivity(res);
        }

        return true;
    }



    private void RetToApp() {
        SharedPreferences prefs_name=getSharedPreferences("NAME_AND_COUNT",MODE_PRIVATE);
        if(prefs_name.getInt("cond",0)==1){
            count=prefs_name.getInt("count",-1);
            countV.setText(" "+ count);
            name.setText(prefs_name.getString("username","none"));
        }
    }

    /**
     * Increase count.
     *
     * @param view the view
     */
    public void increaseCount(View view) {
        countV.setText(" "+count++);
    }

    /**
     * Reset count.
     *
     * @param view the view
     */
    public void resetCount(View view) {
        count=0;
        countV.setText(" "+count);
    }

    /**
     * Exit app. - exits the app and saves in SharedPreferences the values of current app
     *
     * @param view the view
     */
    public void exit_app(View view) {
        SharedPreferences prefs_name=getSharedPreferences("NAME_AND_COUNT",MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs_name.edit();
        editor.putString("username", nameInput);
        editor.putInt("count",count);
        editor.putInt("cond",1);
        editor.commit();

        finish();


    }

    /**
     * Submit. - an On Click method which submits the edit Text input
     *
     * @param view the view
     */
    public void submit(View view) {
        nameInput= name.getText().toString();
    }
}