package com.me.website.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_skills", schema = "sweprofile", catalog = "")
public class UserSkillsEntity {
    private int userId;
    private String skills;
    private int id;

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "skills", nullable = false, length = 190)
    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSkillsEntity that = (UserSkillsEntity) o;
        return userId == that.userId &&
                Objects.equals(skills, that.skills);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, skills);
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
