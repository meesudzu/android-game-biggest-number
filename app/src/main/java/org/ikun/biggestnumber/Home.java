package org.ikun.biggestnumber;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static android.view.View.OnClickListener;
import static java.lang.String.valueOf;

/**
 * Game Project: Biggest Number
 * Author: Dzu
 * FB: http://fb.com/ultildark
 * Mail: meesudzu@gmail.com
 * images + sound from google.com :)
 * credit: stackoverflow
 */

public class Home extends AppCompatActivity {
    RelativeLayout bgHome;
    FrameLayout numberLayout;
    Random r = new Random();//create random
    TextView tvCoin, tvKing;
    ProgressBar progressBar;
    boolean isOver = false;//check game over
    int ran, max, rangenum,lv=1, coin=0, count;//random number,max number,range number button,level,coin,count process
    Timer timer;
    SharedPreferences saveData;
    int king= 0;
    MediaPlayer intro,success;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bgHome = (RelativeLayout) findViewById(R.id.backgroundHome);
        bgHome.setBackgroundResource(R.drawable.background);
        numberLayout = (FrameLayout) findViewById(R.id.LayoutN);
        tvCoin = (TextView) findViewById(R.id.textViewCoin);
        tvKing = (TextView) findViewById(R.id.textViewKing);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        intro = MediaPlayer.create(Home.this, R.raw.intro);//set intro sound
        success = MediaPlayer.create(Home.this, R.raw.success);//set success sound
        intro.start();
        intro.setLooping(true);
        saveData = getSharedPreferences ("data_game", Context.MODE_PRIVATE);//creat save data king coin
        FGetData();//open get data king coin when app run
        tvKing.setText(String.valueOf(king));//set king coin
        progressBar.setProgress(0);
        progressBar.setMax(100);
        CheckLV();
    }
    //start function save data king coin
    private void FSaveData(int MaxCoin)
    {
        SharedPreferences.Editor edit = saveData.edit();
        edit.putInt("maxCoin",MaxCoin);//save data to maxCoin
        edit.commit();
    }
    //end function save data king coin
    //start function get data king coin
    private void FGetData()
    {
        king = saveData.getInt("maxCoin",1);//get data from maxCoin, if null set to 1
    }
    //end function get data
    //start function cowndown time
    public void CDTime(int Speed)
    {
        count = 0;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                count=  count + 10;
                progressBar.setProgress(count);//set percent processBar == count
                if(count==100 && !isOver)
                    GamerOver();//if count max & not Game Over, go to Game OVer
            }
        },1,Speed);//speed coundown time
    }
    //end function cowndown time
    //start function check level to create button
    public void CheckLV()
    {
        isOver = false;
        switch (lv)
        {
            case 1:
                CreatLV(2);
                CDTime(500);
                break;
            case 2:
                CreatLV(2);
                CDTime(500);//if level 1,2 create 2 button & and speed = 500 miliseconds
                break;
            case 3:
                CreatLV(3);
                CDTime(500);
                break;
            case 4:
                CreatLV(3);
                CDTime(500);
                break;
            case 5:
                CreatLV(3);
                CDTime(500);
                break;
            case 6:
                CreatLV(3);
                CDTime(500);//if level 3,4,5,6 create 2 button & and speed = 500 miliseconds
                break;
            case 7:
                CreatLV(5);
                CDTime(700);
                break;
            case 8:
                CreatLV(5);
                CDTime(700);
                break;
            case 9:
                CreatLV(5);
                CDTime(700);//if level 7,8,9 create 5 button & and speed = 700 miliseconds
                break;
            default:
                CreatLV(9);
                CDTime(1000-lv);//if level 9+ create 9 button & and speed = 1000 miliseconds - level
                break;
        }
    }
    //end function check level
    //start function creat level
    public void CreatLV(int So) {
        max = 0;//set max number = 0
        rangenum = 1;//set range number  = 1
        for (int i = 0; i < So; i++) {
            ran = r.nextInt(100);
            if (ran >= max) {
                max = ran;
            }
            //if random number > max number, set it to max
            String num;//creat text to set in button button
            if(ran<10) {
                num = valueOf("0"+ran);
            } else {
                num = valueOf(ran);
            }
            //set text = random number, if <10, + "0" to text
            Button b = new Button(this);
            CreateButton(b, num);//creat button have text is random number
            rangenum++;
        }
        lv++;
    }
    //end function create level

    @TargetApi(Build.VERSION_CODES.M)
    //start function create button
    public void CreateButton(Button NewButton, final String Num) {
        NewButton = new Button(this);
        NewButton.setText(Num);//set text
        NewButton.setBackgroundResource(R.drawable.button_bg);//set background is button
        NewButton.setTextColor(Color.parseColor("white"));//set color of button
        NewButton.setTextSize(30);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
        );
        if (rangenum == 1) {
            params.gravity = (Gravity.LEFT | Gravity.CENTER_VERTICAL);
            numberLayout.addView(NewButton, params);
        }
        if (rangenum == 3) {
            params.gravity = Gravity.CENTER;
            numberLayout.addView(NewButton, params);
        }
        if (rangenum == 2) {
            params.gravity = (Gravity.RIGHT | Gravity.CENTER_VERTICAL);
            numberLayout.addView(NewButton, params);
        }
        if (rangenum == 4) {
            params.gravity = (Gravity.TOP | Gravity.CENTER_HORIZONTAL);
            numberLayout.addView(NewButton, params);
        }
        if (rangenum == 5) {
            params.gravity = (Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
            numberLayout.addView(NewButton, params);
        }
        if (rangenum == 6) {
            params.gravity = (Gravity.TOP | Gravity.LEFT);
            numberLayout.addView(NewButton, params);
        }
        if (rangenum == 7) {
            params.gravity = (Gravity.TOP | Gravity.RIGHT);
            numberLayout.addView(NewButton, params);
        }
        if (rangenum == 8) {
            params.gravity = (Gravity.BOTTOM | Gravity.LEFT);
            numberLayout.addView(NewButton, params);
        }
        if (rangenum == 9) {
            params.gravity = (Gravity.BOTTOM | Gravity.RIGHT);
            numberLayout.addView(NewButton, params);
        }
        //check range number and add button to there garavity in framelayout
        NewButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int chose = Integer.parseInt(Num);
                if (chose == max) {
                    success.start();
                    coin += 10;
                    tvCoin.setText(String.valueOf(coin));
                    timer.cancel();
                    numberLayout.removeAllViews();
                    if(coin>king)
                    {
                        king=coin;
                        FSaveData(king);
                    }
                    CheckLV();
                    //check button click, if = max number start success sound, + coin, cancel cowndown time, and save coin, then next level
                } else {
                    isOver = true;
                    intro.stop();
                    GamerOver();
                }
                //else game over and stop intro sound, set isOver = true to stop cowndown time
            }
        });
    }
    //end function create button
    //start function if game over
    public void GamerOver ()
    {
        finish();//finish this activity
        String scoin = tvCoin.getText().toString();
        Intent gover = new Intent(Home.this, Lost.class);
        gover.putExtra("score",scoin);
        startActivity(gover);
        //open game over activity send user coin
    }
    //end function game over
    @Override
    public void onBackPressed() {
        timer.cancel();
        intro.stop();
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    //if press back button, pause game and go to home screen device
    @Override
    protected void onPause() {
        isOver = true;
        intro.stop();
        super.onPause();
    }
    //if game pause, stop intro sound
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
    protected void onStart()
    {
        intro.start();
        super.onStart();
    }
}
