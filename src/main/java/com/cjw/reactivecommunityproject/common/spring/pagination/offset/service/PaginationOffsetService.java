package com.cjw.reactivecommunityproject.common.spring.pagination.offset.service;

import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.request.PaginationOffsetVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.request.PaginationRequestVO;

public interface PaginationOffsetService {
    PaginationOffsetVO createPagination(Object object, PaginationRequestVO paginationRequestVO);
}
