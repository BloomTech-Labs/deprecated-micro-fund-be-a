package com.lambdaschool.microfund.services;

import com.lambdaschool.microfund.models.Application;
import com.lambdaschool.microfund.models.User;

import java.util.List;

/**
 * The Service that works with Application Model.
 */
public interface ApplicationService
{

    List<Application> findAll();

    /**
     * Returns the application with the given primary key.
     *
     * @param id The primary key (long) of the application you seek.
     * @return The given Application or throws an exception if not found.
     */
    Application findAppById(long id);

    /**
     * Returns the application with the given name
     *
     * @param type The type (String) of the of applicaions you seek.
     * @return List of applications with given type.
     */
    List<Application> findAppByType(String type);

    /**
     * Deletes the application record from the database based off of the provided primary key
     *
     * @param id id The primary key (long) of the application you seek to delete.
     */
    void delete(long id);

    /**
     * Given a complete application object, saves that application object in the database.
     * If a primary key is provided, the record is completely replaced
     * If no primary key is provided, one is automatically generated and the record is added to the database.
     *
     * @param applicaion the application object to be saved
     * @return the saved application object including any automatically generated fields
     */
    Application save(Application applicaion);

    /**
     * Updates the provided fields in the application record referenced by the primary key.
     *
     * @param application just the application fields to be updated.
     * @param id   The primary key (long) of the application to update
     * @return the complete application object that got updated
     */
    Application update(
        Application application,
        long id);

    /**
     * Deletes all applications and their associated records from the database
     */
    public void deleteAll();

    void memberResponse(long appid);
}