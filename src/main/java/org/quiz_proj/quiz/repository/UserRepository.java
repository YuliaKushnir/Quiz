package org.quiz_proj.quiz.repository;

import org.quiz_proj.quiz.enums.UserRole;
import org.quiz_proj.quiz.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByEmail(String email);

    User findByRole(UserRole role);
}
