package com.challenge.techbase.models.dto;

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
}
