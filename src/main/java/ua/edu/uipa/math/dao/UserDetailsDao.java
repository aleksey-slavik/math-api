package ua.edu.uipa.math.dao;

import org.springframework.data.repository.CrudRepository;
import ua.edu.uipa.math.enums.Language;
import ua.edu.uipa.math.model.user.UserDetails;
import ua.edu.uipa.math.model.user.UserDetailsId;

import java.util.List;

public interface UserDetailsDao extends CrudRepository<UserDetails, UserDetailsId> {

    UserDetails findOneByUsernameAndLanguageCode(String username, Language languageCode);

    List<UserDetails> findAllByUsername(String username);
}
