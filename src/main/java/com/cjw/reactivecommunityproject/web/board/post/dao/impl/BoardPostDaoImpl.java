package com.cjw.reactivecommunityproject.web.board.post.dao.impl;

import com.cjw.reactivecommunityproject.web.board.post.dao.BoardPostDao;
import com.cjw.reactivecommunityproject.web.board.post.mapper.BoardPostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BoardPostDaoImpl implements BoardPostDao {
    private final BoardPostMapper boardPostMapper;
}
