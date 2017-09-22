package org.ikun.biggestnumber;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class More extends AppCompatActivity {

    RelativeLayout imgBG;
    Button btnBack;
    MediaPlayer intro;
    MediaPlayer press;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        imgBG = (RelativeLayout) findViewById(R.id.bg);
        imgBG.setBackgroundResource(R.drawable.background);
        btnBack = (Button) findViewById(R.id.buttonBack);
        intro = MediaPlayer.create(More.this, R.raw.intro);
        press = MediaPlayer.create(More.this, R.raw.press);
        intro.start();
        intro.setLooping(true);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Back();
            }
        });
    }
    public void onBackPressed() {
        press.start();
        Toast.makeText(More.this, "Dont Press Back Button! :)", Toast.LENGTH_SHORT).show();
    }
    public void Back ()
    {
        finish();
        press.start();
        intro.stop();
        Intent home = new Intent(More.this, MainActivity.class);
        startActivity(home);
    }
    @Override
    protected void onPause() {
        intro.stop();
        super.onPause();
    }
    @Override
    public void onResume() {
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
    protected void onStart()
    {
        intro.start();
        super.onStart();
    }
}
