package com.phonegap.plugins.nativesettings;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.content.Context;
import android.net.Uri;

import android.provider.Settings;

import android.os.Build;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;

public class NativeSettings extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		Context context = this.cordova.getActivity().getApplicationContext();
        PluginResult.Status status = PluginResult.Status.OK;
        String result = "";

        Intent intent = new Intent();

        if(android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1){
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", context.getPackageName());
        } else {
            if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                intent.putExtra("app_package", context.getPackageName());
                intent.putExtra("app_uid", context.getApplicationInfo().uid);
            } else {
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + context.getPackageName()));
            }
        }

        try {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.cordova.getActivity().startActivity(intent);
            callbackContext.sendPluginResult(new PluginResult(status, result));
            result = "Opened";
        } finally {
            status = PluginResult.Status.INVALID_ACTION;
            callbackContext.sendPluginResult(new PluginResult(status, result));
            result = "Cannot open";
        }

        if(result == "Opened"){
            return true;
        }else{
            return false;
        }
    }

}

