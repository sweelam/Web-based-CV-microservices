package com.me.website.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "temp")
public class TempEntity {
    private int id;
    private String value;
    private String description;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "value", nullable = false, length = 100)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TempEntity that = (TempEntity) o;
        return id == that.id &&
                Objects.equals(value, that.value) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, value, description);
    }
}
