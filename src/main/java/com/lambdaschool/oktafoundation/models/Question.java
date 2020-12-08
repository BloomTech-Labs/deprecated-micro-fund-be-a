package com.lambdaschool.oktafoundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "questions")
public class Question
    extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long questionid;

    @ManyToOne
    @JoinColumn(name = "orgid")
    @JsonIgnoreProperties(value = "questions", // not sure 'questions' is right value
        allowSetters = true)
    private Organization organization;

    private String question;

    public Question()
    {
        // Constructor for JPA
    }

    public Question(Organization organization, String question)
    {
        this.organization = organization;
        this.question = question;
    }

    public long getQuestionid()
    {
        return questionid;
    }

    public void setQuestionid(long questionid)
    {
        this.questionid = questionid;
    }

    public Organization getOrganization()
    {
        return organization;
    }

    public void setOrganization(Organization organization)
    {
        this.organization = organization;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }
}
