package com.lambdaschool.microfund.repository;

import com.lambdaschool.microfund.models.Organization;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrganizationRepository extends CrudRepository<Organization, Long>
{
    Organization findByName(String name);

    List<Organization> findByNameContainingIgnoreCase(String name);

}
