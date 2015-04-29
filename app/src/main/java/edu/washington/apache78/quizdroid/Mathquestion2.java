package edu.washington.apache78.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class Mathquestion2 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathquestion2);

        Intent launchingIntent = getIntent();
        final Button submit = (Button) findViewById(R.id.btnSubmit);
        final Intent next = new Intent(Mathquestion2.this, answerpage.class);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.choices);
        next.putExtra("LAST", true);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                RadioButton selected = (RadioButton) findViewById(checkedId);
                String gave = selected.getText().toString();
                next.putExtra("GAVE", gave);
                submit.setVisibility(View.VISIBLE);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton right = (RadioButton) findViewById(R.id.right);
                String answer = right.getText().toString();
                next.putExtra("ANSWER",answer);
                startActivity(next);
                finish();
            }
        });


    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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