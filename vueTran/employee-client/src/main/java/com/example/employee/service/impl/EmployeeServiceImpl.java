package com.example.employee.service.impl;

import com.example.employee.model.entity.Employee;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.EmployeeService;
import com.example.employee.utils.DataTimeUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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

    @CircuitBreaker(name = "employeeService", fallbackMethod = "saveFallback")
    @Override
    public void update(Employee employee) {
        employee.setUpdateAt(DataTimeUtil.getNowTimeString());
        employeeRepository.save(employee);
    }

    public Employee saveFallback(Employee employee, Throwable ex) {
        // 在这里添加断路器打开后的备用逻辑
        // 返回一个默认的Employee对象，或者抛出异常等等
        return null;
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
