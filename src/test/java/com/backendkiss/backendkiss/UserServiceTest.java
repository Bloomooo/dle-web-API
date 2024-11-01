package com.backendkiss.backendkiss;

import com.backendkiss.backendkiss.entity.User;
import com.backendkiss.backendkiss.repository.UserRepository;
import com.backendkiss.backendkiss.security.JwtUtil;
import com.backendkiss.backendkiss.services.PlayerService;
import com.backendkiss.backendkiss.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private PlayerService playerService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private JavaMailSender javaMailSender;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("testUser");
        user.setMail("test@gmail.com");
        user.setPassword("password123");
    }

    @Test
    void testCreateUser_Success() {
        User user = new User();
        user.setUsername("test");
        user.setMail("test@gmail.com");
        user.setPassword("password123");

        when(userRepository.existsByUsername(user.getUsername())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(playerService.createPlayer(any(User.class))).thenReturn(true);
        User result = userService.createUser(user);

        assertNotNull(result);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testCreateUser_Failure_NullUser() {
        User user = null;

        User result = userService.createUser(user);

        assertNull(result);
    }

    @Test
    void testCreateUser_Failure_Exception() {
        User user = new User();
        user.setUsername("test");
        user.setMail("test@gmail.com");

        when(userRepository.existsByUsername(user.getUsername())).thenReturn(false);
        doThrow(new RuntimeException()).when(userRepository).save(any(User.class));

        User result = userService.createUser(user);

        assertNull(result);
    }

    @Test
    void testGettersAndSetters() {
        User user = new User();
        user.setId(1);
        user.setUsername("John Doe");
        user.setMail("john.doe@example.com");

        assertEquals(1, user.getId());
        assertEquals("John Doe", user.getUsername());
        assertEquals("john.doe@example.com", user.getMail());
    }

    @Test
    void testFindById() {
        User user = new User();
        user.setId(1);
        when(userRepository.findById(Integer.valueOf(1))).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1);
        assertNotNull(foundUser);
        assertEquals(1, foundUser.getId());
    }

    @Test
    void testFindAll() {
        List<User> userList = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(userList);

        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
    }

    @Test
    void testLoginUser_Success() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword(new BCryptPasswordEncoder().encode("testPassword"));

        when(userRepository.findByUsername("testUser")).thenReturn(user);
        when(jwtUtil.generateToken("testUser")).thenReturn("mockedToken");
        when(bCryptPasswordEncoder.matches("testPassword", user.getPassword())).thenReturn(true);

        User loggedInUser = userService.loginUser("testUser", "testPassword");

        assertNotNull(loggedInUser);
        assertEquals("mockedToken", loggedInUser.getToken());
        verify(userRepository).findByUsername("testUser");
    }

    @Test
    void testLoginUser_Failure_InvalidPassword() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword(new BCryptPasswordEncoder().encode("correctPassword"));

        when(userRepository.findByUsername("testUser")).thenReturn(user);
        when(bCryptPasswordEncoder.matches("wrongPassword", user.getPassword())).thenReturn(false);

        User loggedInUser = userService.loginUser("testUser", "wrongPassword");

        assertNull(loggedInUser);
        verify(userRepository).findByUsername("testUser");
    }

    @Test
    void testLoginUser_Failure_UserNotFound() {
        when(userRepository.findByUsername("nonExistentUser")).thenReturn(null);

        User loggedInUser = userService.loginUser("nonExistentUser", "password");

        assertNull(loggedInUser);
        verify(userRepository).findByUsername("nonExistentUser");
    }

    @Test
    void testForgotPassword_Failure_Exception() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("newPassword");
        when(userRepository.findByUsername("testUser")).thenReturn(user);
        doThrow(new RuntimeException()).when(userRepository).save(any(User.class));

        boolean result = userService.forgotPassword(user);

        assertFalse(result);
    }

    @Test
    void testForgotPassword_Success() {
        String token = "mockedToken";
        user.setToken(token);

        when(jwtUtil.extractUsername(token)).thenReturn(user.getMail());
        when(userRepository.findByMail(user.getMail())).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);

        boolean result = userService.forgotPassword(user);

        assertTrue(result);
        verify(userRepository).save(user);
    }

    @Test
    void testForgotPassword_Failure_UserNotFound() {
        user.setToken("mockedToken");
        when(jwtUtil.extractUsername(user.getToken())).thenReturn(user.getMail());
        when(userRepository.findByMail(user.getMail())).thenReturn(null);

        boolean result = userService.forgotPassword(user);

        assertFalse(result);
    }

    @Test
    void testSearchUserByToken_Failure() {
        String token = "mockedToken";
        user.setToken(token);
        when(jwtUtil.extractUsername(token)).thenReturn(user.getMail());
        when(userRepository.findByUsername(anyString())).thenReturn(null);

        User foundUser = userService.searchUserByToken(user);

        assertNull(foundUser);
    }

    @Test
    void testExistsByUsername_Success() {
        String username = "testUser";
        when(userRepository.existsByUsername(username)).thenReturn(true);

        boolean exists = userService.existsByUsername(username);

        assertTrue(exists);
        verify(userRepository).existsByUsername(username);
    }

    @Test
    void testExistsByMail_Success() {
        String email = "test@gmail.com";
        when(userRepository.existsByMail(email)).thenReturn(true);

        boolean exists = userService.existsByMail(email);

        assertTrue(exists);
        verify(userRepository).existsByMail(email);
    }
}
