package ua.edu.uipa.math.dto.user;

import ua.edu.uipa.math.enums.Language;
import ua.edu.uipa.math.enums.Rank;
import ua.edu.uipa.math.model.user.User;
import ua.edu.uipa.math.model.user.UserDetails;

public class UserResponse {

    private String username;

    private Rank rank;

    private String email;

    private Language languageCode;

    private String name;

    private String degree;

    private String position;

    private String education;

    private String cv;

    private Boolean isActive;

    private Long updatedAt;

    public UserResponse(User user, UserDetails details) {
        this.username = user.getUsername();
        this.rank = user.getRank();
        this.email = user.getEmail();
        this.languageCode = details.getLanguageCode();
        this.name = details.getName();
        this.degree = details.getDegree();
        this.education = details.getEducation();
        this.position = details.getPosition();
        this.cv = details.getCv();
        this.isActive = user.getActive();
        this.updatedAt = user.getUpdatedAt();
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
