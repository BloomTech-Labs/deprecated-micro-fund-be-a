package com.lambdaschool.oktafoundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @JsonIgnoreProperties(value = "organizations",
        allowSetters = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "orgid", nullable = false)
    @JsonIgnoreProperties(value = "users",
        allowSetters = true)
    private Organization organization;

    private String status;

    private String type;

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
}
