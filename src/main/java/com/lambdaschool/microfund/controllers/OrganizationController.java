package com.lambdaschool.microfund.controllers;

import com.lambdaschool.microfund.models.Organization;
import com.lambdaschool.microfund.models.User;
import com.lambdaschool.microfund.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
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


    @PostMapping(value = "/",
        consumes = "application/json")
    public ResponseEntity<?> addNewOrg(
        @Valid
        @RequestBody
            Organization neworg) throws
        URISyntaxException
    {
        neworg.setOrgid(0);
        neworg = orgService.save(neworg);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newOrgURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{orgid}")
            .buildAndExpand(neworg.getOrgid())
            .toUri();
        responseHeaders.setLocation(newOrgURI);

        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }

    @PutMapping(value = "/{orgid}",
        consumes = "application/json")
    public ResponseEntity<?> updateFullOrg(
        @Valid
        @RequestBody
            Organization updateOrg,
        @PathVariable
            long orgid)
    {
        updateOrg.setOrgid(orgid);
        orgService.save(updateOrg);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/{orgid}",
        consumes = "application/json")
    public ResponseEntity<?> updateOrg(
        @RequestBody
            Organization updateOrg,
        @PathVariable
            long orgid)
    {
        orgService.update(updateOrg,
            orgid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
