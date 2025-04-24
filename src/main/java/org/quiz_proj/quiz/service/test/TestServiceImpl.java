package org.quiz_proj.quiz.service.test;

import jakarta.persistence.EntityNotFoundException;
import org.quiz_proj.quiz.dto.QuestionDto;
import org.quiz_proj.quiz.dto.TestDetailsDto;
import org.quiz_proj.quiz.dto.TestDto;
import org.quiz_proj.quiz.models.Question;
import org.quiz_proj.quiz.models.Test;
import org.quiz_proj.quiz.repository.QuestionRepository;
import org.quiz_proj.quiz.repository.TestRepository;
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

}
