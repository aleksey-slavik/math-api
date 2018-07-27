package ua.edu.uipa.math.lib.entity;

import java.util.Objects;

/**
 * Entity for testing purposes
 *
 * @author oleksii.slavik
 */
public class TestEntity {

    private Integer intField;
    private Long longField;
    private String stringField;

    public Integer getIntField() {
        return intField;
    }

    public void setIntField(Integer intField) {
        this.intField = intField;
    }

    public Long getLongField() {
        return longField;
    }

    public void setLongField(Long longField) {
        this.longField = longField;
    }

    public String getStringField() {
        return stringField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TestEntity that = (TestEntity) object;
        return Objects.equals(intField, that.intField) &&
                Objects.equals(longField, that.longField) &&
                Objects.equals(stringField, that.stringField);
    }

    @Override
    public int hashCode() {

        return Objects.hash(intField, longField, stringField);
    }
}
