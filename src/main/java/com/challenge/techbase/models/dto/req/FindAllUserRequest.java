package com.challenge.techbase.models.dto.req;

import com.challenge.techbase.util.Enum.SortDirection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class FindAllUserRequest {
    private Integer offset;
    private Integer size;
    private String sortColumn;
    private SortDirection sortDirection;

}
