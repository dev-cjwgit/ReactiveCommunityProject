package com.cjw.reactivecommunityproject.web.board.bbs.dao;

import com.cjw.reactivecommunityproject.web.board.bbs.model.entity.BoardBbsInsertEntity;
import com.cjw.reactivecommunityproject.web.board.bbs.model.entity.BoardBbsListEntity;
import com.cjw.reactivecommunityproject.web.board.bbs.model.entity.BoardBbsModifyEntity;
import java.util.List;

public interface BoardBbsDao {
    Boolean isExistUid(Long uid);

    Boolean isExistPath(String path);

    Boolean isExistName(String name);

    Boolean isExistPath(String path, Long uid);

    Boolean isExistName(String name, Long uid);

    void insertTransactional(BoardBbsInsertEntity boardBbsInsertEntity);

    void updateTransactional(BoardBbsModifyEntity boardBbsModifyEntity);

    void deleteTransactional(Long uid);

    List<BoardBbsListEntity> selectListAll();
}
