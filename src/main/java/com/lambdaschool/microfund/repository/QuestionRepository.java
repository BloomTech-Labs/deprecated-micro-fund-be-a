package com.lambdaschool.microfund.repository;

import com.lambdaschool.microfund.models.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository
    extends CrudRepository<Question, Long>
{
}
