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

    @Id
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
    @Length(max = 100)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Length(max = 250)
    @Column(name = "degree", nullable = false)
    private String degree;

    @NotNull
    @Length(max = 1000)
    @Column(name = "education", nullable = false)
    private String education;

    @NotNull
    @Length(max = 10000)
    @Column(name = "cv", nullable = false)
    private String cv;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public Boolean getActive() {
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
