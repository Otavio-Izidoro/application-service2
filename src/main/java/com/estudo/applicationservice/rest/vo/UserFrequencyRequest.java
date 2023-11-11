package com.estudo.applicationservice.rest.vo;

import com.estudo.applicationservice.helpers.enums.DayOfWeek;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserFrequencyRequest {
    @JsonProperty
    @NotBlank
    @NotNull
    @NotEmpty
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
    @JsonProperty("dayOfWeek")
    @NotBlank
    @NotNull
    @NotEmpty
    private DayOfWeek day;


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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }
}
