package com.cjw.reactivecommunityproject.common.spring.pagination.cursor.model.entity;

import java.util.Map;
import lombok.Builder;

@Builder
public record PaginationCursorVO(
        // 반드시 PaginationCursorService 에서 가져온 값만을 사용해야 한다.
        Map<String, Object> lastPk
        , Map<String, Object> condition
) {
}
