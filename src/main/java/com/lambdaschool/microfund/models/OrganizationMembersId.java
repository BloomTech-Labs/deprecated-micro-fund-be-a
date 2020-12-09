package com.lambdaschool.microfund.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrganizationMembersId
    implements Serializable
{
    private long user;

    private long organization;

    /**
     * The default constructor required by JPA
     */
    public OrganizationMembersId()
    {
    }

    /**
     * Getter for the user id
     *
     * @return long the user id
     */
    public long getUser()
    {
        return user;
    }

    /**
     * Setter for the user id
     *
     * @param user the new user id for this object
     */
    public void setUser(long user)
    {
        this.user = user;
    }

    /**
     * Getter for the role id
     *
     * @return long the role id
     */
    public long getOrganization()
    {
        return organization;
    }

    /**
     * The setter for the organization id
     *
     * @param organization the new organization id for this object
     */
    public void setOrganization(long organization)
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
        // boolean temp = (o.getClass() instanceof Class);
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        OrganizationMembersId that = (OrganizationMembersId) o;
        return user == that.user &&
            organization == that.organization;
    }

    @Override
    public int hashCode()
    {
        return 3383;
    }
}
