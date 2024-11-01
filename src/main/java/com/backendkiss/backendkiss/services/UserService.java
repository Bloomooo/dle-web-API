package com.backendkiss.backendkiss.services;

import com.backendkiss.backendkiss.entity.User;
import com.backendkiss.backendkiss.entity.type.Role;
import com.backendkiss.backendkiss.repository.UserRepository;
import com.backendkiss.backendkiss.security.JwtUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;
    private final JavaMailSender javaMailSender;
    private final PlayerService playerService;
    private String resetToken;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil, PlayerService playerService,
            JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.playerService = playerService;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.jwtUtil = jwtUtil;
        this.javaMailSender = javaMailSender;
        this.resetToken = "";
    }
    @Transactional
    public User createUserTest(User user) {
        if (user == null || user.getUsername() == null || user.getMail() == null || user.getPassword() == null) {
            return null;
        }
        try {
            user.setRole(Role.USER);
            user.setPassword(hashPassword(user.getPassword()));
            userRepository.save(user);
            System.out.println(user);
            playerService.createPlayer(user);
            return user;
        } catch (Exception e) {
            return null;
        }
    }
    @Transactional
    public User createUser(User user) {
        if (user == null || user.getUsername() == null || user.getMail() == null || user.getPassword() == null) {
            return null;
        }
        try {
            user.setRole(Role.USER);
            user.setPassword(hashPassword(user.getPassword()));
            userRepository.save(user);
            System.out.println(user);
            playerService.createPlayer(user);
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
            user.setToken(jwtUtil.generateToken(username));
            return user;
        }
        return null;
    }

    @Transactional
    public boolean forgotPassword(User user) {
        try {
            String mail = jwtUtil.extractUsername(user.getToken());
            User userToReset = getUserByMail(mail);
            if (userToReset == null) {
                return false;
            }
            userToReset.setPassword(hashPassword(user.getPassword()));
            userRepository.save(userToReset);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public User searchUserByToken(User user) {
        String username = jwtUtil.extractUsername(user.getToken());
        return userRepository.findByUsername(username);
    }

    public boolean sendPasswordResetMail(String email) {
        try {
            resetToken = jwtUtil.generateToken(email);
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(email);
            helper.setSubject("Réinitialisation de votre mot de passe");
            String resetLink = "http://localhost:4200/resetPassword?token=" + resetToken;
            helper.setText("<html>" +
                    "<body style='font-family: Arial, sans-serif;'>" +
                    "<h2 style='color: #333;'>Bonjour,</h2>" +
                    "<p>Pour réinitialiser votre mot de passe, veuillez cliquer sur le lien ci-dessous:</p>" +
                    "<a href=\"" + resetLink
                    + "\" style='display: inline-block; padding: 10px 20px; margin: 10px 0; font-size: 16px; color: #fff; background-color: #007bff; text-decoration: none; border-radius: 5px;'>Réinitialiser mon mot de passe</a>"
                    +
                    "<p>Si vous n'avez pas demandé cela, ignorez ce mail.</p>" +
                    "<p>Merci,</p>" +
                    "<p>L'équipe de support</p>" +
                    "<img src='cid:iconAnimeDle' alt='animedle' style='width: 100px; height: auto; margin-top: 20px;'/>"
                    +
                    "</body>" +
                    "</html>",
                    true);

            FileSystemResource res = new FileSystemResource(new File("src/main/resources/images/iconAnimeDle.png"));
            helper.addInline("iconAnimeDle", res);

            javaMailSender.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }

    public User getUserById(int id) {
        return userRepository.findById(Integer.valueOf(id)).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserByMail(String mail) {
        return userRepository.findByMail(mail);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public void deleteUser(int userID){
         this.userRepository.deleteById(userID);
    }
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByMail(String mail) {
        return userRepository.existsByMail(mail);
    }

    private String hashPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public String getToken() {
        return resetToken;
    }

    public void setToken(String token) {
        this.resetToken = token;
    }
}
