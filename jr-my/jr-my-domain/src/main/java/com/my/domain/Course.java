package com.my.domain;

public class Course {
    private Long  courseId;

    private String courseName;

    private String courseCreator;

    public Long  getCourseId() {
        return courseId;
    }

    public void setCourseId(Long  courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public String getCourseCreator() {
        return courseCreator;
    }

    public void setCourseCreator(String courseCreator) {
        this.courseCreator = courseCreator == null ? null : courseCreator.trim();
    }
}