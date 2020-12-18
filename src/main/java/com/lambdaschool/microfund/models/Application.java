package com.lambdaschool.microfund.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "applications")
public class Application
    extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long applicationid;


    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnoreProperties(value = "organization",
        allowSetters = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "orgid", nullable = false)
    @JsonIgnoreProperties(value = {"user", "applications", "members"},
        allowSetters = true)
    private Organization organization;

    @NotNull
    private String status;

    @NotNull
    private String type;

    private String memberresponse;

    @OneToMany(mappedBy = "application",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "applications",
        allowSetters = true)
    private List<Answer> answers = new ArrayList<>();

    public Application()
    {
        // Constructor for JPA
    }

    public Application(User user, Organization organization)
    {
        this.user = user;
        this.organization = organization;
    }

    public long getApplicationid()
    {
        return applicationid;
    }

    public void setApplicationid(long applicationid)
    {
        this.applicationid = applicationid;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Organization getOrganization()
    {
        return organization;
    }

    public void setOrganization(Organization organization)
    {
        this.organization = organization;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public List<Answer> getAnswers()
    {
        return answers;
    }

    public void setAnswers(List<Answer> answers)
    {
        this.answers = answers;
    }

    public String getMemberresponse()
    {
        return memberresponse;
    }

    public void setMemberresponse(String memberresponse)
    {
        this.memberresponse = memberresponse;
    }
}
