package com.cjw.reactivecommunityproject.web.board.service.impl;

import com.cjw.reactivecommunityproject.common.spring.component.RcUserComponent;
import com.cjw.reactivecommunityproject.web.board.dao.BoardDao;
import com.cjw.reactivecommunityproject.web.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardDao boardDao;

    private final RcUserComponent rcUserComponent;


}
