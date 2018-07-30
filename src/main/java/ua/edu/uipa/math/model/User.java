package ua.edu.uipa.math.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ua.edu.uipa.math.enums.Degree;
import ua.edu.uipa.math.enums.Position;
import ua.edu.uipa.math.enums.Rank;

import javax.persistence.Entity;
import javax.persistence.Table;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "users")
public class User {

    private String username;

    private String password;

    private String name;

    private Position position;

    private Rank rank;

    private Degree degree;

    private String education;

    private String email;

    private String cv;

    private boolean isActive;

    private Long updated;
}
