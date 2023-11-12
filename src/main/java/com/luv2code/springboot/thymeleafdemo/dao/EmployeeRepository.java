package com.luv2code.springboot.thymeleafdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// that's it ... no need to write any code LOL!
	public List<Employee> findAllByOrderByLastNameAsc();

	@Query(value = "SELECT * FROM employee entity WHERE entity.cust_type = ?1",nativeQuery = true)
	List<Employee> findEmployeeByType(String Type);
}
