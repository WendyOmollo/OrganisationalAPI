package models;

import java.util.Objects;

public class ClassifiedNews  extends News{

    public ClassifiedNews(String title, String details) {
        super(title, details);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ClassifiedNews that = (ClassifiedNews) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }
}
