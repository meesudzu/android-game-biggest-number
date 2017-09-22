package org.ikun.biggestnumber;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
/**
 * Game Project: Biggest Number
 * Author: Dzu
 * FB: http://fb.com/ultildark
 * Mail: meesudzu@gmail.com
 * images + sound from google.com :)
 * credit: stackoverflow
 */
public class MainActivity extends AppCompatActivity {
    RelativeLayout imgBG;
    MediaPlayer intro;
    MediaPlayer press;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgBG  = (RelativeLayout) findViewById(R.id.bg);
        imgBG.setBackgroundResource(R.drawable.background);//set background
        intro = MediaPlayer.create(MainActivity.this, R.raw.intro);//set intro sound
        press = MediaPlayer.create(MainActivity.this, R.raw.press);//set press sound
        intro.start();
        intro.setLooping(true);
    }
    //star function play game
    public void GoToHome (View v)
    {
        press.start();
        intro.stop();
        Intent home = new Intent(MainActivity.this, Home.class);
        startActivity(home);
    }
    //end function go to play game
    //start function open more info
    public void More (View v)
    {
        press.start();
        intro.stop();
        Intent home = new Intent(MainActivity.this, More.class);
        startActivity(home);
    }
    //end function open more
    //start function exit
    public  void  Exit (View v)
    {
        press.start();
        finish();
        intro.stop();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();
        press.start();
        intro.stop();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    //end function exit
    @Override
    protected void onResume()
    {
        intro.start();
        super.onResume();
    }
    @Override
    protected void onRestart()
    {
        intro.start();
        super.onRestart();
    }
    @Override
    protected void onPause() {
        intro.stop();
        super.onPause();
    }
    @Override
    protected void onStart()
    {
        intro.start();
        super.onStart();
    }
}
