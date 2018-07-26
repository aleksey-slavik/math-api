package ua.edu.uipa.math.unit.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.edu.uipa.math.dao.QuestionDao;
import ua.edu.uipa.math.model.Question;
import ua.edu.uipa.math.util.Criteria;
import ua.edu.uipa.math.utils.builder.QuestionBuilder;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ua.edu.uipa.math.utils.Workflow.defaultCriteria;

/*@RunWith(SpringRunner.class)
@DataJpaTest*/
public class QuestionDaoTest {

    /*@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private QuestionDao questionDao;*/

    @Mock
    private QuestionDao questionDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllByCriteria() throws Exception {
        //Question request = readFromResources("messages/question.json", Question.class);
        List<Question> expected = new QuestionBuilder().list(3);
        Criteria criteria = defaultCriteria();
        when(questionDao.findAllByCriteria(any(Criteria.class))).thenReturn(expected);

        List<Question> actual = questionDao.findAllByCriteria(criteria);

        verify(questionDao, times(1)).findAllByCriteria(criteria);
        verifyNoMoreInteractions(questionDao);
        assertEquals("Check list of questions", expected, actual);
    }

    /*@Test
    public void testFindAllByCriteria() {
        Question question = new QuestionBuilder().build();
        entityManager.persist(question);
        Optional<Question> response = questionDao.findById(question.getId());
        assertEquals(question, response.get());
    }*/
}
