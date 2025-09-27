package com.cjw.reactivecommunityproject.common.spring.pagination.cursor.service;

import com.cjw.reactivecommunityproject.common.spring.pagination.cursor.model.entity.PaginationCursorVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.cursor.model.request.PaginationCursorRequestVO;

public interface PaginationCursorService {
    PaginationCursorVO createPaginationCursor(Object searchCondition, PaginationCursorRequestVO paginationCursorRequestVO);
}
