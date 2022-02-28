package edu.isu.cs2263.hw02;

import java.io.Serializable;
import java.util.ArrayList;

public class Course {


    private final String name;
    private final int number;
    private final int credit;
    private final String departmentCode;
    // constructor method
    public Course(String name, int number, int credit, String departmentCode) {
        this.name = name;
        this.number = number;
        this.credit = credit;
        this.departmentCode = departmentCode;
    }

    //getter method
    public String getName() {
        return name;
    }
    // getter method
    public int getNumber() {
        return number;
    }
    //getter method
    public int getCredit() {
        return credit;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", credit=" + credit+
                ", departmentCode='" + departmentCode+ '\'' +
                '}';
    }


}