package code.microsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import code.microsystem.enity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Query("from Employee e where e.fname=:name")
	Optional<Employee> findByFname(String name);

}
