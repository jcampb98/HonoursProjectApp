package com.razormist.simpleregistrationandloginapplication;

import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.usage.UsageStatsManager;

import static android.app.AppOpsManager.MODE_ALLOWED;
import static android.os.Process.myUid;


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class Home extends AppCompatActivity implements View.OnClickListener {
    Button logout;
    TextView permissionMessage;
    private final Context context;

    private UsageStatsManager usageStatsManager;

    public Home(Context context) {
        this.context = context;
        usageStatsManager = (UsageStatsManager)
                context.getSystemService(Context.USAGE_STATS_SERVICE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logout = (Button) findViewById(R.id.buttonLogout);
        permissionMessage = (TextView) findViewById(R.id.grant_permission_message);

        permissionMessage.setOnClickListener(v -> openSettings());
    }

    public void onClick(View v) {
        //Checks for exit button
        if(v == logout){
            showtbDialog();
        }
    }

    private void showtbDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Logout??");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id)
            {
                Toast.makeText(getApplicationContext(), "You Pressed Yes", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id)
            {
                Toast.makeText(getApplicationContext(), "You Pressed No", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void openSettings() {
        startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
    }

    private boolean checkForPermission(Context context) {
        AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, myUid(), context.getPackageName());

        return mode == MODE_ALLOWED;
    }
}
