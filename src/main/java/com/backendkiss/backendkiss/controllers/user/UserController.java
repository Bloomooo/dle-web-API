package com.backendkiss.backendkiss.controllers.user;

import com.backendkiss.backendkiss.services.UserService;
import com.backendkiss.backendkiss.entity.ResponseBody;
import com.backendkiss.backendkiss.entity.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseBody> createUser(@RequestBody User user) {
        try {
            if (user == null || user.getUsername() == null || user.getMail() == null || user.getPassword() == null) {
                return new ResponseEntity<>(new ResponseBody(
                        "Les données de l'utilisateur sont incomplètes", false, null), HttpStatus.BAD_REQUEST);
            }
            if (!user.getMail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                return new ResponseEntity<>(new ResponseBody(
                        "Format d'email invalide", false, null), HttpStatus.BAD_REQUEST);
            }
            if (userService.existsByUsername(user.getUsername())) {
                return new ResponseEntity<>(new ResponseBody(
                        "L'utilisateur existe déjà", false, null), HttpStatus.BAD_REQUEST);
            }
            if (userService.existsByMail(user.getMail())) {
                return new ResponseEntity<>(new ResponseBody(
                        "L'email existe déjà", false, null), HttpStatus.BAD_REQUEST);
            }
            User createUser = this.userService.createUser(user);

            return createUser != null ? new ResponseEntity<>(new ResponseBody(
                    "Utilisateur créé avec succès", true, createUser), HttpStatus.CREATED)
                    : new ResponseEntity<>(new ResponseBody(
                            "Échec de la création de l'utilisateur", false, null), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBody(
                    "Erreur interne du serveur : " + e.getMessage(), false, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseBody> loginUser(@RequestBody User user) {
        try {
            if (user == null || user.getUsername() == null || user.getPassword() == null) {
                return new ResponseEntity<>(new ResponseBody(
                        "Nom d'utilisateur ou mot de passe manquant", false, new User()), HttpStatus.BAD_REQUEST);
            }
            User loggedInUser = this.userService.loginUser(user.getUsername(), user.getPassword());
            return loggedInUser != null ? new ResponseEntity<>(new ResponseBody(
                    "Connexion réussie", true, loggedInUser), HttpStatus.OK)
                    : new ResponseEntity<>(new ResponseBody(
                            "Non autorisé", false, new User()), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBody(
                    "Erreur interne du serveur : " + e.getMessage(), false, new User()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ResponseBody> forgotPassword(@RequestBody User user) {
        try {
            if (user == null || user.getPassword() == null || user.getToken() == null) {
                return new ResponseEntity<>(new ResponseBody(
                        "Informations manquantes", false, null), HttpStatus.BAD_REQUEST);
            }
            if (!user.getToken().equals(userService.getToken())) {
                return new ResponseEntity<>(new ResponseBody(
                        "Jeton invalide", false, null), HttpStatus.BAD_REQUEST);
            }
            boolean isSent = this.userService.forgotPassword(user);
            return isSent ? new ResponseEntity<>(new ResponseBody(
                    "Mot de passe changé avec succès", true, null), HttpStatus.OK)
                    : new ResponseEntity<>(new ResponseBody(
                            "Échec de la réinitialisation du mot de passe", false, null), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBody(
                    "Erreur interne du serveur : " + e.getMessage(), false, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/search-user-by-token")
    public ResponseEntity<ResponseBody> searchUserByToken(@RequestBody User user) {

        try {
            if (user == null || user.getToken() == null) {
                return new ResponseEntity<>(new ResponseBody(
                        "Le jeton est requis", false, null), HttpStatus.BAD_REQUEST);
            }
            User foundUser = this.userService.searchUserByToken(user);
            return foundUser != null ? new ResponseEntity<>(new ResponseBody(
                    "Utilisateur trouvé", true, foundUser), HttpStatus.OK)
                    : new ResponseEntity<>(new ResponseBody(
                            "Utilisateur non trouvé", false, null), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBody(
                    "Erreur interne du serveur : " + e.getMessage(), false, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/mailsender")
    public ResponseEntity<ResponseBody> sendMail(@RequestBody User user) {
        try {
            if (user == null || user.getMail() == null) {
                return new ResponseEntity<>(new ResponseBody(
                        "L'email est requis", false, null), HttpStatus.BAD_REQUEST);
            }
            if (!user.getMail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                return new ResponseEntity<>(new ResponseBody(
                        "Format d'email invalide", false, null), HttpStatus.BAD_REQUEST);
            }
            if (!userService.existsByMail(user.getMail())) {
                return new ResponseEntity<>(new ResponseBody(
                        "Email non trouvé", false, null), HttpStatus.NOT_FOUND);
            }

            boolean isSent = this.userService.sendPasswordResetMail(user.getMail());

            return isSent ? new ResponseEntity<>(new ResponseBody(
                    "Email envoyé avec succès", true, null), HttpStatus.OK)
                    : new ResponseEntity<>(new ResponseBody(
                            "Échec de l'envoi de l'email", false, null), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBody(
                    "Erreur interne du serveur : " + e.getMessage(), false, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = this.userService.getAllUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping("getUser/{mail}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("mail") String mail) {
        User user = this.userService.getUserByMail(mail);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        this.userService.deleteUser(id);

    }
}
