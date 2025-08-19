package com.cjw.reactivecommunityproject.common.spring.pagination.offset.service.impl;

import com.cjw.reactivecommunityproject.common.spring.pagination.offset.exception.PaginationErrorMessage;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.exception.PaginationException;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.entity.PaginationEntityVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.service.PaginationService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaginationServiceImpl implements PaginationService {
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
    public PaginationVO createPagination(Object searchCondition, PaginationRequestVO pagination) {
        if (pagination == null || pagination.pageNumber() == null || pagination.pageSize() == null) {
            throw new PaginationException(PaginationErrorMessage.NOT_NULL_PAGINATION);
        }

        var searchMap = this.convertFieldsToMap(searchCondition);
        var paginationMap = this.convertFieldsToMap(new PaginationEntityVO(pagination));

        var resultMap = new HashMap<String, Object>();

        resultMap.putAll(searchMap);
        resultMap.putAll(paginationMap);

        return PaginationVO.builder()
                .pagination(resultMap)
                .build();
    }
}
