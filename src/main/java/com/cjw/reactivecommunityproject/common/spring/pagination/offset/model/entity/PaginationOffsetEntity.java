package com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.entity;

import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.request.PaginationOffsetRequestVO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PaginationOffsetEntity {
    private final Integer offset;
    private final Integer limit;

    private PaginationOffsetEntity() {
        this.offset = -1;
        this.limit = -1;
    }

    public PaginationOffsetEntity(Integer pageNumber, Integer pageSize) {
        this.offset = (pageNumber - 1) * pageSize;
        this.limit = pageSize;
    }

    public PaginationOffsetEntity(PaginationOffsetRequestVO paginationOffsetRequestVO) {
        this(paginationOffsetRequestVO.pageNumber(), paginationOffsetRequestVO.pageSize());
    }
}
