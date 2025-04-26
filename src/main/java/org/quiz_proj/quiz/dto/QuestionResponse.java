package org.quiz_proj.quiz.dto;

import lombok.Data;

@Data
public class QuestionResponse {
    private Long questionId;
    private String selectedOption;
}
