package org.quiz_proj.quiz.service.user;

import jakarta.annotation.PostConstruct;
import org.quiz_proj.quiz.enums.UserRole;
import org.quiz_proj.quiz.models.User;
import org.quiz_proj.quiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void createAdminUser() {
        User optionalUser = userRepository.findByRole(UserRole.ADMIN);
        if (optionalUser == null) {
            User user = new User();

            user.setUsername("Admin");
            user.setEmail("admin@admin.com");
            user.setRole(UserRole.ADMIN);
            user.setPassword("admin");

            userRepository.save(user);
        }
    }

    public Boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email) != null;
    }

    public User createUser(User user) {
        user.setRole(UserRole.USER);

        return userRepository.save(user);
    }

}
