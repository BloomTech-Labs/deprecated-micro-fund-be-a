package com.lambdaschool.microfund.services;

import com.lambdaschool.microfund.exceptions.ResourceNotFoundException;
import com.lambdaschool.microfund.models.Question;
import com.lambdaschool.microfund.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "questionService")
public class QuestionServiceImpl
    implements QuestionService
{

    @Autowired
    QuestionRepository questionrepos;

    @Override
    public List<Question> findAll()
    {
       List<Question> list = new ArrayList<>();
       questionrepos.findAll()
           .iterator()
           .forEachRemaining(list::add);
       return list;
    }

    @Override
    public Question findQuestionById(long id)
    {
        return questionrepos.findById(id)
            .orElseThrow(() -> new ResourceAccessException("Question id " + id + " Not " +
                "Found!"));
    }

    @Override
    public Question save(Question question)
    {
        return questionrepos.save(question);
    }

    @Override
    public Question update(long id, Question question)
    {
        if (question.getQuestion() == null)
        {
            throw new ResourceNotFoundException("No question found to update!");
        }

        Question newQuestion = findQuestionById(id);
        newQuestion.setQuestion(question.getQuestion());
        newQuestion.setOrganization(question.getOrganization());

        return questionrepos.save(newQuestion);

    }

    @Override
    public void deleteAll()
    {
        questionrepos.deleteAll();
    }
}
