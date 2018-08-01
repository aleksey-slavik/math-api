package ua.edu.uipa.math.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.edu.uipa.math.enums.Language;
import ua.edu.uipa.math.enums.Rank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String username;

    private Rank rank;

    private String email;

    private Language languageCode;

    private String name;

    private String degree;

    private String education;

    private String cv;

    private Boolean activated;

    private Long updated;

    public UserResponse(User user, UserDetails details) {
        this.username = user.getUsername();
        this.rank = user.getRank();
        this.email = user.getEmail();
        this.languageCode = details.getLanguageCode();
        this.name = details.getName();
        this.degree = details.getDegree();
        this.education = details.getEducation();
        this.cv = details.getCv();
        this.activated = user.getActivated();
        this.updated = user.getUpdated();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }
}
