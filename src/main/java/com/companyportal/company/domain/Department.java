package com.companyportal.company.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Department {
    private String departmentName;
    private Set<Long> employees;
}
