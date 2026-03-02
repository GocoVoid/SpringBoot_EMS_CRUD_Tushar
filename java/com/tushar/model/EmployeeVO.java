package com.tushar.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Employee")
public record EmployeeVO(Integer emp_id, String emp_name, String emp_city) {}
