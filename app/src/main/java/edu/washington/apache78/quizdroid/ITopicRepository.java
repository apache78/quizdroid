package edu.washington.apache78.quizdroid;

import java.util.List;

public interface ITopicRepository
{
    //HashMap<String, JSONObject> questions = null;
    public List<Topic> getAllTopics();

    public String getTitle();
    public String getDesciption();
    public Question getQuestion(int questionIndex);
    public String getQuestionDescription();
    public void setTopic(String topic);
    public int totalQuestions();
}

