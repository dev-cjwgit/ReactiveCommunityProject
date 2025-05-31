package com.cjw.reactivecommunityproject.web.bbs.dao;

import com.cjw.reactivecommunityproject.web.bbs.model.entity.BbsInsertEntity;
import com.cjw.reactivecommunityproject.web.bbs.model.entity.BbsModifyEntity;

public interface BbsDao {

    Boolean isExistPath(String path);

    Boolean isExistName(String name);

    Boolean isExistPath(String path, Long uid);

    Boolean isExistName(String name, Long uid);

    void insertTransactional(BbsInsertEntity bbsInsertEntity);

    void updateTransactional(BbsModifyEntity bbsModifyEntity);
}
