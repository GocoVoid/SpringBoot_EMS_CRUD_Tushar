package com.tushar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tushar.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
	
	//	Read Operations
	List<EmployeeEntity> findByEmpId(Integer empId);
	
	List<EmployeeEntity> findByEmpName(String empName);
	
	List<EmployeeEntity> findByEmpCity(String empCity);
	
	List<EmployeeEntity> findByEmpCityAndEmpName(String empCity, String empName);
	
	//	Delete Operation
	void deleteByEmpId(Integer empId);

}
