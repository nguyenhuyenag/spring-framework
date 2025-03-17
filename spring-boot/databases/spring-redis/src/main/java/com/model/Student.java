package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student implements java.io.Serializable {

    private String id;
    private String name;
    private int age;

}
