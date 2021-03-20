package com.challenge.techbase.models.dto;

import com.challenge.techbase.models.entity.Team;
import com.challenge.techbase.util.Enum.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamDto {
    private Integer id;
    private String name;
    private Status status;
    private DepartmentDto department;

    public TeamDto(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.status = team.getStatus();
        this.department = new DepartmentDto(team.getDepartment());
    }

}
