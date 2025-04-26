package org.quiz_proj.quiz.dto;

import lombok.Data;

@Data
public class TestResultDto {
    private Long id;

    private int totalQuestions;

    private int totalCorrectAnswers;

    private double percentage;

    private String testName;

    private String userName;


}
