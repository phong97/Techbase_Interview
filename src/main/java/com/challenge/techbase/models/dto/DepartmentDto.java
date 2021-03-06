package com.challenge.techbase.models.dto;

import com.challenge.techbase.models.entity.Department;
import com.challenge.techbase.models.entity.User;
import com.challenge.techbase.util.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDto {
    private Integer id;
    private String name;
    private Status status;
    private UserDetailDto manager;

    public DepartmentDto(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.status = department.getStatus();
        this.manager = department.getUser() == null ? null : new UserDetailDto(department.getUser());
    }
}
