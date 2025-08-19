package com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.entity;

import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.request.PaginationRequestVO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PaginationOffsetEntity {
    private final Integer offset;
    private final Integer limit;

    public PaginationOffsetEntity(Integer pageNumber, Integer pageSize) {
        this.offset = (pageNumber - 1) * pageSize;
        this.limit = pageSize;
    }

    public PaginationOffsetEntity(PaginationRequestVO paginationRequestVO) {
        this(paginationRequestVO.pageNumber(), paginationRequestVO.pageSize());
    }
}
