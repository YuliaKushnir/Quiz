package org.quiz_proj.quiz.service.test;

import org.quiz_proj.quiz.dto.*;
import org.quiz_proj.quiz.models.Test;

import java.util.List;

public interface TestService {

    TestDto createTest(TestDto dto);

    QuestionDto addQuestionInTest(QuestionDto dto);

    List<TestDto> getAllTests();

    TestDetailsDto getAllQuestionsByTest(Long id);

    TestResultDto submitTest(SubmitTestDto request);

    List<TestResultDto> getAllTestsResults();

    List<TestResultDto> getAllTestResultsByUserId(Long userId);

}
