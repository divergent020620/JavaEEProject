package com.example.employee.service.impl;

import com.example.employee.model.entity.Employee;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.EmployeeService;
import com.example.employee.utils.DataTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee employee) {
        employee.setCreateAt(DataTimeUtil.getNowTimeString());
        return employeeRepository.save(employee);
    }

    @Override
    public void update(Employee employee) {
        employee.setUpdateAt(DataTimeUtil.getNowTimeString());
        employeeRepository.save(employee);
    }

    @Override
    public void delete(String id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee findById(String id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

}
