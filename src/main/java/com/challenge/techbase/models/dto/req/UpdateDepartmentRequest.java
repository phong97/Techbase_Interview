package com.challenge.techbase.models.dto.req;

import com.challenge.techbase.models.entity.Department;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UpdateDepartmentRequest {
    @NotNull
    @NotBlank
    private Integer id;
    @NotBlank(message = "Department name is required.")
    private String name;

    public Department toModel(Department department) {
        department.setName(this.name);
        return department;
    }
}
