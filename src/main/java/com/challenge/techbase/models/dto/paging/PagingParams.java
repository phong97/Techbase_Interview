package com.challenge.techbase.models.dto.paging;

import com.challenge.techbase.util.Enum.SortDirection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PagingParams {
    private Integer offset;
    private Integer size;
    private String sortColumn;
    private SortDirection direction;

    public PagingParams(Integer offset, Integer size, String sortColumn, SortDirection direction) {
        this.offset = offset;
        this.size = size;
        this.sortColumn = sortColumn;
        this.direction = direction;
    }
}
