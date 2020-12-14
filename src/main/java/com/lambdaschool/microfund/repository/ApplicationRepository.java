package com.lambdaschool.microfund.repository;

import com.lambdaschool.microfund.models.Application;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApplicationRepository
        extends CrudRepository<Application, Long>
{
    /**
     * JPA Query to find all applications for specified user.
     *
     * @param userid the ID of the user whose applications seek
     * @return all applications belonging to a given userid
    **/
    List<Application> findByUser_Userid(long userid);

    /**
     * JPA Query to find all applications for specified user.
     *
     * @param username the username of the user whose applications seek
     * @return all applications belonging to a given username
     **/
    List<Application> findByUser_UsernameContainingIgnoringCase(String username);

    Application findByOrganization_Name(String name);


    List<Application> findByType(String type);

}
