package com.tushar.dao;

import java.util.List;

import com.tushar.entity.EmployeeEntity;

public interface EmployeeDAO {

	//Create Operations
	String addEmployees(List<EmployeeEntity> employeeEntities);
	
	//Read Operations
	List<EmployeeEntity> getAllEmployees();
	List<EmployeeEntity> getEmployeesByEmpId(Integer empId);
	List<EmployeeEntity> getEmployeesByEmpName(String empName);
	List<EmployeeEntity> getEmployeesByEmpCity(String empCity);
	List<EmployeeEntity> findByEmpNameAndEmpCity(String emp_city, String emp_name);
	
	//Update Operations
	EmployeeEntity updateEmployeeName(Integer empId, String new_empName);
	EmployeeEntity updateEmployeeCity(Integer empId, String new_empCity);
	
	//Delete Operations
	void deleteEmployee(Integer empId);

}