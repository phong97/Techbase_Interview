package com.challenge.techbase.models.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AddMemberRequest {
    @NotNull
    private Integer teamId;
    @NotNull
    private Integer userId;
}
