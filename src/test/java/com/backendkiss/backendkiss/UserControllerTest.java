package com.backendkiss.backendkiss;

import com.backendkiss.backendkiss.controllers.user.UserController;
import com.backendkiss.backendkiss.entity.User;
import com.backendkiss.backendkiss.repository.UserRepository;
import com.backendkiss.backendkiss.security.JwtUtil;
import com.backendkiss.backendkiss.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {
    private String URLBASE = "http://localhost:8080/users";

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserController userController;

    @Autowired
    private ObjectMapper objectMapper;

    private static String uniqueUsername;

    @BeforeAll
    public static void init() {
        uniqueUsername = "user" + System.currentTimeMillis();
    }

    @Test
    @Order(1)
    void testCreateUser_Success() throws Exception {
        User user = new User();
        user.setUsername(uniqueUsername);
        user.setMail(uniqueUsername + "@example.com");
        user.setPassword("testPassword123");

        when(userService.existsByUsername(user.getUsername())).thenReturn(false);
        when(userService.existsByMail(user.getMail())).thenReturn(false);
        when(userService.createUser(any(User.class))).thenReturn(user);

        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(content().json(
                        "{\"message\":\"Utilisateur créé avec succès\",\"success\":true,\"data\":null}")); // Mise
        // à
        // jour
        // ici
    }

    @Test
    @Order(2)
    void testCreateUser_Failure_Email_Exist() throws Exception {
        User user = new User();
        user.setUsername("user1");
        user.setMail(uniqueUsername + "@example.com");
        user.setPassword("testPassword123");

        when(userService.existsByUsername(user.getUsername())).thenReturn(false);
        when(userService.existsByMail(user.getMail())).thenReturn(false);
        when(userService.createUser(any(User.class))).thenReturn(user);

        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(
                        "{\"message\":\"L'email existe déjà\",\"success\":false,\"data\":null}"));
    }

    @Test
    void testCreateUser_Failure_NullUsername() throws Exception {
        User user = new User();
        user.setUsername(null);
        user.setMail("john.doe@example.com");
        user.setPassword("password123");

        String userJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(
                        "{\"message\":\"Les données de l'utilisateur sont incomplètes\",\"success\":false,\"data\":null}")); // Mise
        // à
        // jour
        // ici
    }

    @Test
    void testCreateUser_InvalidInput() throws Exception {
        User user = new User();

        String userJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(
                        "{\"message\":\"Les données de l'utilisateur sont incomplètes\",\"success\":false,\"data\":null}")); // Mise
        // à
        // jour
        // ici
    }

    @Test
    @Order(3)
    void testLoginUser_Success() throws Exception {
        User user = new User();
        user.setUsername(uniqueUsername);
        user.setPassword("testPassword123");

        when(userService.loginUser(uniqueUsername, "testPassword123")).thenReturn(user);

        String userJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"message\":\"Connexion réussie\",\"success\":true,\"data\":{\"username\":\""
                                + uniqueUsername + "\"}}"));
    }

    @Test
    void testLoginUser_Failure() throws Exception {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("wrongPassword");

        when(userService.loginUser("testUser", "wrongPassword")).thenReturn(null);

        String userJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isUnauthorized())
                .andExpect(content().json(
                        "{\"message\":\"Non autorisé\",\"success\":false,\"data\":{\"id\":0,\"role\":null,\"username\":null,\"password\":null,\"mail\":null,\"token\":null}}")); // Mise
        // à
        // jour
        // ici
    }

    @Test
    void testLoginUser_InvalidInput() throws Exception {
        User user = new User();

        String userJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(
                        "{\"message\":\"Nom d'utilisateur ou mot de passe manquant\",\"success\":false,\"data\":{\"id\":0,\"role\":null,\"username\":null,\"password\":null,\"mail\":null,\"token\":null}}")); // Mise
        // à
        // jour
        // ici
    }

    @Test
    void testForgotPassword_InvalidInput() throws Exception {
        User user = new User();

        String userJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/users/forgot-password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(
                        "{\"message\":\"Informations manquantes\",\"success\":false,\"data\":null}")); // Mise
        // à
        // jour
        // ici
    }

    @Test
    void testSearchUserByToken_Failure() throws Exception {
        User user = new User();
        user.setToken(null);

        String userJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/users/search-user-by-token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(
                        "{\"message\":\"Le jeton est requis\",\"success\":false,\"data\":null}")); // Mise
        // à
        // jour
        // ici
    }

    @Test
    void testCreateUser_Failure_UserExists() throws Exception {
        User user = new User();
        user.setUsername(uniqueUsername);
        user.setMail(uniqueUsername + "@example.com");
        user.setPassword("password123");

        when(userService.existsByUsername(user.getUsername())).thenReturn(true);

        String userJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(
                        "{\"message\":\"L'utilisateur existe déjà\",\"success\":false,\"data\":null}")); // Mise
        // à
        // jour
        // ici
    }

    @Test
    void testForgotPassword_Failure_InvalidToken() throws Exception {
        String resetToken = UUID.randomUUID().toString();
        User user = new User();
        user.setMail(uniqueUsername + "@example.com");
        user.setPassword("newPassword123");
        user.setToken("wrongToken");
        userService.setToken(resetToken);

        when(userService.existsByMail(user.getMail())).thenReturn(true);

        String userJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/users/forgot-password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(
                        "{\"message\":\"Jeton invalide\",\"success\":false,\"data\":null}"));
    }

    @Test
    void testCreateUser_Failure_InvalidEmailFormat() throws Exception {
        User user = new User();
        user.setUsername("testUser");
        user.setMail("invalid-email-format");
        user.setPassword("password123");

        String userJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(
                        "{\"message\":\"Format d'email invalide\",\"success\":false,\"data\":null}")); // Mise
        // à
        // jour
        // ici
    }

    @Test
    @Order(4)
    void listeUsersLength() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<User>> response = restTemplate.exchange(URLBASE,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<User>>() {
                });
        List<User> users = response.getBody();
        assertTrue((users.size()) >= 2);
    }
}
