package org.quiz_proj.quiz.models;

import jakarta.persistence.*;
import lombok.Data;
import org.quiz_proj.quiz.dto.TestDto;

import java.util.List;

@Entity
@Data
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Long time;

    // One test cat have many questions
    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private List<Question> questions;

    public TestDto getDto() {
        TestDto testDto = new TestDto();
        testDto.setId(id);
        testDto.setTitle(title);
        testDto.setDescription(description);
        testDto.setTime(time);

        return testDto;
    }


}
