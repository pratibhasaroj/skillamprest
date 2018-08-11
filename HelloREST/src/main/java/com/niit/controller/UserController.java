package com.niit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.skillmapbackend.model.Employee;
import com.niit.skillmapbackend.service.EmployeeService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/emp")
public class UserController {
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping
	public ResponseEntity<List<Employee>> userList() {
		if (employeeService.getAllEmployees().size() != 0) {
			return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(), HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/{empId}")
	public ResponseEntity<Employee> getEmpById(@PathVariable("empId") int empId) {
		if (employeeService.getAllEmployees().size() != 0) {
			return new ResponseEntity<Employee>(employeeService.getEmployee(empId), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
		}
	}
		
		@PostMapping
		public ResponseEntity<Void> addEmployee(@RequestBody Employee emp) {
			
			if(employeeService.getEmployee(emp.getEmpID())!=null) {
				
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
			else { 
				employeeService.insertEmp(emp);
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			}
		}
		
		@PutMapping
		public ResponseEntity<Void> updateEmployee(@RequestBody Employee emp){
			
			employeeService.updateEmp(emp);
			return new ResponseEntity<Void>(HttpStatus.OK);
				
			}
			
		
		@DeleteMapping
		public ResponseEntity<Void> deleteEmployee(@RequestBody Employee emp){
			
			employeeService.delete(emp);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		@PostMapping("/{login}")
		public ResponseEntity<Employee> loginEmployee(@RequestBody String emailId,String password,String role){
			
			return new ResponseEntity<Employee>(employeeService.loginEmployee(emailId, password, role),HttpStatus.OK);
		}
		
		@PutMapping("/{status}")
		public ResponseEntity<Void> updateStatus(@RequestBody Employee emp){
			
			employeeService.setStatusEmp(emp);
			return new ResponseEntity<Void>(HttpStatus.OK);
				
			}
		
		
	}

