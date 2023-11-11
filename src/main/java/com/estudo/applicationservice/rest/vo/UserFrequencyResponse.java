package com.estudo.applicationservice.rest.vo;

import com.estudo.applicationservice.helpers.enums.DayOfWeek;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserFrequencyResponse {

    @JsonProperty
    private String accountId;
    @JsonProperty
    private String subjectName;
    @JsonProperty
    private String teacherName;
    @JsonProperty
    private String email;
    @JsonProperty
    private String grades;
    @JsonProperty
    private String notes;
    @JsonProperty
    private Double frequency;
    @JsonProperty
    private DayOfWeek day;
    @JsonProperty
    private Integer numberCurrentClasses;
    @JsonProperty
    private Integer presences;
    @JsonProperty
    private Integer absences;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }

    public String getGrades() { return grades; }

    public void setGrades(String grades) { this.grades = grades; }

    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }

    public Double getFrequency() {
        return frequency;
    }

    public void setFrequency(Double frequency) {
        this.frequency = frequency;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public Integer getNumberCurrentClasses() {
        return numberCurrentClasses;
    }

    public void setNumberCurrentClasses(Integer numberCurrentClasses) {
        this.numberCurrentClasses = numberCurrentClasses;
    }

    public Integer getPresences() {
        return presences;
    }

    public void setPresences(Integer presences) {
        this.presences = presences;
    }

    public Integer getAbsences() {
        return absences;
    }

    public void setAbsences(Integer absences) {
        this.absences = absences;
    }
}
