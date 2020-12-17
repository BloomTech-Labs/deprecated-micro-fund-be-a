package com.lambdaschool.microfund.controllers;

import com.lambdaschool.microfund.models.Application;
import com.lambdaschool.microfund.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apps")
public class ApplicationController
{
    @Autowired
    private ApplicationService appService;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<?> listAllOrgs()
    {
        List<Application> apps = appService.findAll();
        return new ResponseEntity<>(apps, HttpStatus.OK);
    }

    @GetMapping(value = "/{orgId}", produces = "application/json")
    public ResponseEntity<?> getOrgById(@PathVariable Long orgId)
    {
        Application a = appService.findAppById(orgId);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }
}
