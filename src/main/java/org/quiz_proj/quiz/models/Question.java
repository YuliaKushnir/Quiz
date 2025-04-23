package org.quiz_proj.quiz.models;

import jakarta.persistence.*;
import lombok.Data;
import org.quiz_proj.quiz.dto.QuestionDto;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    private String correctOption;

    // One test can have many questions
    @ManyToOne
    @JoinColumn(name="test_id")
    private Test test;

    public QuestionDto getQuestionDto(){
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(id);
        questionDto.setQuestionText(questionText);
        questionDto.setOptionA(optionA);
        questionDto.setOptionB(optionB);
        questionDto.setOptionC(optionC);
        questionDto.setOptionD(optionD);
        questionDto.setCorrectOption(correctOption);

        return questionDto;
    }
}
