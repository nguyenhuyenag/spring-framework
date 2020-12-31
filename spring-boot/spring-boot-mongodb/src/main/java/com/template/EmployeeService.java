package com.template;

import java.util.Date;

public interface EmployeeService {

	long getMaxEmpId();

	long updateEmployee(String empNo, String fullName, Date hireDate);

}
