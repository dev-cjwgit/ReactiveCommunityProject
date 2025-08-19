package com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.entity;

import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.request.PaginationRequestVO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PaginationEntityVO {
    private final Integer offset;
    private final Integer limit;

    public PaginationEntityVO(Integer pageNumber, Integer pageSize) {
        this.offset = (pageNumber - 1) * pageSize;
        this.limit = pageSize;
    }

    public PaginationEntityVO(PaginationRequestVO paginationRequestVO) {
        this(paginationRequestVO.pageNumber(), paginationRequestVO.pageSize());
    }
}
