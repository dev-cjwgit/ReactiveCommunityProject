package com.cjw.reactivecommunityproject.web.bbs.dao.impl;

import com.cjw.reactivecommunityproject.web.bbs.dao.BbsDao;
import com.cjw.reactivecommunityproject.web.bbs.mapper.BbsMapper;
import com.cjw.reactivecommunityproject.web.bbs.model.entity.BbsInsertEntity;
import com.cjw.reactivecommunityproject.web.bbs.model.entity.BbsModifyEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BbsDaoImpl implements BbsDao {
    private final BbsMapper bbsMapper;


    @Override
    public Boolean isExistPath(String path) {
        return bbsMapper.isExistPath(path, null);
    }

    @Override
    public Boolean isExistName(String name) {
        return bbsMapper.isExistName(name, null);
    }

    @Override
    public Boolean isExistPath(String path, Long uid) {
        return bbsMapper.isExistPath(path, uid);
    }

    @Override
    public Boolean isExistName(String name, Long uid) {
        return bbsMapper.isExistName(name, uid);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void insertTransactional(BbsInsertEntity bbsInsertEntity) {
        int rtn = bbsMapper.insert(bbsInsertEntity);
        log.info("bbsMapper.insert() : {}", rtn);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void updateTransactional(BbsModifyEntity bbsModifyEntity) {
        int rtn = bbsMapper.update(bbsModifyEntity);
        log.info("bbsMapper.update() : {}", rtn);
    }
}
