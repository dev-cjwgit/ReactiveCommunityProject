package com.cjw.reactivecommunityproject.common.spring.pagination.cursor.model.request;

public record PaginationCursorRequestVO(
        // 반드시 PaginationCursorService 에서 가져온 값만을 사용해야 한다.
        Object lastPk
        , int pageSize
) {
}
