package com.example.theotherlegacy.smarttictactoe;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import static android.preference.PreferenceManager.*;

public class MainActivity extends AppCompatActivity {

    static boolean robot =false;
    static String playerOne = "Player One";
    static String playerTwo = "Player Two";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button twop = (Button) findViewById(R.id.twobutton);
        Button cpu = (Button) findViewById(R.id.cpu);
        Button setting = (Button) findViewById(R.id.settingButton);
      //  ActionBar actionBar = getActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        twop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getDefaultSharedPreferences(MainActivity.this);
                playerOne= prefs.getString("example_text","");
                playerTwo= prefs.getString("example_text_2","");
                robot=false;
                Intent intent = new Intent(MainActivity.this, TwoPlayer.class);
                startActivity(intent);

            }
        });
        cpu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getDefaultSharedPreferences(MainActivity.this);
                playerTwo= prefs.getString("example_text_2","Player Two");
                playerOne= prefs.getString("example_text","Player One");

                robot=true;
                Intent intent = new Intent(MainActivity.this, TwoPlayer.class);
                startActivity(intent);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });



    }
    public static boolean getRobot(){
        return robot;
    }
    public static String getPlayerOne(){

        return playerOne;
    }
    public static String getPlayerTwo(){
        return playerTwo;
    }
    public static void setRobot(boolean gamemode){
        robot=gamemode;
    }

}
