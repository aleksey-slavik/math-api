package ua.edu.uipa.math.dto.user;

import ua.edu.uipa.math.enums.Language;
import ua.edu.uipa.math.model.user.UserDetails;

public class UserTranslation {

    private Language languageCode;

    private String name;

    private String degree;

    private String position;

    private String education;

    private String cv;

    public UserTranslation(UserDetails userDetails) {
        this.languageCode = userDetails.getLanguageCode();
        this.name = userDetails.getName();
        this.degree = userDetails.getDegree();
        this.position = userDetails.getPosition();
        this.cv = userDetails.getCv();
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
