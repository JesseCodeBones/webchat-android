package com.jesse.sample.networkchat.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionHelper {
    public static final void checkPermission(Activity context, String permission){
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(context, new String[]{permission}, 2);
        }
    }
}
