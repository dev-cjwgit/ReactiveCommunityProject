package com.cjw.reactivecommunityproject.web.bbs.mapper;

import com.cjw.reactivecommunityproject.web.bbs.model.entity.BbsInsertEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BbsMapper {
    Boolean isExistPath(@Param("path") String path);

    Boolean isExistName(@Param("name") String name);

    int insert(BbsInsertEntity bbsInsertEntity);
}
