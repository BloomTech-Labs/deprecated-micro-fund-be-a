package com.lambdaschool.oktafoundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "answers")
public class Answer
    extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long answerid;

    @ManyToOne
    @JoinColumn(name = "applicationid")
    @JsonIgnoreProperties(value = "questions",
        allowSetters = true)
    private Application application;

    @ManyToOne
    @JoinColumn(name = "questionid")
    @JsonIgnoreProperties(value = "applications",
        allowSetters = true)
    private Question question;

    private String answer;

    public Answer()
    {
        // Constructor for JPA
    }

    public Answer(Application application, Question question, String answer)
    {
        this.application = application;
        this.question = question;
        this.answer = answer;
    }

    public long getAnswerid()
    {
        return answerid;
    }

    public void setAnswerid(long answerid)
    {
        this.answerid = answerid;
    }

    public Application getApplication()
    {
        return application;
    }

    public void setApplication(Application application)
    {
        this.application = application;
    }

    public Question getQuestion()
    {
        return question;
    }

    public void setQuestion(Question question)
    {
        this.question = question;
    }

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }
}
