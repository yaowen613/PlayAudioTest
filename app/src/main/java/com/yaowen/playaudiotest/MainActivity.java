package com.yaowen.playaudiotest;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button play,pause,stop;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play= (Button) findViewById(R.id.play);
        pause= (Button) findViewById(R.id.pause);
        stop= (Button) findViewById(R.id.stop);
        mediaPlayer=new MediaPlayer();

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
        initMediaPlayer();//初始化MediaPlayer

    }

    private void initMediaPlayer() {

        try {
            File file=new File(Environment.getExternalStorageDirectory(),"music.mp3");
            mediaPlayer.setDataSource(file.getPath());// 指定⾳频⽂件的路径
            mediaPlayer.prepare();// 让MediaPlayer进⼊到准备状态
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:{
                if (!mediaPlayer.isPlaying()){
                    mediaPlayer.start();// 开始播放
                }
                break;
            }
            case R.id.pause:{
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();// 暂停播放
                }
                break;
            }
            case R.id.stop:{
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.reset();// 停⽌播放
                    initMediaPlayer();
                }
                break;
            }
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
