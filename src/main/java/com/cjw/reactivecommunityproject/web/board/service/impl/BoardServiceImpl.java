package com.cjw.reactivecommunityproject.web.board.service.impl;

import com.cjw.reactivecommunityproject.common.spring.component.RcUserComponent;
import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.request.PaginationOffsetRequestVO;
import com.cjw.reactivecommunityproject.web.board.dao.BoardDao;
import com.cjw.reactivecommunityproject.web.board.model.entity.BoardListEntity;
import com.cjw.reactivecommunityproject.web.board.model.request.BoardCreateVO;
import com.cjw.reactivecommunityproject.web.board.model.request.BoardListVO;
import com.cjw.reactivecommunityproject.web.board.model.request.BoardModifyVO;
import com.cjw.reactivecommunityproject.web.board.model.request.BoardRecommendVO;
import com.cjw.reactivecommunityproject.web.board.service.BoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardDao boardDao;

    private final RcUserComponent rcUserComponent;


    @Override
    public RestResponseVO<Void> create(String bbs, BoardCreateVO boardCreateVO) {
        return null;
    }

    @Override
    public RestResponseVO<List<BoardListEntity>> list(BoardListVO boardListVO, PaginationOffsetRequestVO paginationOffsetRequestVO) {
        return null;
    }

    @Override
    public RestResponseVO<BoardListEntity> detail(String bbs, long uid) {
        return null;
    }

    @Override
    public RestResponseVO<Void> remove(String bbs, long boardUid) {
        return null;
    }

    @Override
    public RestResponseVO<Void> perfectRemove(String bbs, long boardUid) {
        return null;
    }

    @Override
    public RestResponseVO<Void> modify(String bbs, BoardModifyVO boardModifyVO) {
        return null;
    }

    @Override
    public RestResponseVO<Void> recommend(String bbs, BoardRecommendVO boardRecommendVO) {
        return null;
    }
}
