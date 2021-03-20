package com.challenge.techbase.models.dto.req;

import com.challenge.techbase.models.entity.Department;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CreateDepartmentRequest {
    @NotBlank(message = "Department name is required.")
    private String name;

    public Department toModel() {
        Department department = new Department();
        department.setName(this.name);
        return department;
    }
}
