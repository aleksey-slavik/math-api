package ua.edu.uipa.math.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.uipa.math.dao.UserDao;
import ua.edu.uipa.math.dao.UserDetailsDao;
import ua.edu.uipa.math.enums.Language;
import ua.edu.uipa.math.model.UserDetails;
import ua.edu.uipa.math.model.UserDetailsId;

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
    public ResponseEntity<?> getQuestionById(@PathVariable String username, @RequestParam(defaultValue = "EN") Language language) {
        UserDetailsId key = new UserDetailsId();
        key.setUsername(username);
        key.setLanguageCode(language);
        UserDetails user = userDetailsDao.findOneByPrimaryKey(key);
        return ResponseEntity.ok().body(user);
    }
}
