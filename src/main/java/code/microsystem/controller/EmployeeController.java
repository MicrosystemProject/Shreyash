package code.microsystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import code.microsystem.enity.Employee;
import code.microsystem.exception.ResourceNotFoundException;
import code.microsystem.service.EmployeeService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	//add new employee details
	@PostMapping("/addEmployee")
	public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee employee) {
	
		Employee employee1=employeeService.addNewEMp(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(employee);
	}
	@GetMapping("/getAllEmployee")
	public List<Employee> getAllEmployee(){
		List<Employee> emplist=employeeService.getAllEmployee();
		return emplist;		
	}
	 @GetMapping("/getEmployeeById/{id}")
	 public Employee getById(@PathVariable int id) {
		 
		 Optional<Employee> employee= employeeService.findById(id);
		 if(employee.isPresent()) {
			 System.out.println(employee.get());
		 } else {
			   System.out.printf("No employee found with id"+ id);
	        }
		return employee.get();	 
	 }
	 @GetMapping("/getEmployeeByName")
	 public Employee getEmpByfname(@RequestParam("fname")String name) {
		 
		 Optional <Employee> employee = employeeService.findByName(name);
		 if (employee.isPresent()) {
			 System.out.println(employee.get());
		 }else {
			 System.out.printf("no employee found with Name"+ name);
		 }
			 return employee.get();
	}
	
	 
	//Update Employee Details
		@PutMapping("/editEmployee/{id}")
		public ResponseEntity<Employee> editEmployee(@PathVariable int id,@RequestBody Employee employee) {
			Employee employee1=employeeService.editEmp(id,employee);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(employee);
		}
	
		@DeleteMapping("/deleteById/{id}")
		 public ResponseEntity< Map<String,Boolean>> deleteEmployee(@PathVariable int id){
			 Map<String,Boolean> response=new HashMap<>();
				
			 Employee emp=employeeService.findById(id)
						.orElseThrow(()->new ResourceNotFoundException("Employee doenot exits"+id));
			
					employeeService.deleteEmp(emp);
					response.put("Recored is Deleted", true);
				
				
				return ResponseEntity.ok(response);
		 }
		
		
} 
	
	

















