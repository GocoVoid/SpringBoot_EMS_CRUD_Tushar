package com.tushar.rest.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tushar.entity.EmployeeEntity;
import com.tushar.model.EmployeeModel;
import com.tushar.service.EmployeeService;

@RestController
@RequestMapping("/emp")
public class EmployeeAPI {
	
	@Autowired
	private EmployeeService employeeService;
	
	//	CREATE OPERATION
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addEmployee(@RequestBody EmployeeModel... employeeModels) {		
		return employeeService.addEmployee(employeeModels);
	}
	
	//	READ OPERATIONS

	@GetMapping
	public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/getEmployee/id/{empId}")
	public ResponseEntity<List<EmployeeEntity>> getEmployeesByEmpId(@PathVariable Integer empId) {
		return employeeService.getEmployeesByEmpId(empId);
	}

	@GetMapping("/getEmployee/name/{empName}")
	public ResponseEntity<List<EmployeeEntity>> getEmployeesByEmpName(@PathVariable String empName) {
		return employeeService.getEmployeesByEmpName(empName);
	}

	@GetMapping("/getEmployee/city/{empCity}")
	public ResponseEntity<List<EmployeeEntity>> getEmployeesByEmpCity(@PathVariable String empCity) {
		return employeeService.getEmployeesByEmpCity(empCity);
	}
	
	//	UPDATE OPERATIONS

	@PatchMapping("/updateEmployee/name/{empId}/{newEmpName}")
	public ResponseEntity<EmployeeEntity> updateEmployeeName(@PathVariable Integer empId, @PathVariable String newEmpName) {
		ResponseEntity<EmployeeEntity> updatedEmployee = employeeService.updateEmployeeName(empId, newEmpName);
		return updatedEmployee;
	}

	@PatchMapping("/updateEmployee/city/{empId}/{newEmpCity}")
	public ResponseEntity<EmployeeEntity> updateEmployeeCity(@PathVariable Integer empId, @PathVariable String newEmpCity) {
		ResponseEntity<EmployeeEntity> updatedEmployee = employeeService.updateEmployeeCity(empId, newEmpCity);
		return updatedEmployee;
	}
	
	//	DELETE OPERATION

	@DeleteMapping("/deleteEmployee/{empId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer empId) {
		employeeService.deleteEmployee(empId);
		return ResponseEntity.ok("Employee deleted successfully");
	}

}