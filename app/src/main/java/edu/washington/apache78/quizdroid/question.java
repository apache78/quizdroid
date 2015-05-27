package edu.washington.apache78.quizdroid;

/**
 * Created by apache78 on 5/20/2015.
 */
public class Question {
    private String text;
    private int answerIndex;
    private String[] answers;

    public Question()
    {
        this.text=null;
        this.answers=null;
        this.answerIndex=0;
    }

    public Question(String questionText, String[] answers, int index){
        this.text = questionText;
        this.answers = answers;
        this.answerIndex = index;
    }

    public String getQuestionText(){
        return text;
    }

    public String[] getAnswerArray(){
        return answers;
    }

    public int getAnswerIndex(){
        return answerIndex;
    }


}
