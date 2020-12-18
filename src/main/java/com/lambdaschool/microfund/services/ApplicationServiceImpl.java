package com.lambdaschool.microfund.services;

import com.lambdaschool.microfund.exceptions.ResourceFoundException;
import com.lambdaschool.microfund.exceptions.ResourceNotFoundException;
import com.lambdaschool.microfund.models.*;
import com.lambdaschool.microfund.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value="applicationService")
public class ApplicationServiceImpl implements ApplicationService
{
    @Autowired
    ApplicationRepository appRepos;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    UserService userService;

    @Override
    public List<Application> findAll()
    {
        List<Application> applicationList = new ArrayList<>();

        appRepos.findAll()
                .iterator()
                .forEachRemaining(applicationList::add);
        return applicationList;
    }

    @Override
    public Application findAppById(long id)
    {

        return appRepos.findById(id)
                .orElseThrow(() -> new ResourceFoundException("Application ID " + id + " not found!"));
    }

    @Override
    public List<Application> findAppByType(String type)
    {
        List<Application> applicationList = new ArrayList<>();
        appRepos.findByType(type)
                .iterator()
                .forEachRemaining(applicationList::add);
        return applicationList;
    }

    @Override
    public void delete(long id)
    {
        appRepos.findById(id)
            .orElseThrow(()-> new ResourceFoundException("Application ID: "+ id + " not found."));
        appRepos.deleteById(id);

    }

    @Override
    public Application save(Application application)
    {
        Application newapp = new Application();

        if (application.getApplicationid() != 0)
        {
            appRepos.findById(application.getApplicationid())
                    .orElseThrow(() -> new ResourceNotFoundException("application id " + application.getApplicationid() + " not found!"));
            newapp.setApplicationid(application.getApplicationid());
        }

        newapp.setStatus(application.getStatus().toLowerCase());
        newapp.setType(application.getType().toLowerCase());
        newapp.setOrganization(application.getOrganization());
        newapp.setUser(application.getUser());

        newapp.getAnswers().clear();
        for(Answer answer : application.getAnswers()){
            Answer ans = new Answer(answer.getApplication(),answer.getQuestion(),answer.getAnswer());
            newapp.getAnswers().add(ans);
        }
        return appRepos.save(newapp);
    }

    @Override
    public Application update(Application application,
                              long id)
    {
        Application app = findAppById(id);

        if(application.getStatus() != null)
        {
            app.setStatus(application.getStatus()
                    .toLowerCase());
        }
        if(application.getType() != null)
        {
            app.setType(application.getType()
                    .toLowerCase());
        }
        if(application.getOrganization() != null)
        {
            app.setOrganization(application.getOrganization());
        }
        if(application.getUser() != null)
        {
            app.setUser(application.getUser());
        }

        if(application.getMemberresponse() != null)
        {
            app.setMemberresponse(application.getMemberresponse().toLowerCase());
        }

        if(application.getAnswers().size() > 0)
        {
            app.getAnswers()
                    .clear();
            for (Answer answer : application.getAnswers())
            {
                Answer ans = new Answer(answer.getApplication(),
                        answer.getQuestion(),
                        answer.getAnswer());
                app.getAnswers()
                        .add(ans);
            }
        }
        return appRepos.save(app);
    }

    @Override
    public void deleteAll()
    {
        appRepos.deleteAll();

    }

    @Override
    public void memberResponse(long appid)
    {
        Application app = findAppById(appid);

        if (app.getMemberresponse().equalsIgnoreCase("accept")){
            Organization org = organizationService.findOrgById(app.getOrganization().getOrgid());
            User user = userService.findUserById(app.getUser().getUserid());

            org.getMembers().add( new OrganizationMembers(user, org));
            organizationService.update(org,org.getOrgid());
        }

    }
}
