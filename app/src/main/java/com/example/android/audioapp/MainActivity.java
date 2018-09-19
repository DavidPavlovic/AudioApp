package com.example.android.audioapp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button startBtn, pauseBtn, forwardBtn, stopBtn;
    private MediaPlayer mediaPlayer;

    private double startTime = 0;
    private double finalTime = 0;

    private int forwardTime = 5000;

    public static int oneTimeOnly;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.start_btn);
        pauseBtn = findViewById(R.id.pause_btn);
        forwardBtn = findViewById(R.id.forward_btn);
        stopBtn = findViewById(R.id.stop_btn);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.rompasso);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();

                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        Toast.makeText(MainActivity.this, "Song ended.", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
            }
        });

        forwardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = (int)startTime;

                if((temp + startTime) <= finalTime) {
                    startTime += forwardTime;
                    mediaPlayer.seekTo((int)startTime);
                    Toast.makeText(MainActivity.this, "Forward 5 seconds", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Cannot fast forward", Toast.LENGTH_SHORT).show();
                }
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.rompasso);
            }
        });

    }

}
