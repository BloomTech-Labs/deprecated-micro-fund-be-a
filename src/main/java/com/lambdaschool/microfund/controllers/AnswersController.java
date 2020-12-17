package com.lambdaschool.microfund.controllers;

import com.lambdaschool.microfund.models.Answer;
import com.lambdaschool.microfund.models.Organization;
import com.lambdaschool.microfund.services.AnswerService;
import com.lambdaschool.microfund.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswersController
{
    @Autowired
    private AnswerService answersService;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<?> listAllOrgs()
    {
        List<Answer> answers = answersService.findAll();
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    @GetMapping(value = "/{ansId}", produces = "application/json")
    public ResponseEntity<?> getOrgById(@PathVariable Long ansId)
    {
        Answer ans = answersService.findAnswerById(ansId);
        return new ResponseEntity<>(ans, HttpStatus.OK);
    }
}
