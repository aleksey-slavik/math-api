package ua.edu.uipa.math.model.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ua.edu.uipa.math.enums.Language;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@IdClass(UserDetailsId.class)
@Table(name = "user_translations")
public class UserDetails {

    @Id
    @NotNull
    @Length(max = 100)
    @Column(name = "username", nullable = false)
    private String username;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "language_code", nullable = false)
    private Language languageCode;

    @NotNull
    @Length(max = 100)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Length(max = 250)
    @Column(name = "degree", nullable = false)
    private String degree;

    @NotNull
    @Length(max = 250)
    @Column(name = "position", nullable = false)
    private String position;

    @NotNull
    @Length(max = 1000)
    @Column(name = "education", nullable = false)
    private String education;

    @NotNull
    @Length(max = 10000)
    @Column(name = "cv", nullable = false)
    private String cv;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Language getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(Language languageCode) {
        this.languageCode = languageCode;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
}
