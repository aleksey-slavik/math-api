package ua.edu.uipa.math.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ua.edu.uipa.math.enums.Rank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name = "users")
public class User {

    @NotNull
    @Length(max = 100)
    @Column(name = "username", nullable = false)
    private String username;

    @NotNull
    @Length(max = 100)
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "rank", nullable = false)
    private Rank rank;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(?:[a-zA-Z]{2,6})$", message = "email is not valid")
    @Length(max = 250)
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "activated")
    private Boolean isActive;

    @Column(name = "updated", nullable = false)
    private Long updated;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }
}
