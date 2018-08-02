package com.namkit.namki.musicplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button button;
    SeekBar seekbar;
    MediaPlayer music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        music = MediaPlayer.create(this, R.raw.darling);
        music.setLooping(false);

        button = (Button) findViewById(R.id.button1);
        seekbar = (SeekBar) findViewById(R.id.seekBar1);

        seekbar.setMax(music.getDuration()); //get으로 음악의 총길이가져온다

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {//사용자가 건들면 true
                // TODO Auto-generated method stub
                if (fromUser) //사용자가 seek움직일때만 실행
                    music.seekTo(progress); //seekTo로 재생 위치 변경
            }
        });
    }
        public void button(View v){
            if(music.isPlaying()){ //재생중이면 true반환 - 재생중이면 정지 재생중 아니면 재생
// 재생중이면 실행될 작업 (정지)
                music.stop();
                try {
                    music.prepare();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                music.seekTo(0);
                button.setText(R.string.start);
                seekbar.setProgress(0);
            }else{
// 재생중이 아니면 실행될 작업 (재생)
                music.start();
                button.setText(R.string.stop);
                Thread();

            }
        }
    public void Thread(){
        Runnable task = new Runnable(){
            public void run(){ //1초마다 SeekBar의 재생위치가 옮겨진다
                /**

                 * while문을 돌려서 음악이 실행중일때 게속 돌아가게 합니다

                 */
                while(music.isPlaying()){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    /**
                     * music.getCurrentPosition()은 현재 음악 재생 위치를 가져오는 구문 입니다
                     */
                    seekbar.setProgress(music.getCurrentPosition()); //재생중인 위치 파악
                }
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }
}

