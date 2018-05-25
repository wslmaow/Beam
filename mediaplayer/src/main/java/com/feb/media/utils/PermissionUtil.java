package com.feb.media.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;

import com.feb.media.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/16.
 */

public class PermissionUtil {
    Context context;
    List<String> deniedPermissions;

    public PermissionUtil(Context context) {
        this.context = context;
    }
    private boolean[] noAskAnyMore(List<String> permissions){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return null;
        }
        boolean[] result = new boolean[permissions.size()];
        for (int i=0;i<permissions.size();i++){
            int hasPermission = ContextCompat.checkSelfPermission(context, permissions.get(i));
            if (hasPermission== PackageManager.PERMISSION_DENIED && !((Activity)context).shouldShowRequestPermissionRationale(permissions.get(i))){
                result[i] = true;
            }else{
                result[i] = false;
            }
        }

        return result;
    }

    private void showDialog(){
        /*new TDialog.Builder(((FragmentActivity)context).getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_seting_permissions)
                .setWidth(400)
                .setGravity(Gravity.CENTER)
                .addOnClickListener(R.id.dialog_cancel,R.id.dialog_ensure)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                        tDialog.dismiss();
                        switch (view.getId()){
                            case R.id.dialog_ensure :
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                                intent.setData(uri);
                                context.startActivity(intent);
                                break;
                        }
                    }
                })
                .create()
                .show();*/
    }



    public boolean hasPermission(String[] permissions){
        if (deniedPermissions!=null){
            deniedPermissions=null;
        }
        for (String permission:permissions){
            int result = ContextCompat.checkSelfPermission(context, permission);
            if (result==PackageManager.PERMISSION_DENIED){
                if (deniedPermissions==null){
                    deniedPermissions=new ArrayList<>();
                }
                deniedPermissions.add(permission);
            }
        }
        return deniedPermissions==null;
    }

    public void requestPermissions(String[] permissions){
        if (deniedPermissions==null)return;
        boolean[] askResult = noAskAnyMore(deniedPermissions);
        for (int i=0;i<askResult.length;i++){
            if (askResult[i]){
                showDialog();
                return;
            }else {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    ActivityCompat.requestPermissions((Activity)context,permissions, 3);
                }
            }
        }
    }

}
