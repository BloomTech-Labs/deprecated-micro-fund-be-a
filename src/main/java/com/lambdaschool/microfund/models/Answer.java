package com.lambdaschool.microfund.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "answers")
public class Answer
    extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long answerid;

    @ManyToOne
    @JoinColumn(name = "applicationid", nullable = false)
    @JsonIgnoreProperties(value = {"questions", "answers"},
        allowSetters = true)
    private Application application;

    @ManyToOne
    @JoinColumn(name = "questionid", nullable = false)
    @JsonIgnoreProperties(value = "application",
        allowSetters = true)
    private Question question;

    @NotNull
    @Column(columnDefinition = "TEXT")
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
