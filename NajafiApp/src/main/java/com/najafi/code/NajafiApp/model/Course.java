package com.najafi.code.NajafiApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    private Integer id;

    private String courseName;

    private String courseDescription;

    private String courseDuration;

    private String courseFees;
}
