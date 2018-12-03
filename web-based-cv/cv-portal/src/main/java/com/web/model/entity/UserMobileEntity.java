package com.web.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_mobile", schema = "sweprofile", catalog = "")
public class UserMobileEntity {
    private int id;
    private String value;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMobileEntity that = (UserMobileEntity) o;
        return id == that.id &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, value);
    }
}
