package org.ikun.biggestnumber;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class Lost extends AppCompatActivity {

    RelativeLayout imgBG;
    Button btnShare;
    Button btnScore;
    String score;
    MediaPlayer endGame, press, capture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);
        imgBG  = (RelativeLayout) findViewById(R.id.bg);
        imgBG.setBackgroundResource(R.drawable.background);
        btnScore = (Button) findViewById(R.id.ButtonScore);
        endGame = MediaPlayer.create(Lost.this, R.raw.gover);//set game over intro sound
        press = MediaPlayer.create(Lost.this, R.raw.press);//set press sound
        capture = MediaPlayer.create(Lost.this, R.raw.capture);//set capture sounf
        endGame.start();
        Bundle bd = getIntent().getExtras();
        if(bd!=null)
        {
            score = bd.getString("score");
            btnScore.setText(score);
        }
        //if score send from home activity not null, set it to score and show it
        View content = findViewById(R.id.bg);
        content.setDrawingCacheEnabled(true);
        btnShare = (Button) findViewById(R.id.ButtonShare);
        btnShare.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                capture.start();
                getScreen();
            }
        });
        //if button share was click, start capture sound and capture screen device
    }
    //start function play again
    public void GoToHome (View v)
    {
        finish();
        press.start();
        endGame.stop();
        Intent home = new Intent(Lost.this, Home.class);
        startActivity(home);
    }
    //end function play again
    //start function exit
    public  void  Exit (View v)
    {
        finish();
        endGame.stop();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        System.exit(0);
    }
    //end function exit
    @Override
    public void onBackPressed() {
        press.start();
        Toast.makeText(Lost.this, "Dont Press Back Button! :)", Toast.LENGTH_SHORT).show();
    }
    //lock back press
    @Override
    public void onResume() {
        endGame.start();
        Toast.makeText(Lost.this,"GameOver", Toast.LENGTH_SHORT).show();
        super.onResume();
    }
    //show toast "game over"
    //start function capture screen
    private void getScreen()
    {
        View content = findViewById(R.id.bg);
        Bitmap bitmap = content.getDrawingCache();
        File file = new File( Environment.getExternalStorageDirectory() + "/" + score + "-BiggestNumber.png");
        try
        {
            file.createNewFile();
            FileOutputStream ostream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, ostream);
            ostream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        openScreenshot(file);
    }
    //end function capture screen
    //start function show screen shot
    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }
    //end function
    @Override
    protected void onRestart()
    {
        endGame.start();
        super.onRestart();
    }
    @Override
    protected void onStart()
    {
        endGame.start();
        super.onStart();
    }
    @Override
    protected void onPause() {
        endGame.stop();
        super.onPause();
    }
}
