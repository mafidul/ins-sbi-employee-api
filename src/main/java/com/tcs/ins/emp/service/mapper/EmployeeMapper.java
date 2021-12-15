package com.tcs.ins.emp.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.tcs.ins.emp.repository.entity.Employee;
import com.tcs.ins.emp.service.model.EmployeeModel;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	EmployeeModel toModel(Employee source);

	Employee toEntity(EmployeeModel source);

	List<EmployeeModel> toModel(Page<Employee> page);

	List<EmployeeModel> toModel(List<Employee> content);
}
