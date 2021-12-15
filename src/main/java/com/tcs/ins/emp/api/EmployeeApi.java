package com.tcs.ins.emp.api;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tcs.ins.emp.service.EmployeeService;
import com.tcs.ins.emp.service.model.EmployeeModel;

import static com.tcs.ins.emp.support.Constant.REQUEST_PARAM_MAPPING;
import static com.tcs.ins.emp.support.Constant.REQUEST_PARAM_PAGE_NUMBER;
import static com.tcs.ins.emp.support.Constant.REQUEST_PARAM_PAGE_SIZE;
import static com.tcs.ins.emp.support.Constant.REQUEST_PARAM_SORT_BY;
import static com.tcs.ins.emp.support.Constant.REQUEST_PARAM_SORT_DIRECTION;

@RestController
@RequestMapping(REQUEST_PARAM_MAPPING)
public class EmployeeApi {
	private static final String SORT_DIRECTION_ASC = "asc";
	private final EmployeeService employeeService;

	public EmployeeApi(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping(path = "/{emiId}")
	ResponseEntity<EmployeeModel> getEmployee(@PathVariable Long emiId) {
		return ResponseEntity.ok(employeeService.getEmployee(emiId));
	}

	@GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Page<EmployeeModel>> searchEmployee(
			@RequestParam(name = REQUEST_PARAM_PAGE_NUMBER, required = true) Integer pageNumber,
			@RequestParam(name = REQUEST_PARAM_PAGE_SIZE, required = true) Integer pageSize,
			@RequestParam(name = REQUEST_PARAM_SORT_BY, required = false) String sortBy,
			@RequestParam(name = REQUEST_PARAM_SORT_DIRECTION, required = false) String sortDirection) {
		PageRequest pagerequest = pageRequest(pageNumber, pageSize, sortBy, sortDirection);
		Page<EmployeeModel> page = employeeService.search(pagerequest);
		return ResponseEntity.ok(page);
	}

	private PageRequest pageRequest(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
		if (StringUtils.hasText(sortBy)) {
			Direction direction = StringUtils.hasText(sortDirection) && SORT_DIRECTION_ASC.equalsIgnoreCase(sortDirection)
					? Direction.ASC
					: Direction.DESC;
			return PageRequest.of(pageNumber, pageSize, Sort.by(new Order(direction, sortBy)));
		} else
			return PageRequest.of(pageNumber, pageSize);
	}

	@PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<EmployeeModel> createEmployee(@RequestBody EmployeeModel employeeModel, UriComponentsBuilder ucb) {
		EmployeeModel create = employeeService.createEmployee(employeeModel);
		return ResponseEntity.created(ucb
									 .path(REQUEST_PARAM_MAPPING + "/{emiId}")
									 .buildAndExpand(create.getEmpId())
									 .toUri())
							  .body(create);
	}

	@PutMapping(path = "/{emiId}", consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<EmployeeModel> updateEmployee(@PathVariable Long emiId, @RequestBody EmployeeModel employeeModel) {
		employeeModel.setEmpId(emiId);
		EmployeeModel update = employeeService.updateEmployee(employeeModel);
		return ResponseEntity.ok(update);
	}

	@DeleteMapping(path = "/{emiId}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> deleteEmployee(@PathVariable Long emiId) {
		employeeService.createEmployee(emiId);
		return ResponseEntity.ok(null);
	}
}