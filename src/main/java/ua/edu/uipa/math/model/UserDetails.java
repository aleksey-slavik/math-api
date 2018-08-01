package ua.edu.uipa.math.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "user_translations")
public class UserDetails {

    @EmbeddedId
    private UserDetailsId primaryKey;

    @Valid
    @MapsId("username")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

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

    public UserDetailsId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(UserDetailsId primaryKey) {
        this.primaryKey = primaryKey;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
