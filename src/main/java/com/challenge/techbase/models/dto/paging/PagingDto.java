package com.challenge.techbase.models.dto.paging;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PagingDto<T> {
    private Long total;
    private List<T> data = new ArrayList<>();

    public PagingDto(Long total, List<T> data) {
        this.total = total;
        this.data = data;
    }
}
