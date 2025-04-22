package org.quiz_proj.quiz.controllers;

import org.quiz_proj.quiz.dto.TestDto;
import org.quiz_proj.quiz.service.test.TestService;
import org.quiz_proj.quiz.service.test.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/test")
@CrossOrigin("*")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping()
    public ResponseEntity<?> createTest(@RequestBody TestDto dto){
        try {
            return new ResponseEntity<>(testService.createTest(dto), HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
