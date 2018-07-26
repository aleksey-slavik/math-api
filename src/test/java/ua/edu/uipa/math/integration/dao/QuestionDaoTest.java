package ua.edu.uipa.math.integration.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ua.edu.uipa.math.dao.QuestionDao;
import ua.edu.uipa.math.model.Question;
import ua.edu.uipa.math.utils.builder.QuestionBuilder;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class QuestionDaoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private QuestionDao questionDao;

    @Test
    public void testFindById() {
        Question question = new QuestionBuilder().build();
        entityManager.persist(question);
        Optional<Question> response = questionDao.findById(question.getId());
        assertEquals(question, response.get());
    }
}
