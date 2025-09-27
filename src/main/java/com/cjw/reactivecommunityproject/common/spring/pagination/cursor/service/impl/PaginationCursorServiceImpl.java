package com.cjw.reactivecommunityproject.common.spring.pagination.cursor.service.impl;

import com.cjw.reactivecommunityproject.common.spring.pagination.cursor.model.entity.PaginationCursorVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.cursor.model.request.PaginationCursorRequestVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.cursor.service.PaginationCursorService;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.exception.PaginationOffsetErrorMessage;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.exception.PaginationOffsetException;
import com.cjw.reactivecommunityproject.common.spring.pagination.utils.PaginationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaginationCursorServiceImpl implements PaginationCursorService {

    @Override
    public PaginationCursorVO createPaginationCursor(Object searchCondition, PaginationCursorRequestVO paginationCursorRequestVO) {
        if (paginationCursorRequestVO == null || paginationCursorRequestVO.lastPk() == null) {
            throw new PaginationOffsetException(PaginationOffsetErrorMessage.NOT_NULL_PAGINATION);
        }

        var searchMap = PaginationUtils.convertFieldsToMap(searchCondition);
        var pkMap = PaginationUtils.convertFieldsToMap(paginationCursorRequestVO.lastPk());


        return PaginationCursorVO.builder()
                .lastPk(pkMap)
                .condition(searchMap)
                .build();
    }
}
