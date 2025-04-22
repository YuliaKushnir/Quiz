package org.quiz_proj.quiz.service.test;

import org.quiz_proj.quiz.dto.TestDto;
import org.quiz_proj.quiz.models.Test;
import org.quiz_proj.quiz.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestRepository testRepository;

    public TestDto createTest(TestDto dto){
        Test test = new Test();

        test.setTitle(dto.getTitle());
        test.setDescription(dto.getDescription());
        test.setTime(dto.getTime());

        return testRepository.save(test).getDto();
    }

}
