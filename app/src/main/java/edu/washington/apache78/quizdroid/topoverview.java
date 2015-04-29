package edu.washington.apache78.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class topoverview extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topoverview);
        Intent launchingIntent = getIntent();
        TextView topic = (TextView) findViewById(R.id.topic);
        String itopic = launchingIntent.getStringExtra("TOPIC");
        topic.setText(itopic);
        TextView description = (TextView) findViewById(R.id.desciption);
        getDescription(itopic);
        Button start = (Button) findViewById(R.id.btnStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(topoverview.this, MathQuestion1.class);
                startActivity(next);
                finish();
            }
        });
    }

    public void getDescription(String topic){
        TextView description = (TextView) findViewById(R.id.desciption);
        if(topic.equalsIgnoreCase("math")){
            description.setText(R.string.math_desciption);
        }else if(topic.equalsIgnoreCase("physics")){
            description.setText(R.string.physics_desciption);
        }else{
            description.setText(R.string.smh_description);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topoverview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
