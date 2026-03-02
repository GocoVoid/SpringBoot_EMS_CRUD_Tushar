package com.tushar.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmployeeModel {
	
	private Integer empId;
	
	@JsonProperty("emp_name")
	private String empName;
	
	@JsonProperty("emp_city")
	private String empCity;

}
