package com.tushar.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tushar.dao.EmployeeDAO;
import com.tushar.entity.EmployeeEntity;
import com.tushar.exception.InvalidEmployeeIdException;
import com.tushar.model.EmployeeModel;
import com.tushar.rest.api.EmployeeAPI;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    private EmployeeDAO employeeDAO;
    
    private static final Logger logger = LoggerFactory.getLogger(EmployeeAPI.class);
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<String> addEmployee(EmployeeModel... employeeModels) {
        logger.info("Received request to add {} employee(s)", employeeModels != null ? employeeModels.length : 0);
        try {
            if (employeeModels == null || employeeModels.length == 0) {
                logger.warn("Add employee request received with empty payload");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input: Employee data is missing");
            }
            
            List<EmployeeEntity> l = Arrays.stream(employeeModels)
                    .map(model -> modelMapper.map(model, EmployeeEntity.class))
                    .toList();
            
            employeeDAO.addEmployees(l);
            logger.info("Successfully added employees");
            return ResponseEntity.status(HttpStatus.CREATED).body("Employees Added Successfully");
            
        } catch (Exception e) {
            logger.error("Error occurred while adding employee(s): {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add employees due to a server error");
        }
    }

    @Override
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
        logger.info("Received request to fetch all employees");
        try {
            List<EmployeeEntity> employees = employeeDAO.getAllEmployees();
            logger.info("Successfully fetched {} employees", employees.size());
            return ResponseEntity.ok(employees);
            
        } catch (Exception e) {
            logger.error("Error occurred while fetching all employees: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<List<EmployeeEntity>> getEmployeesByEmpId(Integer empId) {
        logger.info("Received request to fetch employee(s) with ID: {}", empId);
        try {
            List<EmployeeEntity> employees = employeeDAO.getEmployeesByEmpId(empId);
            
            if (employees == null || employees.isEmpty()) {
                throw new InvalidEmployeeIdException("No employee found with ID: " + empId);
            }
            
            logger.info("Successfully fetched {} employee(s) for ID: {}", employees.size(), empId);
            return ResponseEntity.ok(employees);
            
        } catch (InvalidEmployeeIdException e) {
            logger.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error occurred while fetching employee with ID {}: {}", empId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<List<EmployeeEntity>> getEmployeesByEmpName(String empName) {
        logger.info("Received request to fetch employee(s) with name: {}", empName);
        try {
            List<EmployeeEntity> employees = employeeDAO.getEmployeesByEmpName(empName);
            logger.info("Successfully fetched {} employee(s) for name: {}", employees.size(), empName);
            return ResponseEntity.ok(employees);
            
        } catch (Exception e) {
            logger.error("Error occurred while fetching employee(s) with name {}: {}", empName, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<List<EmployeeEntity>> getEmployeesByEmpCity(String empCity) {
        logger.info("Received request to fetch employee(s) with city: {}", empCity);
        try {
            List<EmployeeEntity> employees = employeeDAO.getEmployeesByEmpCity(empCity);
            logger.info("Successfully fetched {} employee(s) for city: {}", employees.size(), empCity);
            return ResponseEntity.ok(employees);
            
        } catch (Exception e) {
            logger.error("Error occurred while fetching employee(s) with city {}: {}", empCity, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<EmployeeEntity> updateEmployeeName(Integer empId, String new_empName) {
        logger.info("Received request to update name to '{}' for employee ID: {}", new_empName, empId);
        try {
            List<EmployeeEntity> existing = employeeDAO.getEmployeesByEmpId(empId);
            if (existing == null || existing.isEmpty()) {
                throw new InvalidEmployeeIdException("Cannot update name. No employee found with ID: " + empId);
            }

            EmployeeEntity updatedEmployee = employeeDAO.updateEmployeeName(empId, new_empName);
            logger.info("Successfully updated name for employee ID: {}", empId);
            return ResponseEntity.ok(updatedEmployee);
            
        } catch (InvalidEmployeeIdException e) {
            logger.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error occurred while updating name for employee ID {}: {}", empId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<EmployeeEntity> updateEmployeeCity(Integer empId, String new_empCity) {
        logger.info("Received request to update city to '{}' for employee ID: {}", new_empCity, empId);
        try {
            List<EmployeeEntity> existing = employeeDAO.getEmployeesByEmpId(empId);
            if (existing == null || existing.isEmpty()) {
                throw new InvalidEmployeeIdException("Cannot update city. No employee found with ID: " + empId);
            }

            EmployeeEntity updatedEmployee = employeeDAO.updateEmployeeCity(empId, new_empCity);
            logger.info("Successfully updated city for employee ID: {}", empId);
            return ResponseEntity.ok(updatedEmployee);
            
        } catch (InvalidEmployeeIdException e) {
            logger.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error occurred while updating city for employee ID {}: {}", empId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteEmployee(Integer empId) {
        logger.info("Received request to delete employee with ID: {}", empId);
        try {
            List<EmployeeEntity> existing = employeeDAO.getEmployeesByEmpId(empId);
            if (existing == null || existing.isEmpty()) {
                throw new InvalidEmployeeIdException("Cannot delete. No employee found with ID: " + empId);
            }

            employeeDAO.deleteEmployee(empId);
            logger.info("Successfully deleted employee with ID: {}", empId);
            return ResponseEntity.ok("Employee deleted successfully");
            
        } catch (InvalidEmployeeIdException e) {
            logger.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error occurred while deleting employee with ID {}: {}", empId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete employee due to a server error");
        }
    }

}
