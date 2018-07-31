package ua.edu.uipa.math.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.uipa.math.dao.UserDao;
import ua.edu.uipa.math.enums.Language;
import ua.edu.uipa.math.model.User;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getQuestionById(@PathVariable String username, @RequestParam(defaultValue = "EN") Language language) {
        User user = userDao.findUserByUsername(username, String.valueOf(language));
        return ResponseEntity.ok().body(user);
    }
}
