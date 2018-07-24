package ua.edu.uipa.math.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * Contains all data access layer (DAL) for Question Service.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question")
public final class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Length(max = 250)
    @Column(name = "title", nullable = false)
    private String title;

    @Length(max = 1000)
    @Column(name = "description", nullable = false)
    private String description;

    @Length(max = 250)
    @Column(name = "contacts", nullable = false)
    private String contacts;

    @Column(name = "created", nullable = false)
    private Long created;

    public void timestamp() {
        created = System.currentTimeMillis();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }
}
