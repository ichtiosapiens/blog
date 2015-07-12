package com.companyportal.company.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Company {

    private Long companyId;
    private String companyName;
    private Department department;


}
