package com.lambdaschool.microfund.repository;

import com.lambdaschool.microfund.models.Answer;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository
    extends CrudRepository<Answer, Long>
{
}
