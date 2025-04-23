package org.quiz_proj.quiz.dto;

import lombok.Data;

@Data
public class QuestionDto {
    private Long id;

    private String questionText;

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    private String correctOption;
}
