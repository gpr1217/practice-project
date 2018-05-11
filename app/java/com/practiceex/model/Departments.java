package com.practiceex.model;

/**
 * Created by Pratyu on 12/3/2017.
 */

public class Departments {

    int dept_no;
    String dept_name;
    String dept_description;

    public int getDept_no() {
        return dept_no;
    }

    public void setDept_no(int dept_no) {
        this.dept_no = dept_no;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getDept_description() {
        return dept_description;
    }

    public void setDept_description(String dept_description) {
        this.dept_description = dept_description;
    }

    @Override
    public String toString() {
        return "Departments{" +
                "dept_no=" + dept_no +
                ", dept_name='" + dept_name + '\'' +
                ", dept_description='" + dept_description + '\'' +
                '}';
    }
}
