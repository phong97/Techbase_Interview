package com.challenge.techbase.models.dto.req;

import com.challenge.techbase.models.entity.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CreateTeamRequest {
    @NotBlank(message = "Team name is required.")
    private String name;

    public Team toModel() {
        Team team = new Team();
        team.setName(this.name);
        return team;
    }
}

