package ua.edu.uipa.math.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ua.edu.uipa.math.enums.Language;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "user_translations")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "language_code", nullable = false)
    private Language language;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
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
}
