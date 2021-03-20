package com.challenge.techbase.models.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddTeamRequest {
    private Integer departmentId;
    private Integer teamId;
}
