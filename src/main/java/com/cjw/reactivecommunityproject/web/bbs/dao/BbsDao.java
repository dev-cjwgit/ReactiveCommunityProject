package com.cjw.reactivecommunityproject.web.bbs.dao;

import com.cjw.reactivecommunityproject.web.bbs.model.entity.BbsInsertEntity;

public interface BbsDao {

    Boolean isExistPath(String path);

    Boolean isExistName(String name);

    void insertTransactional(BbsInsertEntity bbsInsertEntity);
}
