package org.quiz_proj.quiz.service.user;

import org.quiz_proj.quiz.models.User;

public interface UserService {

    User createUser(User user);

    Boolean hasUserWithEmail(String email);

    User login(User user);

}
