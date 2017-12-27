package com.jude.beamdemo.ui;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.beamdome.R;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/12/26.
 */

public class MyMediaPlayerActivity extends BeamBaseActivity {
    @Bind(R.id.surfaceView)
    SurfaceView surfaceView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.numText)
    TextView numText;
    @Bind(R.id.image)
    ImageView image;
    private MediaPlayer player;
    private SurfaceHolder holder;
    private String[] permissions={Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    String uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymediaplayer);
        ButterKnife.bind(this);
        player = new MediaPlayer();
        holder = surfaceView.getHolder();
        holder.addCallback(new MyCallBack());
        uri=Environment.getExternalStorageDirectory().getPath()+"/DCIM/Camera/2.mp4";
        //String uri = Environment.getExternalStorageDirectory().getPath() + "/DCIM/photos/1.jpg";
        int i= ContextCompat.checkSelfPermission(this,permissions[0]);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && i!= PackageManager.PERMISSION_GRANTED) {
            requestPermissions(permissions,3);
        }else play();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int result:grantResults){
            if(result==PackageManager.PERMISSION_DENIED){
                return;
            }
        }
        play();
    }
    private void play(){
        try {
            player.setDataSource(this, Uri.parse(uri));
            player.prepare();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    progressBar.setVisibility(View.INVISIBLE);
                    player.start();
                    player.setLooping(true);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class MyCallBack implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            player.setDisplay(holder);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }
}
