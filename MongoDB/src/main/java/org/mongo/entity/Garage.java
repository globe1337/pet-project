package org.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

@Document(collection = "garage")
public class Garage {
    @Id
    private String id;
    @Field("is_active")
    private Boolean isActive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Garage garage = (Garage) o;
        return Objects.equals(id, garage.id) && Objects.equals(isActive, garage.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isActive);
    }

    @Override
    public String toString() {
        return "Garage{" +
                "id=" + id +
                ", isActive=" + isActive +
                '}';
    }
}
