package com.cjw.reactivecommunityproject.common.spring.pagination.offset.service.impl;

import com.cjw.reactivecommunityproject.common.spring.pagination.offset.exception.PaginationOffsetErrorMessage;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.exception.PaginationOffsetException;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.entity.PaginationOffsetEntity;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.request.PaginationOffsetVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.request.PaginationOffsetRequestVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.service.PaginationOffsetService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaginationOffsetServiceImpl implements PaginationOffsetService {
    private Map<String, Object> convertFieldsToMap(Object object) {
        Map<String, Object> result = new HashMap<>();
        if (object == null) {
            return result;
        }
        var clazz = object.getClass();
        var fields = clazz.getDeclaredFields();
        Arrays.stream(fields).forEach(o -> {
            try {
                o.setAccessible(true);
                result.put(o.getName(), o.get(object));
            } catch (IllegalAccessException e) {
                log.error("PaginationServiceImpl.convertFieldsToMap: {}", e.getMessage());
            }
        });
        return result;
    }

    @Override
    public PaginationOffsetVO createPagination(Object searchCondition, PaginationOffsetRequestVO pagination) {
        if (pagination == null || pagination.pageNumber() == null || pagination.pageSize() == null) {
            throw new PaginationOffsetException(PaginationOffsetErrorMessage.NOT_NULL_PAGINATION);
        }

        var searchMap = this.convertFieldsToMap(searchCondition);
        var paginationMap = this.convertFieldsToMap(new PaginationOffsetEntity(pagination));

        var resultMap = new HashMap<String, Object>();

        resultMap.putAll(searchMap);
        resultMap.putAll(paginationMap);

        return PaginationOffsetVO.builder()
                .pagination(resultMap)
                .build();
    }
}
