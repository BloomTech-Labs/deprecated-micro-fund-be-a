package com.lambdaschool.microfund.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnoreProperties(value = "organization",
        allowSetters = true)
    private List<Application> applications = new ArrayList<>();

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

    public void setDescription(String description)
    {
        this.description = description;
    }
}

