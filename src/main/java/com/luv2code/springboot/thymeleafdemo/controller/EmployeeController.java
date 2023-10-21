package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;
	@Autowired
	public  EmployeeController(EmployeeService employeeService){
		this.employeeService = employeeService;
	}

	@GetMapping("/list")
	public String listEmployees(Model model){
		List<Employee> theEmployees = employeeService.findAll();
		model.addAttribute("employees",theEmployees);
		return "employees/list-employees";
	}
	@GetMapping("/showFormToAdd")
	public String addEmployee(Model model){
		Employee theEmployee = new Employee();
		model.addAttribute("Employee",theEmployee);
		return "employees/showFormToAdd";
	}
	@PostMapping("/save")
	public  String saveEmployee(@ModelAttribute("Employee") Employee employee){
		employeeService.save(employee);
		return "redirect:/employees/list";
	}
	@GetMapping("/showFormToUpdate")
	public String showFormToUpdate(@RequestParam("employeeId") int theId, Model model){
		Employee theEmployee = employeeService.findById(theId);
		model.addAttribute("Employee",theEmployee);
		return "employees/showFormToUpdate";
	}
	@PostMapping("/update")
	public String updateEmployee(@ModelAttribute("Employee") Employee employee){
		employeeService.save(employee);
		return "redirect:/employees/list";
	}
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam("employeeId") int theId){
		employeeService.deleteById(theId);
		return "redirect:/employees/list";
	}
}









