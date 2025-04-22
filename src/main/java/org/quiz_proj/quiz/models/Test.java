package org.quiz_proj.quiz.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.quiz_proj.quiz.dto.TestDto;

@Entity
@Data
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Long time;

    public TestDto getDto() {
        TestDto testDto = new TestDto();
        testDto.setId(id);
        testDto.setTitle(title);
        testDto.setDescription(description);
        testDto.setTime(time);

        return testDto;
    }


}
