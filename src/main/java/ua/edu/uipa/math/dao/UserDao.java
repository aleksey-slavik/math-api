package ua.edu.uipa.math.dao;

import org.springframework.data.repository.CrudRepository;
import ua.edu.uipa.math.model.user.User;

public interface UserDao extends CrudRepository<User, String> {

    User findOneByUsername(String username);
}
