package ua.edu.uipa.math.dto.user;

import ua.edu.uipa.math.enums.Rank;
import ua.edu.uipa.math.model.user.User;
import ua.edu.uipa.math.model.user.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

public class UserFullResponse {

    private String username;

    private Rank rank;

    private String email;

    private Boolean isActive;

    private Long updatedAt;

    private List<UserTranslation> translations;

    public UserFullResponse(User user, List<UserDetails> details) {
        this.username = user.getUsername();
        this.rank = user.getRank();
        this.email = user.getEmail();
        this.isActive = user.getActive();
        this.updatedAt = user.getUpdatedAt();
        this.translations = details.stream().map(UserTranslation::new).collect(Collectors.toList());
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

    public List<UserTranslation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<UserTranslation> translations) {
        this.translations = translations;
    }
}
