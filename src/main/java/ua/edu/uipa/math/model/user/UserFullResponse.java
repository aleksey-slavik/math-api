package ua.edu.uipa.math.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.edu.uipa.math.enums.Rank;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFullResponse {

    private String username;

    private String password;

    private Rank rank;

    private String email;

    private List<UserDetails> details;

    private Boolean activated;

    private Long updated;

    public UserFullResponse(User user, List<UserDetails> details) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.rank = user.getRank();
        this.email = user.getEmail();
        this.details = details;
        this.activated = user.getActivated();
        this.updated = user.getUpdated();
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
