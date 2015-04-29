package edu.washington.apache78.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class answerpage extends ActionBarActivity {
    private int correct=0;
    private int total=0;
    private static final String TAG = "FIX";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answerpage);
        Intent launchingIntent = getIntent();
        String gave = launchingIntent.getStringExtra("GAVE");
        String answer = launchingIntent.getStringExtra("ANSWER");
        TextView givenAnswer = (TextView) findViewById(R.id.given);
        givenAnswer.append(gave);
        TextView rightAnswer = (TextView) findViewById(R.id.correct);
        rightAnswer.append(answer);
        if(answer.equalsIgnoreCase(gave)){
            correct++;
        }
        total++;
        TextView correctsofar = (TextView) findViewById(R.id.textView4);
        correctsofar.append(correct + " out of " + total + " correct!");
        Boolean last = launchingIntent.getBooleanExtra("LAST", false);
        Button nextbtn = (Button) findViewById(R.id.btnNext);
        if(last){
            Button done = (Button) findViewById(R.id.btndone);
            nextbtn.setVisibility(View.GONE);
            done.setVisibility(View.VISIBLE);
            done.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent next = new Intent(answerpage.this, MainActivity.class);
                    startActivity(next);
                    finish();
                }
            });

        }else {
            nextbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent next = new Intent(answerpage.this, Mathquestion2.class);
                    startActivity(next);

                }
            });
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceSate) {

        super.onSaveInstanceState(savedInstanceSate);

        savedInstanceSate.putInt("correct", correct);
        savedInstanceSate.putInt("total", total);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        correct = savedInstanceState.getInt("correct");
        total = savedInstanceState.getInt("total");
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_answerpage, menu);
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
