package com.cjw.reactivecommunityproject.common.spring.pagination.offset.service;

import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.request.PaginationRequestVO;

public interface PaginationService {
    PaginationVO createPagination(Object object, PaginationRequestVO paginationRequestVO);
}
