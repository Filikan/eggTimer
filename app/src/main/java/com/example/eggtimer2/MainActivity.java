package com.example.eggtimer2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    SeekBar sb;
    TextView tv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sb = (SeekBar) findViewById(R.id.timerSeekBar);
        tv = (TextView) findViewById(R.id.tvTimer);
        btn = (Button) findViewById(R.id.button);

        sb.setMax(600);
        sb.setProgress(30);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                String second;
                int minute;

                second = ""+(i%60);
                minute = i/60;

                if (i%60<10)
                {
                    second = "0"+second;
                }

                String timestr = ""+minute+":"+second;
                tv.setText(timestr);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    CountDownTimer cdt;

    public void start(View v){
        sb.setEnabled(false);
        cdt = new CountDownTimer(sb.getProgress()*1000,1000) {
            @Override
            public void onTick(long l) {
                sb.setProgress(sb.getProgress()-1);
                btn.setEnabled(false);
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this,"Time is over!",Toast.LENGTH_LONG).show();
                sb.setEnabled(true);
                btn.setEnabled(true);

                int resID = getResources().getIdentifier("horn.wav","raw",getPackageName());

                MediaPlayer mp = MediaPlayer.create(MainActivity.this,R.raw.horn);
                mp.start();
            }
        };
        cdt.start();
    }
}