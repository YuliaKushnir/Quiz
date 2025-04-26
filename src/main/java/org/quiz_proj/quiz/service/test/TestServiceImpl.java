package org.quiz_proj.quiz.service.test;

import jakarta.persistence.EntityNotFoundException;
import org.quiz_proj.quiz.dto.*;
import org.quiz_proj.quiz.models.Question;
import org.quiz_proj.quiz.models.Test;
import org.quiz_proj.quiz.models.TestResult;
import org.quiz_proj.quiz.models.User;
import org.quiz_proj.quiz.repository.QuestionRepository;
import org.quiz_proj.quiz.repository.TestRepository;
import org.quiz_proj.quiz.repository.TestResultRepository;
import org.quiz_proj.quiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestRepository testRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TestResultRepository testResultRepository;

    @Autowired
    private UserRepository userRepository;


    public TestDto createTest(TestDto dto){
        Test test = new Test();

        test.setTitle(dto.getTitle());
        test.setDescription(dto.getDescription());
        test.setTime(dto.getTime());

        return testRepository.save(test).getDto();
    }

    public QuestionDto addQuestionInTest(QuestionDto dto){
        Optional<Test> optionalTest = testRepository.findById(dto.getId());
        if(optionalTest.isPresent()){
            Question question = new Question();

            question.setTest(optionalTest.get());
            question.setQuestionText(dto.getQuestionText());
            question.setOptionA(dto.getOptionA());
            question.setOptionB(dto.getOptionB());
            question.setOptionC(dto.getOptionC());
            question.setOptionD(dto.getOptionD());
            question.setCorrectOption(dto.getCorrectOption());

            return questionRepository.save(question).getQuestionDto();
        }
        throw new EntityNotFoundException("Test not found");
    }

    public List<TestDto> getAllTests() {
        return testRepository.findAll().stream().peek(test -> test.setTime(test.getQuestions().size() * test.getTime())).collect(Collectors.toList())
                .stream().map(Test::getDto).collect(Collectors.toList());
    }

    public TestDetailsDto getAllQuestionsByTest(Long id){
        Optional<Test> optionalTest = testRepository.findById(id);
        TestDetailsDto testDetailsDto = new TestDetailsDto();
        if(optionalTest.isPresent()){
            TestDto testDto = optionalTest.get().getDto();
            testDto.setTime(optionalTest.get().getTime() * optionalTest.get().getQuestions().size());

            testDetailsDto.setTestDto(testDto);
            testDetailsDto.setQuestions(optionalTest.get().getQuestions().stream().map(Question::getQuestionDto).toList());
            return testDetailsDto;
        }
        return testDetailsDto;

    }

    public TestResultDto submitTest(SubmitTestDto request){
        Test test = testRepository.findById(request.getTestId()).orElseThrow(() -> new EntityNotFoundException("Test not found"));
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found"));

        int correctAnswers = 0;

        for(QuestionResponse response: request.getResponses()){
            Question question = questionRepository.findById(response.getQuestionId()).orElseThrow(() -> new EntityNotFoundException("Question not found"));
            if(question.getCorrectOption().equals(response.getSelectedOption())){
                correctAnswers++;
            }
        }

        int totalQuestions = test.getQuestions().size();
        double percentage = ((double)correctAnswers/totalQuestions) * 100;

        TestResult testResult = new TestResult();
        testResult.setTest(test);
        testResult.setUser(user);
        testResult.setTotalQuestions(totalQuestions);
        testResult.setTotalCorrectAnswers(correctAnswers);
        testResult.setPercentage(percentage);

        return testResultRepository.save(testResult).getTestResultDto();
    }

}
