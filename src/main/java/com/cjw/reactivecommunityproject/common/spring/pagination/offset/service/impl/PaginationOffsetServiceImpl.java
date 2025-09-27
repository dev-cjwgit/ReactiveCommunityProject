package com.cjw.reactivecommunityproject.common.spring.pagination.offset.service.impl;

import com.cjw.reactivecommunityproject.common.spring.pagination.offset.exception.PaginationOffsetErrorMessage;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.exception.PaginationOffsetException;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.entity.PaginationOffsetEntity;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.request.PaginationOffsetRequestVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.request.PaginationOffsetVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.service.PaginationOffsetService;
import com.cjw.reactivecommunityproject.common.spring.pagination.utils.PaginationUtils;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaginationOffsetServiceImpl implements PaginationOffsetService {

    @Override
    public PaginationOffsetVO createPagination(Object searchCondition, PaginationOffsetRequestVO pagination) {
        if (pagination == null || pagination.pageNumber() == null || pagination.pageSize() == null) {
            throw new PaginationOffsetException(PaginationOffsetErrorMessage.NOT_NULL_PAGINATION);
        }

        var searchMap = PaginationUtils.convertFieldsToMap(searchCondition);
        var paginationMap = PaginationUtils.convertFieldsToMap(new PaginationOffsetEntity(pagination));

        var resultMap = new HashMap<String, Object>();

        resultMap.putAll(searchMap);
        resultMap.putAll(paginationMap);

        return PaginationOffsetVO.builder()
                .pagination(resultMap)
                .build();
    }
}
