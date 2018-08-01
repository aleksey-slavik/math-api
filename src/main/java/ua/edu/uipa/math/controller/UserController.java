package ua.edu.uipa.math.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.uipa.math.dao.UserDao;
import ua.edu.uipa.math.dao.UserDetailsDao;
import ua.edu.uipa.math.enums.Language;
import ua.edu.uipa.math.model.user.User;
import ua.edu.uipa.math.model.user.UserDetails;
import ua.edu.uipa.math.model.user.UserResponse;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserDao userDao;

    private final UserDetailsDao userDetailsDao;

    @Autowired
    public UserController(UserDao userDao, UserDetailsDao userDetailsDao) {
        this.userDao = userDao;
        this.userDetailsDao = userDetailsDao;
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getQuestionById(@PathVariable String username, @RequestHeader(value = "Accept-Language", defaultValue = "EN") Language language) {
        User user = userDao.findUserByUsername(username);
        UserDetails details = userDetailsDao.findOneByUsernameAndLanguageCode(username, language);
        return ResponseEntity.ok().body(new UserResponse(user, details));
    }
}
