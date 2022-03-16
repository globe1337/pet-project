package org.mongo.controller;

import org.mongo.entity.Employee;
import org.mongo.api.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping
    public Employee insert(@RequestBody Employee employee) {
        return employeeRepository.insert(employee);
    }

    @DeleteMapping
    public void delete(@RequestBody Employee employee) {
        employeeRepository.delete(employee);
    }

    @GetMapping
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @PutMapping
    public Employee update(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }
}
