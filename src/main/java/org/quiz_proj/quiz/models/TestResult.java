package org.quiz_proj.quiz.models;

import jakarta.persistence.*;
import lombok.Data;
import org.quiz_proj.quiz.dto.TestResultDto;

@Entity
@Data
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalQuestions;

    private int totalCorrectAnswers;

    private double percentage;

    @ManyToOne // one test - many results
    @JoinColumn(name ="test_id")
    private Test test;

    @ManyToOne // one user - many results
    @JoinColumn(name="user_id")
    private User user;

    public TestResultDto getTestResultDto() {
        TestResultDto testResultDto = new TestResultDto();

        testResultDto.setId(id);
        testResultDto.setTotalQuestions(totalQuestions);
        testResultDto.setTotalCorrectAnswers(totalCorrectAnswers);
        testResultDto.setPercentage(percentage);
        testResultDto.setTestName(test.getTitle());
        testResultDto.setUserName(user.getUsername());

        return testResultDto;
    }
}
