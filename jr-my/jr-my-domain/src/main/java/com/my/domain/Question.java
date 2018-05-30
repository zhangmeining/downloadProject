package com.my.domain;

import java.util.List;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/1221:48
 */
public class Question {
    String numberName;
    List<Long> jobs;
    Long courseId;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getNumberName() {
        return numberName;
    }

    public void setNumberName(String numberName) {
        this.numberName = numberName;
    }

    public List<Long> getJobs() {
        return jobs;
    }

    public void setJobs(List<Long> jobs) {
        this.jobs = jobs;
    }
}
