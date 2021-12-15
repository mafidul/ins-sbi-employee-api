package com.tcs.ins.emp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tcs.ins.emp.repository.EmployeeRepository;
import com.tcs.ins.emp.repository.entity.Employee;
import com.tcs.ins.emp.service.mapper.EmployeeMapper;
import com.tcs.ins.emp.service.model.EmployeeModel;

@Service
public class DefaultEmployeeService implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final EmployeeMapper employeeMapper;

	public DefaultEmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
		this.employeeRepository = employeeRepository;
		this.employeeMapper = employeeMapper;
	}

	@Override
	public EmployeeModel getEmployee(Long emiId) {
		Employee employee = getOrThrow(emiId);
		return employeeMapper.toModel(employee);
	}

	private Employee getOrThrow(Long emiId) {
		Optional<Employee> employeeModel = employeeRepository.findById(emiId);
		if (employeeModel.isEmpty()) {
			throw new IllegalArgumentException("Employee Id not Found");
		}
		return employeeModel.get();
	}

	@Override
	public EmployeeModel createEmployee(EmployeeModel employeeModel) {
		Employee employee = employeeMapper.toEntity(employeeModel);
		Employee saveEmplyee = employeeRepository.save(employee);
		return employeeMapper.toModel(saveEmplyee);
	}

	@Override
	public EmployeeModel updateEmployee(EmployeeModel employeeModel) {
		Employee employee = getOrThrow(employeeModel.getEmpId());
		if (StringUtils.hasText(employeeModel.getEmpName())) {
			employee.setEmpName(employeeModel.getEmpName());
		}
		if (StringUtils.hasText(employeeModel.getAddress())) {
			employee.setAddress(employeeModel.getAddress());
		}
		if (StringUtils.hasText(employeeModel.getEmail())) {
			employee.setEmail(employeeModel.getEmail());
		}
		if (org.springframework.util.StringUtils.hasText(employeeModel.getConfirmEmail())) {
			employee.setConfirmEmail(employeeModel.getConfirmEmail());
		}
		if (org.springframework.util.StringUtils.hasText(employeeModel.getCommunicationType())) {
			employee.setCommunicationType(employeeModel.getCommunicationType());
		}
		Employee saveEmplyee = employeeRepository.save(employee);
		return employeeMapper.toModel(saveEmplyee);
	}

	@Override
	public void createEmployee(Long emiId) {
		employeeRepository.deleteById(emiId);
	}

	@Override
	public Page<EmployeeModel> search(PageRequest pageRequest) {
		Page<Employee> page = employeeRepository.findAll(pageRequest);
		List<EmployeeModel> content = employeeMapper.toModel(page.getContent());
		return new PageImpl<>(content, pageRequest, page.getTotalElements());
	}
}
