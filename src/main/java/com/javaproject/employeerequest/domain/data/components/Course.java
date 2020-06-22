package com.javaproject.employeerequest.domain.data.components;

public class Course {

    private long courseId;
    private String courseName;

    public Course() {
    }

    public Course(long courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
