package com.cjw.reactivecommunityproject.web.board.dao.impl;

import com.cjw.reactivecommunityproject.web.board.dao.BoardDao;
import com.cjw.reactivecommunityproject.web.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BoardDaoImpl implements BoardDao {
    private final BoardMapper boardMapper;
}
