package edu.washington.apache78.quizdroid;

import android.app.Application;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by apache78 on 5/11/2015.
 */
public class QuizApp extends Application{
    public static String topic;
    public int current;
    public List<Topic> topics;
    private static QuizApp instance = null;
    public List<Question> questions;
    //HashMap<String, JSONObject> questions;


    private ITopicRepository repo;

    /* Protect MyApp at runtime */
    public QuizApp() {
        if (instance == null) {
            instance = this;
        } else {
            throw new RuntimeException("Cannot create more than one MyApp");
        }
    }
    public static QuizApp getInstance(){
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("QUIZAPP", "QuizApp running");
        this.questions = new ArrayList<>();
        topics = new ArrayList<>();
        String json = null;
        try {
            InputStream inputStream = getAssets().open("questions.json");
            json = readJSONFile(inputStream);
            JSONArray jsonTopics = new JSONArray(json);

            for(int i = 0; i<jsonTopics.length(); i++){
                Log.i("TOPIC", jsonTopics.get(i).toString());
                JSONObject cur = jsonTopics.getJSONObject(i);
                Log.i("QUIZAPP", ""+cur);
                Topic jsonTopic = new Topic();
                jsonTopic.setTitle(cur.getString("title"));
                Log.i("QUIZAPP", "" + cur.getString("title"));
                jsonTopic.setDescription(cur.getString("desc"));
                Log.i("QUIZAPP", "" + cur.getString("desc"));
                JSONArray jsonQuestions = cur.getJSONArray("questions");
                List<Question> qs = new ArrayList<>();;
                for(int j =0; j<jsonQuestions.length();j++){

                    JSONObject jsonQuestion = jsonQuestions.getJSONObject(j);
                    String qText = jsonQuestion.getString("text");
                    JSONArray temp = jsonQuestion.getJSONArray("answers");
                    int tempLength = temp.length();
                    String[] qAnswers = new String[tempLength];
                    if(tempLength>0){
                        for(int k=0;k<tempLength;k++){
                            qAnswers[k] = temp.getString(k);
                        }
                    }

                    int correctAnswer = jsonQuestion.getInt("answer");
                    Question newQuestion = new Question(qText,qAnswers,correctAnswer);
                    qs.add(newQuestion);
                    Log.i("QUIZAPP", ""+qs);
                }
                jsonTopic.setQuestions(qs);
                this.topics.add(jsonTopic);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    // reads InputStream of JSON file and returns the file in JSON String format
    public String readJSONFile(InputStream inputStream) throws IOException {

        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();

        return new String(buffer, "UTF-8");


    }

    public ITopicRepository getRepo(){
        if(repo==null){
            try{
                repo = new ITopicRepository() {
                    @Override
                    public List<Topic> getAllTopics() {
                        return topics;
                    }


                    @Override
                    public String getTitle() {
                        return topic;
                    }

                    @Override
                    public String getDesciption() {
                        return topics.get(current).getDescription();
                    }

                    @Override
                    public Question getQuestion(int questionIndex) {
                        List<Question> q = topics.get(current).getQuestions();
                        return q.get(questionIndex);
                    }

                    @Override
                    public String getQuestionDescription() {
                        return questions.get(current).getQuestionText();
                    }

                    @Override
                    public void setTopic(String topic) {
                        QuizApp.topic = topic;
                    }

                    @Override
                    public int totalQuestions() {
                        int n = topics.get(current).getQuestions().size();
                        Log.i("QUIZAPPSIZE", ""+n);
                        return n;
                    }
                };
            }catch (Exception e){
                Log.i("eeror", e.getMessage());
                repo=null;
            }
        }
        return repo;
    }



}



