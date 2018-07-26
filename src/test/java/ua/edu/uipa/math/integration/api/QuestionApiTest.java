package ua.edu.uipa.math.integration.api;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ua.edu.uipa.math.model.Question;
import ua.edu.uipa.math.utils.builder.QuestionBuilder;

import static org.junit.Assert.assertEquals;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionApiTest {

    //@Autowired
    private TestRestTemplate restTemplate;

    //@Test
    public void testGetQuestionById() {
        Question question = new QuestionBuilder().build();
        ResponseEntity<Question> responseEntity = restTemplate.getForEntity("/api/v1/questions/" + question.getId(), Question.class);
        Question response = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(question, response);
    }
}
