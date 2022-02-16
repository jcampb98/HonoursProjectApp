package com.razormist.simpleregistrationandloginapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class Home extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public void onClick(View view) {

    }
    
    public void bootReceiver(Context context, Intent intent) {
        Intent i = new Intent(context, ScreenTimeBroadcastReceiver.class);
        context.startService(i);
    }
}
