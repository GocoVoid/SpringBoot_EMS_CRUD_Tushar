package com.tushar.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tushar.entity.EmployeeEntity;
import com.tushar.model.EmployeeModel;

@Service
public interface EmployeeService {

	ResponseEntity<String> addEmployee(EmployeeModel... employeeModels);

	//Read Operations
	ResponseEntity<List<EmployeeEntity>> getAllEmployees();
	ResponseEntity<List<EmployeeEntity>> getEmployeesByEmpId(Integer empId);
	ResponseEntity<List<EmployeeEntity>> getEmployeesByEmpName(String empName);
	ResponseEntity<List<EmployeeEntity>> getEmployeesByEmpCity(String empCity);
		
	//Update Operations
	ResponseEntity<EmployeeEntity> updateEmployeeName(Integer empId, String new_empName);
	ResponseEntity<EmployeeEntity> updateEmployeeCity(Integer empId, String new_empCity);
		
	//Delete Operations
	ResponseEntity<String> deleteEmployee(Integer empId);

}