package com.cjw.reactivecommunityproject.common.spring.pagination.model.request;

import lombok.Builder;

@Builder
public record PaginationRequestVO(
        Integer pageNumber,
        Integer pageSize
) {

}
