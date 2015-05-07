package edu.washington.apache78.quizdroid;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;



public class MainActivity2 extends FragmentActivity
        implements topicoverview.OnFragmentInteractionListener, answer.OnFragmentInteractionListener,question.OnFragmentInteractionListener {

    private int correct;
    private int total;
    private int currentquestion;
    private String topic;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity22);
        correct=0;
        total=0;

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            topicoverview first = new topicoverview();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            first.setArguments(getIntent().getExtras());


            // Add the fragment to the 'fragment_container' FrameLayout
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, first)
                    .commit();
        }
    }

    public void startQuiz(String topic){
        currentquestion=1;
        question fragment = new question();
        Bundle args = new Bundle();
        this.topic=topic;
        args.putInt("CURRENT", currentquestion);
        args.putString("TOPIC", topic);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                //.addToBackStack(null)
                .commit();
    }


    @Override
    public void answered(String given, String answer) {
        currentquestion++;
        total++;
        answer fragment = new answer();
        Bundle args= new Bundle();
        args.putString("GIVEN", given);
        args.putString("ANSWER", answer);
        checkCorrect(given, answer);
        args.putInt("CORRECT", correct);
        args.putInt("TOTAL", total);

        if(currentquestion == 6){
            args.putBoolean("LAST", true);
        }else {
            args.putBoolean("LAST", false);
        }
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.fragment_container, fragment)
//                .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right)
//                                //.addToBackStack("ques")
//                .commit();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);
        transaction.replace(R.id.fragment_container, fragment).commit();
    }

    public void checkCorrect(String given, String answer){
        if(answer.equalsIgnoreCase(given)){
            correct++;
        }
    }

    @Override
    public void onFragmentInteraction(String s) {

    }

    @Override
    public void onNextQuestion(String s) {
        question fragment = new question();
        Bundle args = new Bundle();
        args.putInt("CURRENT", currentquestion);
        args.putString("TOPIC", topic);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                        //.addToBackStack(null)
                .commit();
    }

    @Override
    public void onFinish(String s) {
        Intent next = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(next);
        finish();
    }
}