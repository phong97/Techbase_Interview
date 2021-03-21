package com.challenge.techbase.models.dto.res;

import com.challenge.techbase.models.dto.UserDetailDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FindAllUserResponse {
    private Long total;
    private List<UserDetailDto> users = new ArrayList<>();

    public FindAllUserResponse(Long total, List<UserDetailDto> users) {
        this.total = total;
        this.users = users;
    }
}
