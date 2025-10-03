package com.cjw.reactivecommunityproject.web.board.bbs.dao.impl;

import com.cjw.reactivecommunityproject.web.board.bbs.dao.BoardBbsDao;
import com.cjw.reactivecommunityproject.web.board.bbs.mapper.BoardBbsMapper;
import com.cjw.reactivecommunityproject.web.board.bbs.model.entity.BoardBbsInsertEntity;
import com.cjw.reactivecommunityproject.web.board.bbs.model.entity.BoardBbsListEntity;
import com.cjw.reactivecommunityproject.web.board.bbs.model.entity.BoardBbsModifyEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BoardBbsDaoImpl implements BoardBbsDao {
    private final BoardBbsMapper boardBbsMapper;


    @Override
    public Boolean isExistUid(Long uid) {
        return boardBbsMapper.isExistUid(uid);
    }

    @Override
    public Boolean isExistPath(String path) {
        return boardBbsMapper.isExistPath(path, null);
    }

    @Override
    public Boolean isExistName(String name) {
        return boardBbsMapper.isExistName(name, null);
    }

    @Override
    public Boolean isExistPath(String path, Long uid) {
        return boardBbsMapper.isExistPath(path, uid);
    }

    @Override
    public Boolean isExistName(String name, Long uid) {
        return boardBbsMapper.isExistName(name, uid);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void insertTransactional(BoardBbsInsertEntity boardBbsInsertEntity) {
        int rtn = boardBbsMapper.insert(boardBbsInsertEntity);
        log.info("bbsMapper.insert() : {}", rtn);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void updateTransactional(BoardBbsModifyEntity boardBbsModifyEntity) {
        int rtn = boardBbsMapper.update(boardBbsModifyEntity);
        log.info("bbsMapper.update() : {}", rtn);
    }

    @Override
    public void deleteTransactional(Long uid) {
        int rtn = boardBbsMapper.delete(uid);
        log.info("bbsMapper.delete() : {}", rtn);
    }

    @Override
    public List<BoardBbsListEntity> selectListAll() {
        return boardBbsMapper.selectListAll();
    }
}
