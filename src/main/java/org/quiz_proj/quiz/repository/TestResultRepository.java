package org.quiz_proj.quiz.repository;

import org.quiz_proj.quiz.models.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {

    List<TestResult> findAllByUserId(Long userId);

}
