package com.estudo.applicationservice.domain.models;

import com.estudo.applicationservice.helpers.enums.DayOfWeek;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userFrequency")
public class UserFrequency {

    @Id
    private String id;
    private String accountId;
    private String subjectName;
    private String teacherName;
    private String email;
    private String grades;
    private String notes;
    private DayOfWeek day;
    private Double frequency;
    private Integer numberCurrentClasses;
    private Integer presences;
    private Integer absences;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(final String accountId) {
        this.accountId = accountId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(final String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(final String teacherName) {
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

    public void setDay(final DayOfWeek day) {
        this.day = day;
    }

    public Double getFrequency() {
        return frequency;
    }

    public void setFrequency(final Double frequency) {
        this.frequency = frequency;
    }

    public Integer getNumberCurrentClasses() {
        return numberCurrentClasses;
    }

    public void setNumberCurrentClasses(final Integer numberCurrentClasses) {
        this.numberCurrentClasses = numberCurrentClasses;
    }

    public void addNumberCurrentClasses(final Integer numberCurrentClasses, final Integer addANewClass){
        setNumberCurrentClasses(numberCurrentClasses+addANewClass);
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
