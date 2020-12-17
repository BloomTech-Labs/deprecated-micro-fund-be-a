package com.lambdaschool.microfund.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "questions")
public class Question
    extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long questionid;

    @ManyToOne
    @JoinColumn(name = "orgid", nullable = false)
    @JsonIgnoreProperties(value = {"questions", "members"},
        allowSetters = true)
    private Organization organization;

    @NotNull
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
