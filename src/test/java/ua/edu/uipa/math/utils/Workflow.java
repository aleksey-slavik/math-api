package ua.edu.uipa.math.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.edu.uipa.math.util.Criteria;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class Workflow {

    private static final String RESOURCES_ROOT_PATH = "src/test/resources/";

    public static <T> T readFromResources(String path, Class<T> clazz) throws IOException {
        String jsonData = readFromResources(path);
        return new ObjectMapper().readValue(jsonData, clazz);
    }

    public static Criteria defaultCriteria() {
        return new Criteria()
                .offset(0)
                .limit(10)
                .orderBy(new String[] {"+id"});
    }

    private static String readFromResources(String path) throws IOException {
        File file = new File(RESOURCES_ROOT_PATH + path);
        byte[] bytes = Files.readAllBytes(file.toPath());
        return new String(bytes, Charset.forName("UTF-8"));
    }
}
