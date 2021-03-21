package com.challenge.techbase.Service;

import com.challenge.techbase.mappers.UserRepository;
import com.challenge.techbase.models.entity.Profile;
import com.challenge.techbase.models.entity.Role;
import com.challenge.techbase.models.entity.User;
import com.challenge.techbase.services.UserService;
import com.challenge.techbase.util.Enum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    @DisplayName("Test findByEmail Success")
    void testFindByEmailSuccess() {
        User user = generateUser("password");
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        Optional<User> userOpt = userService.findByEmail(user.getEmail());

        verify(userRepository, times(1)).findByEmail(any());
        assertTrue(userOpt.isPresent());
        assertEquals(user, userOpt.get());
    }

    @Test
    @DisplayName("Test save Success")
    void testSaveSuccess() {
        User entity = generateUser(bCryptPasswordEncoder.encode("password"));
        userService.save(entity);

        verify(userRepository, times(1)).saveAndFlush(any());
    }

    public static User generateUser(String password) {
        Profile profile = new Profile();
        profile.setId(1);
        profile.setFirstName("first_name");
        profile.setLastName("last_name");
        profile.setDateOfBirth("2000-10-10");
        profile.setGender(Enum.Gender.MALE);
        profile.setPhoneNumber("012345689");

        Role role = new Role(1);
        role.setName("DIRECTOR");

        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword(password);
        user.setDepartments(Collections.emptySet());
        user.setTeams(Collections.emptySet());
        user.setRoles(Collections.singleton(role));
        return user;
    }
}
