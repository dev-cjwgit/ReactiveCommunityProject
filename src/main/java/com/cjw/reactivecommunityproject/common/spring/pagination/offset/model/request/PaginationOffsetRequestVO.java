package com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.request;

import lombok.Builder;

@Builder
public record PaginationOffsetRequestVO(
        Integer pageNumber
        , Integer pageSize
) {

}
