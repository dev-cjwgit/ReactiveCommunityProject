package com.cjw.reactivecommunityproject.common.spring.pagination.service;

import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;

public interface PaginationService {
    PaginationVO createPagination(Object object, PaginationRequestVO paginationRequestVO);
}
