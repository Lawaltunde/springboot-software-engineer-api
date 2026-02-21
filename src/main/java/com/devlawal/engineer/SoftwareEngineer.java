package com.devlawal.engineer;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class SoftwareEngineer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String stack;
    @Column(columnDefinition = "TEXT")
    private String recommendedLearningPath;


    public SoftwareEngineer() {
    }

    public SoftwareEngineer(Integer id, String name, String stack, String recommendedLearningPath) {
        this.id = id;
        this.name = name;
        this.stack = stack;
        this.recommendedLearningPath = recommendedLearningPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getRecommendedLearningPath() {
        return recommendedLearningPath;
    }

    public void setRecommendedLearningPath(String recommendedLearningPath) {
        this.recommendedLearningPath = recommendedLearningPath;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SoftwareEngineer that = (SoftwareEngineer) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(stack, that.stack) && Objects.equals(recommendedLearningPath, that.recommendedLearningPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, stack, recommendedLearningPath);
    }
}
