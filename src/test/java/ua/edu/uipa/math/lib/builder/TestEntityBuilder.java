package ua.edu.uipa.math.lib.builder;

import ua.edu.uipa.math.lib.random.Generator;
import ua.edu.uipa.math.lib.entity.TestEntity;

/**
 * Implementation of {@link Builder} for {@link TestEntity}
 *
 * @author oleksii.slavik
 */
public class TestEntityBuilder implements Builder<TestEntity> {
    @Override
    public TestEntity build() {
        TestEntity testObject = new TestEntity();
        testObject.setIntField(Generator.nextInt(-10, 10));
        testObject.setLongField(Generator.nextLong(-10L, 10L));
        testObject.setStringField(Generator.nextString(10));
        return testObject;
    }
}