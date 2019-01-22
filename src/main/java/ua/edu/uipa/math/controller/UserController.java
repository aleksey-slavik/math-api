package ua.edu.uipa.math.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.uipa.math.dao.UserDao;
import ua.edu.uipa.math.dao.UserDetailsDao;
import ua.edu.uipa.math.dto.user.UserPassword;
import ua.edu.uipa.math.enums.Language;
import ua.edu.uipa.math.exception.ResourceNotFoundException;
import ua.edu.uipa.math.model.user.User;
import ua.edu.uipa.math.model.user.UserDetails;
import ua.edu.uipa.math.dto.user.UserFullResponse;
import ua.edu.uipa.math.dto.user.UserResponse;
import ua.edu.uipa.math.swagger.UserDefinition;
import ua.edu.uipa.math.util.PasswordGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Operations with users.
 *
 * @author oleksii.slavik
 * @version 2.0.0
 */
@RestController
@RequestMapping("api/v1/users")
public class UserController implements UserDefinition {

    private final UserDao userDao;

    private final UserDetailsDao userDetailsDao;

    @Autowired
    public UserController(UserDao userDao, UserDetailsDao userDetailsDao) {
        this.userDao = userDao;
        this.userDetailsDao = userDetailsDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping(
            value = "/{username}",
            headers = "Accept-Language",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getUserByUsernameAndLanguage(
            @PathVariable String username,
            @RequestHeader(value = "Accept-Language") Language language) {
        User user = userDao.findOneByUsername(username);
        UserDetails details = userDetailsDao.findOneByUsernameAndLanguageCode(username, language);

        if (user == null) {
            throw new ResourceNotFoundException("User with username=" + username + " not found!");
        }

        if (details == null) {
            throw new ResourceNotFoundException("Translation for language=" + language + " for user with username=" + username + " not found!");
        }

        return ResponseEntity.ok().body(new UserResponse(user, details));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping(
            headers = "Accept-Language",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getUsersListByLanguage(
            @RequestHeader(value = "Accept-Language") Language language) {
        List<User> users = userDao.findAll();
        List<UserResponse> responses = new ArrayList<>();

        for(User user : users) {
            UserDetails details = userDetailsDao.findOneByUsernameAndLanguageCode(user.getUsername(), language);
            if (details == null) {
                throw new ResourceNotFoundException("Translation for language=" + language + " for user with username=" + user.getUsername() + " not found!");
            }
            responses.add(new UserResponse(user, details));
        }

        return ResponseEntity.ok().body(responses);
    }

    /**
     * {@inheritDoc}
     */
    @Override //TODO make this method accessible only for super administrators
    @GetMapping(
            value = "/{username}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAllUserDataByUsername(
            @PathVariable String username) {
        User user = userDao.findOneByUsername(username);
        List<UserDetails> details = userDetailsDao.findAllByUsername(username);

        if (user == null) {
            throw new ResourceNotFoundException("User with username=" + username + " not found!");
        }

        if (details == null || details.isEmpty()) {
            throw new ResourceNotFoundException("Any translations for user with username=" + username + " not found!");
        }

        return ResponseEntity.ok().body(new UserFullResponse(user, details));
    }

    /**
     * {@inheritDoc}
     */
    @Override //TODO make this method accessible only for super administrators
    @PatchMapping(
            value = "/{username}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<?> resetPasswordByUsername(
            @PathVariable String username) {
        User user = userDao.findOneByUsername(username);

        if (user == null) {
            throw new ResourceNotFoundException("User with username=" + username + " not found!");
        }

        String newPassword = PasswordGenerator.generate();
        user.setPassword(newPassword);
        userDao.save(user);
        return ResponseEntity.ok().body(new UserPassword(newPassword));
    }
}
