package ua.edu.uipa.math.api;

import com.fasterxml.jackson.core.type.TypeReference;
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
import ua.edu.uipa.math.controller.ErrorController;
import ua.edu.uipa.math.controller.QuestionController;
import ua.edu.uipa.math.dao.QuestionDao;
import ua.edu.uipa.math.lib.builder.QuestionBuilder;
import ua.edu.uipa.math.lib.random.Generator;
import ua.edu.uipa.math.model.error.Error;
import ua.edu.uipa.math.model.message.Question;
import ua.edu.uipa.math.util.query.Criteria;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Contains tests for question controller
 *
 * @author oleksii.slavik
 */
public class QuestionApiTest {

    /**
     * permissible error value for date comparison(in milliseconds)
     */
    private static final long DELTA = 1000L;

    private MockMvc mockMvc;

    private QuestionBuilder builder;

    @Mock
    private QuestionDao questionDao;

    @InjectMocks
    private QuestionController questionController;

    @InjectMocks
    private ErrorController errorController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(questionController, errorController).build();
        builder = new QuestionBuilder();
    }

    /**
     * Test find question by id
     */
    @Test
    public void testGetQuestionById() throws Exception {
        Question question = builder.build();
        when(questionDao.findById(question.getId())).thenReturn(Optional.of(question));

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/questions/{id}", question.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Question response = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Question.class);
        assertEquals(question, response);

        verify(questionDao, times(1)).findById(question.getId());
        verifyNoMoreInteractions(questionDao);
    }

    /**
     * Test question by id not found
     */
    @Test
    public void testGetQuestionByIdNotFound() throws Exception {
        Long id = Generator.nextLong(1, 100);
        when(questionDao.findById(anyLong())).thenReturn(Optional.empty());

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/questions/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        Error error = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Error.class);
        assertEquals("ResourceNotFoundException", error.getCode());
        assertEquals("Question with id=" + id + " not found!", error.getMessage());
        assertTrue("Check error creation date", (System.currentTimeMillis() - error.getTimestamp()) < DELTA);

        verify(questionDao, times(1)).findById(anyLong());
        verifyNoMoreInteractions(questionDao);
    }

    @Test
    public void testGetQuestionList() throws Exception {
        List<Question> questions = builder.list(Generator.nextInt(1, 10));
        when(questionDao.findAllByCriteria(any(Criteria.class))).thenReturn(questions);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/questions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<Question> response = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Question>>(){});
        assertEquals(questions, response);

        verify(questionDao, times(1)).findAllByCriteria(any(Criteria.class));
        verifyNoMoreInteractions(questionDao);
    }

    /**
     * Test create new question
     */
    @Test
    public void testPostQuestion() throws Exception {
        Question question = builder.build();
        when(questionDao.save(any(Question.class))).thenReturn(question);

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(question)))
                .andExpect(status().isOk())
                .andReturn();

        Question response = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Question.class);
        assertEquals(question, response);

        verify(questionDao, times(1)).save(any(Question.class));
        verifyNoMoreInteractions(questionDao);
    }

    /**
     * Test create new question without title
     */
    @Test
    public void testPostQuestionWithoutTitle() throws Exception {
        Question question = builder.build();
        question.setTitle(null);

        mockMvc.perform(post("/api/v1/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(question)))
                .andExpect(status().isBadRequest());

        verify(questionDao, times(0)).save(any(Question.class));
        verifyNoMoreInteractions(questionDao);
    }

    /**
     * Test create new question with very long title
     */
    @Test
    public void testPostQuestionWithLongTitle() throws Exception {
        Question question = builder.build();
        question.setTitle(Generator.nextString(Generator.nextInt(251, 1000)));

        mockMvc.perform(post("/api/v1/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(question)))
                .andExpect(status().isBadRequest());

        verify(questionDao, times(0)).save(any(Question.class));
        verifyNoMoreInteractions(questionDao);
    }

    /**
     * Test create new question without description
     */
    @Test
    public void testPostQuestionWithoutDescription() throws Exception {
        Question question = builder.build();
        question.setDescription(null);

        mockMvc.perform(post("/api/v1/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(question)))
                .andExpect(status().isBadRequest());

        verify(questionDao, times(0)).save(any(Question.class));
        verifyNoMoreInteractions(questionDao);
    }

    /**
     * Test create new question with very long description
     */
    @Test
    public void testPostQuestionWithLongDescription() throws Exception {
        Question question = builder.build();
        question.setTitle(Generator.nextString(Generator.nextInt(10001, 15000)));

        mockMvc.perform(post("/api/v1/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(question)))
                .andExpect(status().isBadRequest());

        verify(questionDao, times(0)).save(any(Question.class));
        verifyNoMoreInteractions(questionDao);
    }

    /**
     * Test create new question without contacts
     */
    @Test
    public void testPostQuestionWithoutContacts() throws Exception {
        Question question = builder.build();
        question.setContacts(null);

        mockMvc.perform(post("/api/v1/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(question)))
                .andExpect(status().isBadRequest());

        verify(questionDao, times(0)).save(any(Question.class));
        verifyNoMoreInteractions(questionDao);
    }

    /**
     * Test create new question with very long contacts
     */
    @Test
    public void testPostQuestionWithLongContacts() throws Exception {
        Question question = builder.build();
        question.setContacts(Generator.nextString(Generator.nextInt(251, 1000)));

        mockMvc.perform(post("/api/v1/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(question)))
                .andExpect(status().isBadRequest());

        verify(questionDao, times(0)).save(any(Question.class));
        verifyNoMoreInteractions(questionDao);
    }

    /**
     * Test update question
     */
    @Test
    public void testPutQuestionById() throws Exception {
        Question question = builder.build();
        when(questionDao.findById(question.getId())).thenReturn(Optional.of(question));
        when(questionDao.save(any(Question.class))).thenReturn(question);

        MvcResult mvcResult = mockMvc.perform(put("/api/v1/questions/{id}", question.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(question)))
                .andExpect(status().isOk())
                .andReturn();

        Question response = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Question.class);
        assertEquals(question, response);

        verify(questionDao, times(1)).findById(anyLong());
        verify(questionDao, times(1)).save(any(Question.class));
        verifyNoMoreInteractions(questionDao);
    }

    /**
     * Test update question by unexcited id
     */
    @Test
    public void testPutQuestionByIdNotFound() throws Exception {
        Question question = builder.build();
        when(questionDao.findById(anyLong())).thenReturn(Optional.empty());

        MvcResult mvcResult = mockMvc.perform(put("/api/v1/questions/{id}", question.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(question)))
                .andExpect(status().isBadRequest())
                .andReturn();

        Error error = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Error.class);
        assertEquals("ResourceNotFoundException", error.getCode());
        assertEquals("Can't update question with id=" + question.getId() + " because it not found!", error.getMessage());
        assertTrue("Check error creation date", (System.currentTimeMillis() - error.getTimestamp()) < DELTA);

        verify(questionDao, times(1)).findById(anyLong());
        verify(questionDao, times(0)).save(any(Question.class));
        verifyNoMoreInteractions(questionDao);
    }

    /**
     * Test update question without title
     */
    @Test
    public void testPutQuestionWithoutTitle() throws Exception {
        Question question = builder.build();
        question.setTitle(null);

        mockMvc.perform(put("/api/v1/questions/{id}", question.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(question)))
                .andExpect(status().isBadRequest());

        verify(questionDao, times(0)).findById(anyLong());
        verify(questionDao, times(0)).save(any(Question.class));
        verifyNoMoreInteractions(questionDao);
    }

    /**
     * Test update question with very long title
     */
    @Test
    public void testPutQuestionWithLongTitle() throws Exception {
        Question question = builder.build();
        question.setTitle(Generator.nextString(Generator.nextInt(251, 1000)));

        mockMvc.perform(put("/api/v1/questions/{id}", question.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(question)))
                .andExpect(status().isBadRequest());

        verify(questionDao, times(0)).findById(anyLong());
        verify(questionDao, times(0)).save(any(Question.class));
        verifyNoMoreInteractions(questionDao);
    }

    /**
     * Test update question without description
     */
    @Test
    public void testPutQuestionWithoutDescription() throws Exception {
        Question question = builder.build();
        question.setDescription(null);

        mockMvc.perform(put("/api/v1/questions/{id}", question.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(question)))
                .andExpect(status().isBadRequest());

        verify(questionDao, times(0)).findById(anyLong());
        verify(questionDao, times(0)).save(any(Question.class));
        verifyNoMoreInteractions(questionDao);
    }

    /**
     * Test update question with very long description
     */
    @Test
    public void testPutQuestionWithLongDescription() throws Exception {
        Question question = builder.build();
        question.setTitle(Generator.nextString(Generator.nextInt(10001, 15000)));

        mockMvc.perform(put("/api/v1/questions/{id}", question.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(question)))
                .andExpect(status().isBadRequest());

        verify(questionDao, times(0)).findById(anyLong());
        verify(questionDao, times(0)).save(any(Question.class));
        verifyNoMoreInteractions(questionDao);
    }

    /**
     * Test update question without contacts
     */
    @Test
    public void testPutQuestionWithoutContacts() throws Exception {
        Question question = builder.build();
        question.setContacts(null);

        mockMvc.perform(put("/api/v1/questions/{id}", question.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(question)))
                .andExpect(status().isBadRequest());

        verify(questionDao, times(0)).findById(anyLong());
        verify(questionDao, times(0)).save(any(Question.class));
        verifyNoMoreInteractions(questionDao);
    }

    /**
     * Test update question with very long contacts
     */
    @Test
    public void testPutQuestionWithLongContacts() throws Exception {
        Question question = builder.build();
        question.setContacts(Generator.nextString(Generator.nextInt(251, 1000)));

        mockMvc.perform(put("/api/v1/questions/{id}", question.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(question)))
                .andExpect(status().isBadRequest());

        verify(questionDao, times(0)).findById(anyLong());
        verify(questionDao, times(0)).save(any(Question.class));
        verifyNoMoreInteractions(questionDao);
    }

    /**
     * Test delete question by id
     */
    @Test
    public void testDeleteQuestionById() throws Exception {
        Question question = builder.build();
        when(questionDao.findById(question.getId())).thenReturn(Optional.of(question));
        doNothing().when(questionDao).deleteById(question.getId());

        MvcResult mvcResult = mockMvc.perform(delete("/api/v1/questions/{id}", question.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Question response = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Question.class);
        assertEquals(question, response);

        verify(questionDao, times(1)).findById(anyLong());
        verify(questionDao, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(questionDao);
    }

    /**
     * Test delete question by unexcited id
     */
    @Test
    public void testDeleteQuestionByIdNotFound() throws Exception {
        Long id = Generator.nextLong(1, 100);
        when(questionDao.findById(id)).thenReturn(Optional.empty());

        MvcResult mvcResult = mockMvc.perform(delete("/api/v1/questions/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        Error error = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Error.class);
        assertEquals("ResourceNotFoundException", error.getCode());
        assertEquals("Can't delete question with id=" + id + " because it not found!", error.getMessage());
        assertTrue("Check error creation date", (System.currentTimeMillis() - error.getTimestamp()) < DELTA);


        verify(questionDao, times(1)).findById(anyLong());
        verify(questionDao, times(0)).deleteById(anyLong());
        verifyNoMoreInteractions(questionDao);
    }
}
