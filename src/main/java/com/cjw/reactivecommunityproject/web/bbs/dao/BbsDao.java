package com.cjw.reactivecommunityproject.web.bbs.dao;

import com.cjw.reactivecommunityproject.web.bbs.model.entity.BbsInsertEntity;
import com.cjw.reactivecommunityproject.web.bbs.model.entity.BbsListEntity;
import com.cjw.reactivecommunityproject.web.bbs.model.entity.BbsModifyEntity;
import java.util.List;

public interface BbsDao {
    Boolean isExistUid(Long uid);

    Boolean isExistPath(String path);

    Boolean isExistName(String name);

    Boolean isExistPath(String path, Long uid);

    Boolean isExistName(String name, Long uid);

    void insertTransactional(BbsInsertEntity bbsInsertEntity);

    void updateTransactional(BbsModifyEntity bbsModifyEntity);

    void deleteTransactional(Long uid);

    List<BbsListEntity> selectListAll();
}
