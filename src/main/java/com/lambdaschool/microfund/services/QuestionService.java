package com.lambdaschool.microfund.services;

import com.lambdaschool.microfund.models.Question;

import java.util.List;

public interface QuestionService
{
    List<Question> findAll();

    Question findQuestionById(long id);

    Question save(Question question);

    Question update(long id, Question question);

    public void deleteAll();
}
