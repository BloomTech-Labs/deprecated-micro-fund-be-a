package com.lambdaschool.microfund.services;

import com.lambdaschool.microfund.exceptions.ResourceNotFoundException;
import com.lambdaschool.microfund.models.Answer;
import com.lambdaschool.microfund.models.Question;
import com.lambdaschool.microfund.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "ansService")
public class AnswerServiceImpl
    implements AnswerService
{

    @Autowired
    AnswerRepository answerrepos;

    @Override
    public List<Answer> findAll()
    {
        List<Answer> list = new ArrayList<>();
        answerrepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Answer findAnswerById(long id)
    {
        return answerrepos.findById(id)
            .orElseThrow(() -> new ResourceAccessException("Answer id " + id + " Not " +
                "Found!"));

    }

    @Override
    public Answer save(Answer answer)
    {
        return answerrepos.save(answer);
    }

    @Override
    public Answer update(long id, Answer answer)
    {
        Answer newAnswer = findAnswerById(id);
        newAnswer.setAnswer(answer.getAnswer());
        newAnswer.setQuestion(answer.getQuestion());
        newAnswer.setApplication(answer.getApplication());
        return answerrepos.save(newAnswer);
    }

    @Override
    public void deleteAll()
    {
        answerrepos.deleteAll();
    }
}
