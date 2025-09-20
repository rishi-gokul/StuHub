package com.example.studentapp.Module;

public class GradeModel {
    private double grade;
    private int credit;

    public GradeModel(double grade, int credit) {
        this.grade = grade;
        this.credit = credit;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
