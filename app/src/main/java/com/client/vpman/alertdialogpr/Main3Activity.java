package com.client.vpman.alertdialogpr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.VideoView;

public class Main3Activity extends AppCompatActivity {

    VideoView videoView;
    ImageView imageView;
    SeekBar seekBar;
    String str_video_url;
    Handler handler;
    boolean isPlay=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        videoView=findViewById(R.id.videoView);
        imageView=findViewById(R.id.toggleButton);
        seekBar=findViewById(R.id.seekBar);

        str_video_url=getIntent().getStringExtra("video");
        videoView.setVideoPath(str_video_url);
        handler=new Handler();
        videoView.start();
        isPlay=true;
        imageView.setImageResource(R.drawable.pausebutton);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlay)
                {
                    videoView.pause();
                    isPlay=false;
                    imageView.setImageResource(R.drawable.playbutton);
                }
                else if (isPlay==false)
                {
                    videoView.start();
                    UpdateSeekBar();
                    isPlay=true;
                    imageView.setImageResource(R.drawable.pausebutton);
                }
            }
        });
        UpdateSeekBar();
    }
    public  void UpdateSeekBar()
    {
        handler.postDelayed(updateTimeTask,100);
    }
    public Runnable updateTimeTask=new Runnable() {
        @Override
        public void run() {
            seekBar.setProgress(videoView.getCurrentPosition());
            seekBar.setMax(videoView.getDuration());
            handler.postDelayed(this,100);

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    handler.removeCallbacks(updateTimeTask);
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    handler.removeCallbacks(updateTimeTask);
                    videoView.seekTo(seekBar.getProgress());
                    UpdateSeekBar();
                }
            });
        }
    };
}
