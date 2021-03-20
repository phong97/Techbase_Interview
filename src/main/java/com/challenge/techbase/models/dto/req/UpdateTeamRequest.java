package com.challenge.techbase.models.dto.req;

import com.challenge.techbase.models.entity.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTeamRequest {
    @NotNull
    @NotBlank
    private Integer id;
    @NotBlank(message = "Team name is required.")
    private String name;

    public Team toModel(Team team) {
        team.setName(this.name);
        return team;
    }
}

