package org.quiz_proj.quiz.dto;

import lombok.Data;

import java.util.List;

@Data
public class TestDetailsDto {

    private TestDto testDto;

    private List<QuestionDto> questions;

}
