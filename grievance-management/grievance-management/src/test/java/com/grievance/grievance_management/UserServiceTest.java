package com.grievance.service;

import com.grievance.model.User;
import com.grievance.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserServiceTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private UserService userService = new UserService(userRepository);

    @Test
    public void testRegisterUser() {
        User user = new User();
        user.setUserName("Test User");
        user.setUserEmail("test@example.com");

        Mockito.when(userRepository.save(user)).thenReturn(user);

        User result = userService.registerUser(user);
        assertEquals("Test User", result.getUserName());
        assertEquals("test@example.com", result.getUserEmail());
    }
}
