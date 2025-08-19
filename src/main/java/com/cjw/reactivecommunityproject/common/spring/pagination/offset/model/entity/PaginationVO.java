package com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.entity;

import java.util.Map;
import lombok.Builder;

@Builder
public record PaginationVO(
        // 반드시 PaginationService 에서 가져온 값만을 사용해야 한다.
        Map<String, Object> pagination
) {
}
