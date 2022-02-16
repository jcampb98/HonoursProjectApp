package com.razormist.simpleregistrationandloginapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ScreenTimeBroadcastReceiver extends BroadcastReceiver {
    private long startTimer, endTimer, screenOnTime, screenOnTimeSingle;
    private final long TIME_ERROR = 1000;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("[BroadcastReceiver]", "MyReceiver");

        if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
            startTimer = System.currentTimeMillis();
        }

        else if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            endTimer = System.currentTimeMillis();
            screenOnTime = endTimer - startTimer;

            if(screenOnTime < TIME_ERROR) {
                screenOnTime += screenOnTimeSingle;
            }
        }
    }
}
