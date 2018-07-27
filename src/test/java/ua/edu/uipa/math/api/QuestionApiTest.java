package ua.edu.uipa.math.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.edu.uipa.math.controller.QuestionController;
import ua.edu.uipa.math.dao.QuestionDao;
import ua.edu.uipa.math.model.Question;
import ua.edu.uipa.math.lib.builder.QuestionBuilder;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Contains tests for question controller
 *
 * @author oleksii.slavik
 */
public class QuestionApiTest {

    private MockMvc mockMvc;

    @Mock
    private QuestionDao questionDao;

    @InjectMocks
    private QuestionController questionController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();
    }

    /**
     * Test find question by id
     */
    @Test
    public void testGetQuestionById() throws Exception {
        Question question = new QuestionBuilder().build();
        when(questionDao.findById(question.getId())).thenReturn(Optional.of(question));

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/questions/" + question.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        Question response = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Question.class);
        assertEquals(question, response);

        verify(questionDao, times(1)).findById(question.getId());
        verifyNoMoreInteractions(questionDao);
    }
}
