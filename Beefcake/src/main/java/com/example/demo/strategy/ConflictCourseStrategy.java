package com.example.demo.strategy;


import com.example.demo.entity.CourseEntity;

import java.io.Serializable;


public class ConflictCourseStrategy extends Strategy implements Serializable {

    private CourseEntity course;

    public ConflictCourseStrategy() {
        this.strategyType="ConflictCourseStrategy";
    }

    public ConflictCourseStrategy(Long id, CourseEntity course) {
        super(id);
        this.course = course;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "ConflictCourseStrategy{" +
                "course=" + course +
                '}';
    }
}
