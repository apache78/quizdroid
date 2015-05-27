package edu.washington.apache78.quizdroid;

import java.util.List;

/**
 * Created by apache78 on 5/20/2015.
 */
public class Topic {
    private String title;
    private String description;
    private List<Question> questions;

    public Topic() {
        title=null;
        description=null;
        questions=null;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public void setDescription(String desc){
        this.description=desc;
    }

    public void setQuestions(List<Question> questions){
        this.questions=questions;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }

    public List<Question> getQuestions(){
        return this.questions;
    }
}
