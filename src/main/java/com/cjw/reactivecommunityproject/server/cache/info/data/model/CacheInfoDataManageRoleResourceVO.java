package com.cjw.reactivecommunityproject.server.cache.info.data.model;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CacheInfoDataManageRoleResourceVO {
    private Integer roleUid;
    private Long resourceUid;
    private CommonEnabledEnum enabled;
    private ZonedDateTime updatedUtcAt;
}
