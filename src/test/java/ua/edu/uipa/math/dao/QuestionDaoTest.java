package ua.edu.uipa.math.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.edu.uipa.math.model.Question;
import ua.edu.uipa.math.util.Criteria;
import ua.edu.uipa.math.lib.builder.QuestionBuilder;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ua.edu.uipa.math.lib.Workflow.defaultCriteria;

/**
 * Contains tests for custom {@link QuestionDao} methods
 *
 * @author oleksii.slavik
 */
public class QuestionDaoTest {

    @Mock
    private QuestionDao questionDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test find all questions by {@link Criteria}
     */
    @Test
    public void testFindAllByCriteria() {
        List<Question> expected = new QuestionBuilder().list(3);
        Criteria criteria = defaultCriteria();
        when(questionDao.findAllByCriteria(any(Criteria.class))).thenReturn(expected);

        List<Question> actual = questionDao.findAllByCriteria(criteria);

        verify(questionDao, times(1)).findAllByCriteria(criteria);
        verifyNoMoreInteractions(questionDao);
        assertEquals("Check list of questions", expected, actual);
    }
}
