package com.example.simplewebapp.controller;

import com.example.simplewebapp.dto.EmployeeDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class EmployeeController {
    List<EmployeeDto> employeeDtoList = new ArrayList<>();
    Integer lastInsertID = 0;

    @GetMapping(path = "/{id}")
    public EmployeeDto getByID(@PathVariable String id) {
        if (id == null || id.trim().equalsIgnoreCase("")) {
            return new EmployeeDto();
        }
        for (EmployeeDto employeeDto : employeeDtoList) {
            if (employeeDto.getId().equals(Integer.valueOf(id))) {
                return employeeDto;
            }
        }
        return new EmployeeDto();
    }

    @GetMapping(path = "/getAll")
    public List<EmployeeDto> getAll() {
        return employeeDtoList;
    }

    @PostMapping(path = "/add")
    public EmployeeDto save(@RequestBody EmployeeDto employeeDto) {
        if (employeeDto == null || employeeDto.getName().trim().equalsIgnoreCase("")) {
            return new EmployeeDto();
        }
        lastInsertID = lastInsertID+1;
        employeeDto.setId(lastInsertID);
        employeeDtoList.add(employeeDto);
        return employeeDto;
    }

    @PostMapping(path = "/edit/{id}")
    public EmployeeDto edit(@RequestBody EmployeeDto employeeDto, @PathVariable String id) {
        if (id == null || id.trim().equalsIgnoreCase("")) {
            return new EmployeeDto();
        }
        if (employeeDto == null || employeeDto.getName().trim().equalsIgnoreCase("")) {
            return new EmployeeDto();
        }
        for (EmployeeDto dto : employeeDtoList) {
            if (dto.getId().equals(Integer.valueOf(id))) {
                dto.setName(employeeDto.getName());
                if (employeeDto.getAge() != null && !employeeDto.getAge().equals(0)) {
                    dto.setAge(employeeDto.getAge());
                }
                return dto;
            }
        }
        return new EmployeeDto();
    }

    @PostMapping(path = "/delete/{id}")
    public EmployeeDto edit(@PathVariable String id) {
        if (id == null || id.trim().equalsIgnoreCase("")) {
            return new EmployeeDto();
        }
        for (EmployeeDto dto : employeeDtoList) {
            if (dto.getId().equals(Integer.valueOf(id))) {
                EmployeeDto employee = dto;
                employeeDtoList.remove(dto);
                return employee;
            }
        }
        return new EmployeeDto();
    }
}
