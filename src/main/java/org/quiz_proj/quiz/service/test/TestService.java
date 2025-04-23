package org.quiz_proj.quiz.service.test;

import org.quiz_proj.quiz.dto.QuestionDto;
import org.quiz_proj.quiz.dto.TestDto;
import org.quiz_proj.quiz.models.Test;

import java.util.List;

public interface TestService {

    TestDto createTest(TestDto dto);

    QuestionDto addQuestionInTest(QuestionDto dto);

    List<TestDto> getAllTests();
}
