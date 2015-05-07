package edu.washington.apache78.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity {

    String[] topics = new String[] { "Math", "Physics", "Marvel Super Heroes"};

    private ListView theList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        theList = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> items = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, topics);
        theList.setAdapter(items);

        theList.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent next = new Intent(MainActivity.this, MainActivity2.class);
                if(position==0) {
                    next.putExtra("TOPIC", "Math");
                }else if(position==1){
                    next.putExtra("TOPIC", "Physics");
                }else{
                    next.putExtra("TOPIC", "Marvel Super Heroes");
                }
                startActivity(next);
                finish();
            }
        });
    }
}
