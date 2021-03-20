package com.challenge.techbase.controllers;

import com.challenge.techbase.exceptions.RestException;
import com.challenge.techbase.models.dto.req.AddTeamRequest;
import com.challenge.techbase.models.dto.req.AssignDepartmentRequest;
import com.challenge.techbase.models.dto.req.CreateDepartmentRequest;
import com.challenge.techbase.models.dto.DepartmentDto;
import com.challenge.techbase.models.dto.req.UpdateDepartmentRequest;
import com.challenge.techbase.models.entity.Department;
import com.challenge.techbase.models.entity.Team;
import com.challenge.techbase.models.entity.User;
import com.challenge.techbase.services.DepartmentService;
import com.challenge.techbase.services.TeamService;
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

    @Autowired
    private TeamService teamService;

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
        Department department = req.toModel();
        Department newDepartment = this.departmentService.save(department);
        DepartmentDto departmentDto = new DepartmentDto(department);
        return ResponseEntity.ok(departmentDto);
    }

    @PutMapping
    public ResponseEntity<DepartmentDto> updateDepartment(@Valid @RequestBody UpdateDepartmentRequest req) {
        Optional<Department> departmentOptional = this.departmentService.findById(req.getId());
        if (!departmentOptional.isPresent()) {
            logger.error("Department not found by id");
            throw new RestException("Department not found by id");
        }
        Department department = departmentOptional.get();
        Department newDepartment = req.toModel(department);
        newDepartment = this.departmentService.save(newDepartment);
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
        Department newDepartment = this.departmentService.assignManager(department, user);
        DepartmentDto departmentDto = new DepartmentDto(newDepartment);
        return ResponseEntity.ok(departmentDto);
    }

//    @PostMapping("/add_team")
//    public ResponseEntity<Void> addTeam(@Valid @RequestBody AddTeamRequest req) {
//        Integer departmentId = req.getDepartmentId();
//        Integer teamId = req.getTeamId();
//
//        Optional<Department> departmentOptional = this.departmentService.findById(departmentId);
//        if (!departmentOptional.isPresent()){
//            logger.info("Department not found by id");
//            throw new RestException("Department not found by id");
//        }
//
//        Optional<Team> teamOptional = this.teamService.findById(teamId);
//        if (!teamOptional.isPresent()){
//            logger.error("Team not found by id");
//            throw new RestException("Team not found by id");
//        }
//
//        Department department = departmentOptional.get();
//        Team team = teamOptional.get();
//        department.getTeams().add(team);
//        this.departmentService.save(department);
//
//        return ResponseEntity.ok();
//    }
}
