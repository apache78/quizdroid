package edu.washington.apache78.quizdroid;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends ActionBarActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    String[] topics = new String[] { "Math", "Physics", "Marvel Super Heroes"};

    private ListView theList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);
        QuizApp myApp = (QuizApp) getApplication();
        ITopicRepository repo = myApp.getRepo();
        List<Topic> repotopics = repo.getAllTopics();
            topics = new String[repotopics.size()];
            for(int i = 0 ; i< repotopics.size(); i++){
            topics[i] = repotopics.get(i).getTitle();
        }



        theList = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> items = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, topics);
        theList.setAdapter(items);






        theList.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent next = new Intent(MainActivity.this, MainActivity2.class);
                if (position == 0) {
                    next.putExtra("TOPIC", topics[0]);
                    next.putExtra("INDEX", 0);
                } else if (position == 1) {
                    next.putExtra("TOPIC", topics[1]);
                    next.putExtra("INDEX", 1);
                } else {
                    next.putExtra("TOPIC", topics[2]);
                    next.putExtra("INDEX", 2);
                }
                startActivity(next);
                finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent prefs = new Intent(MainActivity.this, PreferencesActivity.class);
                startActivity(prefs);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals("intervalPreference") && !sharedPreferences.getString("urlPreference", "").equals("")){
            Intent alarmIntent = new Intent(this, Receiver.class);

            int interval = Integer.parseInt(sharedPreferences.getString(key, "10"));
            int time = interval *1000 *60*60;
            String url = sharedPreferences.getString("urlPreference", "reddit.com");
            PendingIntent pendingIntent =PendingIntent.getBroadcast(this,0,alarmIntent,0);
            Toast toast = Toast.makeText(MainActivity.this, "downloading from: " + url +" every " +interval + " hours" , Toast.LENGTH_LONG);
            toast.show();
            ((AlarmManager) getSystemService(ALARM_SERVICE)).setInexactRepeating(AlarmManager.RTC, 0, time, pendingIntent);
        }
    }
}
