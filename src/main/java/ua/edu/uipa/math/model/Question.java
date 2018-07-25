package ua.edu.uipa.math.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

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
public final class Question {

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
    @Length(max = 250)
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * question description
     */
    @Length(max = 1000)
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * contacts of sender
     */
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

    /**
     * @param id question id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return date of creation of question
     */
    public Long getCreated() {
        return created;
    }

    /**
     * @param created date of creation of question
     */
    public void setCreated(Long created) {
        this.created = created;
    }
}
