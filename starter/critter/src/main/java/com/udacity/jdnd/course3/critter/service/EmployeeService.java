package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.EmployeeSkill;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.*;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        return optionalEmployee.orElseThrow(EmptyStackException::new);
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = getEmployee(employeeId);
        employee.setDaysAvailable(daysAvailable);
        saveEmployee(employee);
    }

    public List<Employee> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        // first we filter the employees with the given available day
        DayOfWeek availableDay = employeeDTO.getDate().getDayOfWeek();
        List<Employee> partialResult = employeeRepository.findAllByDaysAvailableContaining(availableDay);

        // now we filter the partial result to get only employees with the required skills
        Set<EmployeeSkill> requiredSkills = employeeDTO.getSkills();
        List<Employee> finalResult = new ArrayList<>();
        for (Employee employee : partialResult) {
            if (employee.getSkills().containsAll(requiredSkills))
                finalResult.add(employee);
        }
        return finalResult;
    }
}
