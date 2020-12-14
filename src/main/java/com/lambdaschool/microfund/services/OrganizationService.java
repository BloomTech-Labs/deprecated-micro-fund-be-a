package com.lambdaschool.microfund.services;

import com.lambdaschool.microfund.models.Organization;

import java.util.List;

/**
 * The Service that works with Organization Model.
 */
public interface OrganizationService
{

    /**
     * finds all org in the database.
     *
     * @return A list of org. an empty list of none if found
     */
    List<Organization> findAll();


    /**
     * Returns the org with the given primary key.
     *
     * @param name The primary key (long) of the org you seek.
     * @return The given Organization or throws an exception if not found.
     */
    Organization findOrgByName(String name);

    List<Organization> findByNameContaining(String name);

    /**
     * Returns the org with the given primary key.
     *
     * @param id The primary key (long) of the Org you seek.
     * @return The given org or throws an exception if not found.
     */
    Organization findOrgById(long id);

    /**
     * Deletes the Organization record from the database based off of the provided primary key
     *
     * @param id id The primary key (long) of the Organization you seek to delete.
     */
    void delete(long id);

    /**
     * Given a complete Organization object, saves that Organization object in the database.
     * If a primary key is provided, the record is completely replaced
     * If no primary key is provided, one is automatically generated and the record is added to the database.
     *
     * @param organization the Organization object to be saved
     * @return the saved Organization object including any automatically generated fields
     */
    Organization save(Organization organization);

    /**
     * Updates the provided fields in the Organization record referenced by the primary key.
     *
     * @param organization just the org fields to be updated.
     * @param id   The primary key (long) of the org to update
     * @return the complete org object that got updated
     */
    Organization update(
            Organization organization,
        long id);

    /**
     * Deletes all Organization and their associated records from the database
     */
    public void deleteAll();
}