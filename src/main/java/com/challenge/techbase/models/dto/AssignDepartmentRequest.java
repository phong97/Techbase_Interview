package com.challenge.techbase.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AssignDepartmentRequest {
    @NotNull(message = "User id is required.")
    private Integer userId;
    @NotNull(message = "Department id is required.")
    private Integer departmentId;
}
