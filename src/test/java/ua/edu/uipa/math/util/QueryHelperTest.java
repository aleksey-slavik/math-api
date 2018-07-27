package ua.edu.uipa.math.util;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.edu.uipa.math.lib.Generator;
import ua.edu.uipa.math.lib.builder.TestEntityBuilder;
import ua.edu.uipa.math.lib.entity.TestEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ua.edu.uipa.math.lib.Workflow.defaultCriteria;

/**
 * Contains tests for {@link QueryHelper}
 *
 * @author oleksii.slavik
 */
public class QueryHelperTest {

    @Mock
    private QueryHelper queryHelper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test find all questions by {@link Criteria}
     */
    @Test
    public void testFindAllByCriteria() {
        List<TestEntity> entities = new TestEntityBuilder().list(Generator.nextInt(1, 10));
        Criteria criteria = defaultCriteria();
        when(queryHelper.findAllByCriteria(any(Criteria.class), eq(TestEntity.class))).thenReturn(entities);

        List<TestEntity> response = queryHelper.findAllByCriteria(criteria, TestEntity.class);

        verify(queryHelper, times(1)).findAllByCriteria(criteria, TestEntity.class);
        verifyNoMoreInteractions(queryHelper);
        assertEquals("Check list of entities", entities, response);
    }
}
