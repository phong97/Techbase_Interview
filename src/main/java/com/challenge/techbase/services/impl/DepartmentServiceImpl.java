package com.challenge.techbase.services.impl;

import com.challenge.techbase.mappers.DepartmentRepository;
import com.challenge.techbase.models.entity.Department;
import com.challenge.techbase.models.entity.User;
import com.challenge.techbase.services.DepartmentService;
import com.challenge.techbase.util.Enum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepo;

    @Override
    public Department save(Department department) {
        return this.departmentRepo.saveAndFlush(department);
    }

    @Override
    public void delete(Department department) {
        department.setStatus(Enum.Status.DELETED);

        this.departmentRepo.saveAndFlush(department);
    }

    @Override
    public List<Department> findAll() {
        return this.departmentRepo.findAll();
    }

    @Override
    public Optional<Department> findById(Integer id) {
        return this.departmentRepo.findById(id);
    }

    @Override
    public Department assignManager(Department department, User user) {
        department.setUser(user);

        return this.departmentRepo.saveAndFlush(department);
    }
}
