package ua.edu.uipa.math.dao;

import org.springframework.data.repository.CrudRepository;
import ua.edu.uipa.math.model.User;

public interface UserDao extends CrudRepository<User, String> {

    //@Query(value = "SELECT u, ut FROM users u LEFT JOIN user_translations ut ON u.username = ut.username WHERE u.username = ?1 AND ut.language_code = ?2")
    User findUserByUsername(String username);
}
