package com.feb.media.ui;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.feb.media.R;
import com.jude.beam.expansion.BeamBaseActivity;
import com.timmy.tdialog.TDialog;

import java.io.IOException;
import java.security.Permission;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/26.
 */

public class MyMediaPlayerActivity extends BeamBaseActivity {

    enum media_player_states {
        MEDIA_PLAYER_STATE_ERROR,
        MEDIA_PLAYER_IDLE , // 1
        MEDIA_PLAYER_INITIALIZED, // 2
        MEDIA_PLAYER_PREPARING, // 4
        MEDIA_PLAYER_PREPARED, // 8
        MEDIA_PLAYER_STARTED, // 16
        MEDIA_PLAYER_PAUSED5, // 32
        MEDIA_PLAYER_STOPPED, // 64
        MEDIA_PLAYER_PLAYBACK_COMPLETE, // 128
    }


    @Bind(R.id.surfaceView)
    SurfaceView surfaceView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.btn1)
    TextView btn1;
    @Bind(R.id.btn2)
    TextView btn2;
    @Bind(R.id.btn3)
    TextView btn3;
    private MediaPlayer player;
    private SurfaceHolder holder;
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    String uri;
    media_player_states state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymediaplayer);
        ButterKnife.bind(this);
        initPlayer();
    }

    private void initPlayer() {
        player=new MediaPlayer();
        holder = surfaceView.getHolder();
        holder.addCallback(new MyCallBack());
        uri = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/2.mp4";
        //String uri = Environment.getExternalStorageDirectory().getPath() + "/DCIM/photos/1.jpg";

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //只点击拒绝按钮时，返回true；点击拒绝&不再询问、允许、允许&不再询问 时，都返回false
            boolean refused = shouldShowRequestPermissionRationale(permissions[0]);
        }
        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                //Toast.makeText(this,"请打开权限！",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivityForResult(intent, requestCode);
                return;
            }
        }
        play();
    }



    private boolean hasPermission(){
        for (String permission:permissions){
            int result = ContextCompat.checkSelfPermission(this, permission);
            if (result==PackageManager.PERMISSION_DENIED){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    ActivityCompat.requestPermissions(this,permissions, 3);
                    return false;
                }
            }
        }
        return true;
    }

    private void play() {
        try {
            player.setDataSource(this,Uri.parse(uri));
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                state=media_player_states.MEDIA_PLAYER_PREPARED;
                mp.start();
            }
        });
        player.setLooping(true);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                sendNotification();
                break;
            case R.id.btn2:
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(uri),"video/mp4");
                startActivity(intent);
                break;
            case R.id.btn3:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player!=null){
            player.release();
            player=null;
        }
    }


    private class MyCallBack implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            player.setDisplay(holder);
            if (hasPermission() ){
                if (state==media_player_states.MEDIA_PLAYER_STOPPED){
                    try {
                        player.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    player.start();
                    state=media_player_states.MEDIA_PLAYER_STARTED;
                }else {
                    play();
                }

            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (player.isPlaying()){
                player.stop();
                state=media_player_states.MEDIA_PLAYER_STOPPED;
            }
        }
    }

    private void sendNotification(){
        // 创建一个NotificationManager的引用
        NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        // 定义Notification的各种属性
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this.getApplicationContext())
                .setSound(android.provider.Settings.System.DEFAULT_NOTIFICATION_URI)        //
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))                                         //设置通知的图标
                .setTicker("有新消息呢")                                                     //设置状态栏的标题
                .setContentTitle("这个是标题")                                               //设置标题
                .setContentText("这个是内容")                                                //消息内容
                .setDefaults(Notification.DEFAULT_LIGHTS)                                      //设置默认的提示音
                .setOngoing(true)                                                          //让通知左右滑的时候不能取消通知
                .setPriority(Notification.PRIORITY_DEFAULT)                                 //设置该通知的优先级
                .setWhen(System.currentTimeMillis())                                        //设置通知时间，默认为系统发出通知的时间，通常不用设置
                .setAutoCancel(true)                                                        //打开程序后图标消失
                /*.setDefaults(Notification.DEFAULT_VIBRATE)*/
                ;
        //处理点击Notification的逻辑
        Intent resultIntent = new Intent(this, MyMediaPlayerActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);           //添加为栈顶Activity
        resultIntent.putExtra("what", 5);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 5, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        //发送
        Notification notification=mBuilder.build();
        notification.flags =Notification.FLAG_SHOW_LIGHTS;
        mNotificationManager.notify(1, notification);
    //结束广播
    //mNotificationManager.cancel(1);
    }
}
