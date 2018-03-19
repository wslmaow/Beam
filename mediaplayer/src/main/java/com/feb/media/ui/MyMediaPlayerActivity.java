package com.feb.media.ui;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.feb.media.R;
import com.feb.media.utils.PermissionUtil;
import com.feb.media.utils.ThreadPoolUtil;
import com.feb.media.utils.TimeUtil;
import com.jude.beam.expansion.BeamBaseActivity;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/26.
 */

public class MyMediaPlayerActivity extends BeamBaseActivity {

    @Bind(R.id.tv_currentTime)
    TextView tvCurrentTime;
    @Bind(R.id.pause)
    TextView pause;
    @Bind(R.id.tv_totalTime)
    TextView tvTotalTime;
    @Bind(R.id.layout_control)
    LinearLayout layoutControl;

    enum media_player_states {
        MEDIA_PLAYER_STATE_ERROR,
        MEDIA_PLAYER_IDLE, // 1
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
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.INTERNET};
    String uri;
    media_player_states state;
    PermissionUtil permissionUtil;
    Handler handler;
    ThreadPoolExecutor threadPoolExecutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymediaplayer);
        ButterKnife.bind(this);
        initParams();
        initPlayer();
    }

    private void initParams(){
        permissionUtil = new PermissionUtil(this);
        handler = new Handler(getMainLooper());
        threadPoolExecutor = ThreadPoolUtil.getThreadPoolExecutor();
        holder = surfaceView.getHolder();
        holder.addCallback(new MyCallBack());
        uri = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/2.mp4";
        uri = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
        //String uri = Environment.getExternalStorageDirectory().getPath() + "/DCIM/photos/1.jpg";
    }

    private void initPlayer() {
        player = new MediaPlayer();
        player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                progressBar.setSecondaryProgress(i*mediaPlayer.getDuration());
            }
        });
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                state = media_player_states.MEDIA_PLAYER_PREPARED;
                initSettings();
                mp.start();
            }
        });
        player.setLooping(true);
        try {
            player.setDataSource(this, Uri.parse(uri));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "请打开权限！", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        try {
            player.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initSettings() {
        progressBar.setMax(player.getDuration());
        tvTotalTime.setText(TimeUtil.getTimeFromInt(player.getDuration()));
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                while (player!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(player==null)return;
                            tvCurrentTime.setText(TimeUtil.getTimeFromInt(player.getCurrentPosition()));
                            progressBar.setProgress(player.getCurrentPosition());
                        }
                    });
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        sendNotification();
                    }
                }
            }
        });
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3,R.id.pause})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                sendNotification();
                break;
            case R.id.btn2:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(uri), "video/mp4");
                startActivity(intent);
                break;
            case R.id.btn3:
                break;
            case R.id.pause:
                if (player.isPlaying()){
                    player.pause();
                    pause.setText("| >");
                }else {
                    player.start();
                    pause.setText("| |");
                }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release();
            player = null;
            threadPoolExecutor.shutdownNow();
        }
    }


    private class MyCallBack implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            player.setDisplay(holder);
            if (permissionUtil.hasPermission(permissions)) {
                //if (state == media_player_states.MEDIA_PLAYER_STOPPED) {
                    player.prepareAsync();
                //}
            } else {
                permissionUtil.requestPermissions(permissions);
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (player.isPlaying()) {
                player.stop();
                state = media_player_states.MEDIA_PLAYER_STOPPED;
            }
        }
    }

    private void sendNotification() {
        // 创建一个NotificationManager的引用
        NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        // 定义Notification的各种属性
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this.getApplicationContext())
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)        //
                .setSmallIcon(R.mipmap.ic_launcher)                                         //设置通知的图标
                .setTicker("有新消息呢")                                                     //设置状态栏的标题
                .setContentTitle("这个是标题")                                               //设置标题
                .setContentText("这个是内容")                                                //消息内容
                .setDefaults(Notification.DEFAULT_LIGHTS)                                      //设置默认的提示音
                .setOngoing(true)                                                          //让通知左右滑的时候不能取消通知
                .setPriority(Notification.PRIORITY_DEFAULT)                                 //设置该通知的优先级
                .setWhen(System.currentTimeMillis())                                        //设置通知时间，默认为系统发出通知的时间，通常不用设置
                .setAutoCancel(true)                                                        //打开程序后图标消失
                /*.setDefaults(Notification.DEFAULT_VIBRATE)*/;
        //处理点击Notification的逻辑
        Intent resultIntent = new Intent(this, MyMediaPlayerActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);           //添加为栈顶Activity
        resultIntent.putExtra("what", 5);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 5, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        //发送
        Notification notification = mBuilder.build();
        notification.flags = Notification.FLAG_SHOW_LIGHTS;
        mNotificationManager.notify(1, notification);
        //结束广播
        //mNotificationManager.cancel(1);
    }
}
