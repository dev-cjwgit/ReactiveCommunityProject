package com.cjw.reactivecommunityproject.web.bbs.mapper;

import com.cjw.reactivecommunityproject.web.bbs.model.entity.BbsInsertEntity;
import com.cjw.reactivecommunityproject.web.bbs.model.entity.BbsListEntity;
import com.cjw.reactivecommunityproject.web.bbs.model.entity.BbsModifyEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BbsMapper {
    Boolean isExistPath(@Param("path") String path, @Param("uid") Long uid);

    Boolean isExistName(@Param("name") String name, @Param("uid") Long uid);

    int insert(BbsInsertEntity bbsInsertEntity);

    int update(BbsModifyEntity bbsModifyEntity);

    Boolean isExistUid(Long uid);

    int delete(Long uid);

    List<BbsListEntity> selectListAll();
}
