package com.cjw.reactivecommunityproject.web.board.bbs.mapper;

import com.cjw.reactivecommunityproject.web.board.bbs.model.entity.BoardBbsInsertEntity;
import com.cjw.reactivecommunityproject.web.board.bbs.model.entity.BoardBbsListEntity;
import com.cjw.reactivecommunityproject.web.board.bbs.model.entity.BoardBbsModifyEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardBbsMapper {
    Boolean isExistPath(@Param("path") String path, @Param("uid") Long uid);

    Boolean isExistName(@Param("name") String name, @Param("uid") Long uid);

    int insert(BoardBbsInsertEntity boardBbsInsertEntity);

    int update(BoardBbsModifyEntity boardBbsModifyEntity);

    Boolean isExistUid(Long uid);

    int delete(Long uid);

    List<BoardBbsListEntity> selectListAll();
}
