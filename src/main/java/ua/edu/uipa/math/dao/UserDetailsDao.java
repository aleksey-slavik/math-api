package ua.edu.uipa.math.dao;

import org.springframework.data.repository.CrudRepository;
import ua.edu.uipa.math.model.UserDetails;
import ua.edu.uipa.math.model.UserDetailsId;

public interface UserDetailsDao extends CrudRepository<UserDetails, UserDetailsId> {

    UserDetails findOneByPrimaryKey(UserDetailsId primaryKey);
}
