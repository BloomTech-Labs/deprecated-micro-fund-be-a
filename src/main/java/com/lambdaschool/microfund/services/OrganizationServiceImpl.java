package com.lambdaschool.microfund.services;

import com.lambdaschool.microfund.exceptions.ResourceNotFoundException;
import com.lambdaschool.microfund.models.*;
import com.lambdaschool.microfund.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "orgService")
public class OrganizationServiceImpl
    implements OrganizationService
{
    @Autowired
    private OrganizationRepository orgrepos;

    @Autowired
    private UserService userService;

    @Autowired
    private HelperFunctions helperFunctions;

    public Organization findOrgById(long id) throws
                                      ResourceNotFoundException
    {
        return orgrepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Org id " + id + " not " +
                "found!"));
    }

    @Override
    public Organization findOrgByName(String name) throws ResourceNotFoundException
    {
        Organization o = orgrepos.findByName(name.toLowerCase());
        if (o == null)
        {
            throw new ResourceNotFoundException(("Org Name "+ name + " not found!"));
        }
        return o;
    }

    @Override
    public List<Organization> findByNameContaining(String orgname)
    {

        return orgrepos.findByNameContainingIgnoreCase(orgname.toLowerCase());
    }

    @Override
    public List<Organization> findAll()
    {
        List<Organization> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        orgrepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        orgrepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Org id " + id + " not " +
                "found!"));
        orgrepos.deleteById(id);
    }

    @Transactional
    @Override
    public Organization save(Organization org)
    {

        Organization newOrg = new Organization();

        if (org.getOrgid() != 0)
        {
            orgrepos.findById(org.getOrgid())
                .orElseThrow(() -> new ResourceNotFoundException("Org id " + org.getOrgid() + " not found!"));
            newOrg.setOrgid(org.getOrgid());
        }

        newOrg.setName(org.getName());

        if (org.getDescription() != null)
        {
            newOrg.setDescription(org.getDescription());
        }

        newOrg.getMembers().clear();
        for (OrganizationMembers orgM : org.getMembers())
        {
            newOrg.getMembers()
                .add(new OrganizationMembers(orgM.getUser(), newOrg));
        }

        newOrg.getQuestions().clear();
        for (Question question : org.getQuestions())
        {
            newOrg.getQuestions()
                .add(new Question(newOrg, question.getQuestion()));
        }

        return orgrepos.save(newOrg);
    }

    @Transactional
    @Override
    public Organization update(
        Organization org,
        long id)
    {
        Organization currentOrg = findOrgById(id);

        if (org.getName() != null)
        {
            currentOrg.setName(org.getName()
                .toLowerCase());
        }

        if (org.getDescription() != null)
        {
            currentOrg.setDescription(org.getDescription());
        }

        if (org.getMembers()
            .size() > 0)
        {
            currentOrg.getMembers()
                .clear();
            for (OrganizationMembers Orgm : org.getMembers())
            {
                User addUser = userService.findUserById(Orgm.getUser()
                    .getUserid());

                currentOrg.getMembers()
                    .add(new OrganizationMembers(addUser, currentOrg));
            }
        }

        if (org.getQuestions()
            .size() > 0)
        {
            currentOrg.getQuestions().clear();
            for (Question question : org.getQuestions())
            {
                currentOrg.getQuestions()
                    .add(new Question(currentOrg, question.getQuestion()));
            }
        }


        return orgrepos.save(currentOrg);
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        orgrepos.deleteAll();
    }
}
