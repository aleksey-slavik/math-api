package ua.edu.uipa.math.model;

import org.hibernate.validator.constraints.Length;
import ua.edu.uipa.math.enums.Language;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class UserDetailsId implements Serializable {

    @NotNull
    @Length(max = 100)
    @Column(name = "username", nullable = false)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "language_code", nullable = false)
    private Language languageCode;

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
}
