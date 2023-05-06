package com.company.entity;


import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmploymentHistory {
    private Integer id;
    private String header;
    private String jobDescription;
    private Date beginDate;
    private Date endDate;
    private User user;
}
