package com.springboot.excel.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CustomerExportDTO {

    @ExcelProperty("ID")
    private Long id;

    @ExcelProperty("First Name")
    private String firstName;

    @ExcelProperty("Last Name")
    private String lastName;

    @ExcelProperty("Email")
    private String email;

    @ExcelProperty("Phone")
    private String phone;

    @ExcelProperty("Address")
    private String address;

    @ExcelProperty("City")
    private String city;

    @ExcelProperty("Country")
    private String country;

    @ExcelProperty("Job Title")
    private String jobTitle;

    @ExcelProperty("Join Date")
    private LocalDate joinDate;
}
