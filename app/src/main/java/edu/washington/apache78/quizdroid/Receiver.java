package edu.washington.apache78.quizdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by apache78 on 5/24/2015.
 */
public class Receiver extends BroadcastReceiver{

    public Receiver(){}

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String url = prefs.getString("urlPreference", "http://tednewardsandbox.site44.com/questions.json");

        Intent serv = new Intent (context, DownloadService.class);
        serv.putExtra("URL", url);
        context.startService(serv);
    }

}
