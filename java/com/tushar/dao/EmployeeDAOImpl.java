package com.tushar.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tushar.entity.EmployeeEntity;
import com.tushar.repository.EmployeeRepository;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	
	@Autowired
	private EmployeeRepository repository;
	
	@Override
	public String addEmployees(List<EmployeeEntity> employeeEntities) {
		List<EmployeeEntity> entities = employeeEntities;
		repository.saveAll(entities);
		return "";
	}

	@Override
	public List<EmployeeEntity> findByEmpNameAndEmpCity(String emp_name, String emp_city) {
		List<EmployeeEntity> employee = repository.findByEmpCityAndEmpName(emp_city, emp_name);
		return employee;
	}

	@Override
	public List<EmployeeEntity> getAllEmployees() {
		return repository.findAll();
	}

	@Override
	public List<EmployeeEntity> getEmployeesByEmpId(Integer empId) {
		return repository.findByEmpId(empId);
	}

	@Override
	public List<EmployeeEntity> getEmployeesByEmpName(String empName) {
		return repository.findByEmpName(empName);
	}

	@Override
	public List<EmployeeEntity> getEmployeesByEmpCity(String empCity) {
		return repository.findByEmpCity(empCity);
	}

	@Override
	public EmployeeEntity updateEmployeeName(Integer empId, String new_empName) {
		Optional<EmployeeEntity> optionalEmp = repository.findById(empId);
        
        if (optionalEmp.isPresent()) {
            EmployeeEntity employee = optionalEmp.get();
            employee.setEmpName(new_empName);
            return repository.save(employee);
        }
        return null;		
	}

	@Override
	public EmployeeEntity updateEmployeeCity(Integer empId, String new_empCity) {
		Optional<EmployeeEntity> optionalEmp = repository.findById(empId);
        
        if (optionalEmp.isPresent()) {
            EmployeeEntity employee = optionalEmp.get();
            employee.setEmpCity(new_empCity);
            return repository.save(employee);
        }
        return null;	
	}

	@Override
	public void deleteEmployee(Integer empId) {
		try {
			repository.deleteByEmpId(empId);
		} catch(Exception e) {
			System.out.println(e);
		}
	}

}
