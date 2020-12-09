package com.lambdaschool.microfund.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * The entity allowing interaction with the userorganizations table.
 * The join table between users and organizations.
 * <p>
 * Table enforces a unique constraint of the combination of userid and organizationid.
 * These two together form the primary key.
 * <p>
 * When you have a compound primary key, you must implement Serializable for Hibernate
 * When you implement Serializable you must implement equals and hash code
 */
@Entity
@Table(name = "organizationmembers")
@IdClass(OrganizationMembersId.class)
public class OrganizationMembers
    extends Auditable
    implements Serializable
{
    /**
     * 1/2 of the primary key (long) for organizationmembers.
     * Also is a foreign key into the users table
     */
    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "organizations",
        allowSetters = true)
    private User user;

    /**
     * 1/2 of the primary key (long) for organizationmembers.
     * Also is a foreign key into the organization table
     */
    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "orgid")
    @JsonIgnoreProperties(value = "users",
        allowSetters = true)
    private Organization organization;

    /**
     * Default constructor used primarily by the JPA.
     */
    public OrganizationMembers()
    {
    }

    /**
     * Given the params, create a new user organization combination object
     *
     * @param user The user object of this relationship
     * @param organization The organization object of this relationship
     */
    public OrganizationMembers(
        User user,
        Organization organization)
    {
        this.user = user;
        this.organization = organization;
    }

    /**
     * The getter for User
     *
     * @return the complete user object associated with user organization combination
     */
    public User getUser()
    {
        return user;
    }

    /**
     * Setter for user
     *
     * @param user change the user object associated with this user organization combination to this one.
     */
    public void setUser(User user)
    {
        this.user = user;
    }

    /**
     * Getter for organization
     *
     * @return the complete organization object associated with this user organization combination
     */
    public Organization getOrganization()
    {
        return organization;
    }

    /**
     * Setter for organization
     *
     * @param organization change organization object associated with this user organization combination to this one.
     */
    public void setOrganization(Organization organization)
    {
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof OrganizationMembers))
        {
            return false;
        }
        OrganizationMembers that = (OrganizationMembers) o;
        return ((user == null) ? 0 : user.getUserid()) == ((that.user == null) ? 0 : that.user.getUserid()) &&
            ((organization == null) ? 0 : organization.getOrgid()) == ((that.organization == null) ? 0 : that.organization.getOrgid());
    }

    @Override
    public int hashCode()
    {
        // return Objects.hash(user.getUserid(), organization.getOrgid());
        return 919;
    }
}
