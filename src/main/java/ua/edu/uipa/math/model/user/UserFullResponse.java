package ua.edu.uipa.math.model.user;

import ua.edu.uipa.math.enums.Rank;

import java.util.List;

public class UserFullResponse {

    private String username;

    private String password;

    private Rank rank;

    private String email;

    private List<UserDetails> details;

    private Boolean isActive;

    private Long updatedAt;

    public UserFullResponse(User user, List<UserDetails> details) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.rank = user.getRank();
        this.email = user.getEmail();
        this.details = details;
        this.isActive = user.getActive();
        this.updatedAt = user.getUpdatedAt();
    }

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

    public List<UserDetails> getDetails() {
        return details;
    }

    public void setDetails(List<UserDetails> details) {
        this.details = details;
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
