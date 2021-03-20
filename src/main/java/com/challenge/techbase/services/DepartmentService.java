package com.challenge.techbase.services;

import com.challenge.techbase.models.entity.Department;
import com.challenge.techbase.models.entity.User;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    Department save(Department department);

    void delete(Department department);

    List<Department> findAll();

    Optional<Department> findById(Integer id);

    Department assignManager(Department department, User user);
}
