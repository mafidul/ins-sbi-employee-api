package com.tcs.ins.emp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.tcs.ins.emp.service.model.EmployeeModel;

public interface EmployeeService {

	EmployeeModel getEmployee(Long emiId);

	EmployeeModel createEmployee(EmployeeModel employeeModel);

	EmployeeModel updateEmployee(EmployeeModel employeeModel);

	Page<EmployeeModel> search(PageRequest pagerequest);

	void createEmployee(Long emiId);
}