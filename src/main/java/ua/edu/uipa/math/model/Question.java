package ua.edu.uipa.math.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Contains all data access layer (DAL) for Question Service.
 *
 * @author oleksii.slavik
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question")
public class Question {

    /**
     * question id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * question title
     */
    @NotNull
    @Length(max = 250)
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * question description
     */
    @NotNull
    @Length(max = 10000)
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * contacts of sender
     */
    @NotNull
    @Length(max = 250)
    @Column(name = "contacts", nullable = false)
    private String contacts;

    /**
     * date of creation of question (in milliseconds)
     */
    @Column(name = "created", nullable = false)
    private Long created;

    /**
     * Save date of creation of question
     */
    public void timestamp() {
        created = System.currentTimeMillis();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) &&
                Objects.equals(title, question.title) &&
                Objects.equals(description, question.description) &&
                Objects.equals(contacts, question.contacts) &&
                Objects.equals(created, question.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, contacts, created);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", contacts='" + contacts + '\'' +
                ", created=" + created +
                '}';
    }
}
