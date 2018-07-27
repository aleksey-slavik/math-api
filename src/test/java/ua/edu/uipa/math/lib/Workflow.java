package ua.edu.uipa.math.lib;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.edu.uipa.math.util.query.Criteria;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

/**
 * Contains workflow methods
 *
 * @author oleksii.slavik
 */
public class Workflow {

    /**
     * path of test resources
     */
    private static final String RESOURCES_ROOT_PATH = "src/test/resources/";

    /**
     * Parse entity from json file
     *
     * @param path  path to json file
     * @param clazz {@link Class} of entity
     * @param <T>   entity type
     * @return entity
     */
    public static <T> T readFromResources(String path, Class<T> clazz) throws IOException {
        String jsonData = readFromResources(path);
        return new ObjectMapper().readValue(jsonData, clazz);
    }

    /**
     * Create {@link Criteria} with default values
     *
     * @return {@link Criteria} with default values
     */
    public static Criteria defaultCriteria() {
        return new Criteria()
                .offset(0)
                .limit(10)
                .orderBy(new String[]{"+id"});
    }

    /**
     * Get string from json file
     *
     * @param path path to json file
     * @return json file as {@link String}
     */
    private static String readFromResources(String path) throws IOException {
        File file = new File(RESOURCES_ROOT_PATH + path);
        byte[] bytes = Files.readAllBytes(file.toPath());
        return new String(bytes, Charset.forName("UTF-8"));
    }
}
