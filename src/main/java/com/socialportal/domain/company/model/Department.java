package com.socialportal.domain.company.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;


public class Department {
    private String departmentName;
    private Set<String> employees;

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Set<String> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<String> employees) {
        this.employees = employees;
    }
}
