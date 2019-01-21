package ua.edu.uipa.math.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.uipa.math.model.user.User;

public interface UserDao extends JpaRepository<User, String> {

    User findOneByUsername(String username);
}
