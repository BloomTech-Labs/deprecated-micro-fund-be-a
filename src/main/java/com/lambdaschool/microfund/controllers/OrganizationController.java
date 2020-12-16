package com.lambdaschool.microfund.controllers;

import com.fasterxml.classmate.members.ResolvedParameterizedMember;
import com.lambdaschool.microfund.models.Organization;
import com.lambdaschool.microfund.models.Question;
import com.lambdaschool.microfund.services.OrganizationService;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orgs")
public class OrganizationController
{
    @Autowired
    private OrganizationService orgService;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<?> listAllOrgs()
    {
        List<Organization> orgs = orgService.findAll();
        return new ResponseEntity<>(orgs, HttpStatus.OK);
    }

    @GetMapping(value = "/{orgId}", produces = "application/json")
    public ResponseEntity<?> getOrgById(@PathVariable Long orgId)
    {
        Organization o = orgService.findOrgById(orgId);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

//    @GetMapping(value = "/{orgId}", produces = "application/json")
//    public ResponseEntity<?> getOrgQuestions(@PathVariable Long orgId)
//    {
//        return null;
//    }
}
