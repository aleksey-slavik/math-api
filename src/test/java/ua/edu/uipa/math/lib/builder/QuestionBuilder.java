package ua.edu.uipa.math.lib.builder;

import ua.edu.uipa.math.model.message.Question;
import ua.edu.uipa.math.lib.random.Generator;

/**
 * Implementation of {@link Builder} for {@link Question} entity
 *
 * @author oleksii.slavik
 */
public class QuestionBuilder implements Builder<Question> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Question build() {
        Question question = new Question();
        question.setId(Generator.id());
        question.setTitle(Generator.nextString(10));
        question.setDescription(Generator.nextString(100));
        question.setContacts(Generator.email("test", "mail.com"));
        question.setCreated(Generator.timestamp());
        return question;
    }
}
