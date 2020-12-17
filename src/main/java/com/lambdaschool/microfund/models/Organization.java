package com.lambdaschool.microfund.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "organizations")
public class Organization
    extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orgid;

    @NotNull
    @Column(unique = true)
    private String name;

    private String description;

    @OneToMany(mappedBy = "organization",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value={"organization", "application", "answers",
        "applications"},
        allowSetters = true)
    private List<Application> applications = new ArrayList<>();

    @OneToMany(mappedBy = "organization",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "organization",
        allowSetters = true)
    private Set<OrganizationMembers> members = new HashSet<>();

    @OneToMany(mappedBy =  "organization",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value={"organization"},
        allowSetters = true)
    private List<Question> questions = new ArrayList<>();

    public Organization()
    {
        // Constructor for JPA
    }

    public Organization(String name)
    {
        setName(name);
    }

    public long getOrgid()
    {
        return orgid;
    }

    public void setOrgid(long orgid)
    {
        this.orgid = orgid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public List<Application> getApplications()
    {
        return applications;
    }

    public void setApplications(List<Application> applications)
    {
        this.applications = applications;
    }

    public Set<OrganizationMembers> getMembers()
    {
        return members;
    }

    public void setMembers(Set<OrganizationMembers> members)
    {
        this.members = members;
    }

    public List<Question> getQuestions()
    {
        return questions;
    }

    public void setQuestions(List<Question> questions)
    {
        this.questions = questions;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}

