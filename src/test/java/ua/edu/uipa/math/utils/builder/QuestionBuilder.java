package ua.edu.uipa.math.utils.builder;

import ua.edu.uipa.math.model.Question;
import ua.edu.uipa.math.utils.Generator;

public class QuestionBuilder implements Builder<Question> {

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
