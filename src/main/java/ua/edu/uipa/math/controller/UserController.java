package ua.edu.uipa.math.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.uipa.math.dao.UserDao;
import ua.edu.uipa.math.dao.UserDetailsDao;
import ua.edu.uipa.math.enums.Language;
import ua.edu.uipa.math.exception.ResourceNotFoundException;
import ua.edu.uipa.math.model.user.User;
import ua.edu.uipa.math.model.user.UserDetails;
import ua.edu.uipa.math.model.user.UserFullResponse;
import ua.edu.uipa.math.model.user.UserResponse;
import ua.edu.uipa.math.swagger.UserDefinition;

import java.util.List;

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

    @GetMapping(
            value = "/{username}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        User user = userDao.findOneByUsername(username);
        List<UserDetails> details = userDetailsDao.findAllByUsername(username);

        if (user == null || details == null || details.isEmpty()) {
            throw new ResourceNotFoundException("User with username=" + username + " not found!");
        }

        return ResponseEntity.ok().body(new UserFullResponse(user, details));
    }
}
