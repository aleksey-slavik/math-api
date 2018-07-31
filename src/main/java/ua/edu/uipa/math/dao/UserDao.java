package ua.edu.uipa.math.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.edu.uipa.math.enums.Language;
import ua.edu.uipa.math.model.Employee;

public interface UserDao extends JpaRepository<Employee, String> {

    @Query(value = "SELECT u.*, ut.* " +
            "FROM users u, user_translations ut " +
            "WHERE u.username = ut.username " +
            "AND ut.username = :username " +
            "AND ut.language_code = :language_code",
            nativeQuery = true)
    Employee findEmployeeByUsername(@Param("username") String username, @Param("language_code") Language language);
}
