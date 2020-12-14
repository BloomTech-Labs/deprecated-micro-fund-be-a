package com.lambdaschool.microfund.services;

import com.lambdaschool.microfund.models.Answer;

import java.util.List;

public interface AnswerService
{
    List<Answer> findAll();

    Answer findAnswerById(long id);

    Answer save(Answer answer);

    Answer update(long id, Answer answer);

    public void deleteAll();
}
