package com.challenge.techbase.controllers;

import com.challenge.techbase.exceptions.RestException;
import com.challenge.techbase.models.dto.AssignDepartmentRequest;
import com.challenge.techbase.models.dto.CreateDepartmentRequest;
import com.challenge.techbase.models.dto.DepartmentDto;
import com.challenge.techbase.models.entity.Department;
import com.challenge.techbase.models.entity.User;
import com.challenge.techbase.services.DepartmentService;
import com.challenge.techbase.services.UserService;
import com.challenge.techbase.util.Enum.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartment() {
        List<Department> departments = departmentService.findAll();
        List<DepartmentDto> departmentDtos = departments
                .stream()
                .map(department -> new DepartmentDto(department))
                .collect(Collectors.toList());

        return ResponseEntity.ok(departmentDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Integer id) {
        Optional<Department> departmentOptional = this.departmentService.findById(id);
        if (!departmentOptional.isPresent()) {
            logger.error("Department not found by id");
            throw new RestException("Department not found by id");
        }

        Department department = departmentOptional.get();
        DepartmentDto departmentDto = new DepartmentDto(department);
        return ResponseEntity.ok(departmentDto);
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@Valid @RequestBody CreateDepartmentRequest req) {
        Department department = new Department();
        department.setName(req.getName());
//        TODO: get id of current user
        department.setCreatedBy(1);
        Department newDepartment = this.departmentService.save(department);
        DepartmentDto departmentDto = new DepartmentDto(department);
        return ResponseEntity.ok(departmentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DepartmentDto> deleteDepartment(@PathVariable("id") Integer id) {
        Optional<Department> departmentOptional = this.departmentService.findById(id);
        if (!departmentOptional.isPresent()) {
            logger.error("Department not found by id");
            throw new RestException("Department not found by id");
        }

        Department department = departmentOptional.get();
        department.setStatus(Status.DELETED);
//        TODO: get id of current user
        department.setModifiedBy(1);
        Department newDepartment = departmentOptional.get();
        DepartmentDto departmentDto = new DepartmentDto(newDepartment);
        return ResponseEntity.ok(departmentDto);
    }

    @PostMapping("/assign_manager")
    public ResponseEntity<DepartmentDto> assignManager(@Valid @RequestBody AssignDepartmentRequest req) {
        int userId = req.getUserId();
        int departmentId = req.getDepartmentId();

        Optional<User> userOptional = this.userService.findById(userId);
        if (!userOptional.isPresent()) {
            logger.info("User not found by id");
            throw new RestException("User not found by id");
        }

        Optional<Department> departmentOptional = this.departmentService.findById(departmentId);
        if (!departmentOptional.isPresent()) {
            logger.info("Department not found by id");
            throw new RestException("Department not found by id");
        }

        User user = userOptional.get();
        Department department = departmentOptional.get();
        //        TODO: get id of current user
        department.setModifiedBy(1);
        Department newDepartment = this.departmentService.assignManager(department, user);
        DepartmentDto departmentDto = new DepartmentDto(newDepartment);
        return ResponseEntity.ok(departmentDto);
    }

}