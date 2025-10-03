package com.cjw.reactivecommunityproject.web.board.post.service.impl;

import com.cjw.reactivecommunityproject.common.spring.component.RcUserComponent;
import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.request.PaginationOffsetRequestVO;
import com.cjw.reactivecommunityproject.web.board.post.dao.BoardPostDao;
import com.cjw.reactivecommunityproject.web.board.post.model.entity.BoardPostListEntity;
import com.cjw.reactivecommunityproject.web.board.post.model.request.BoardPostCreateVO;
import com.cjw.reactivecommunityproject.web.board.post.model.request.BoardPostListVO;
import com.cjw.reactivecommunityproject.web.board.post.model.request.BoardPostModifyVO;
import com.cjw.reactivecommunityproject.web.board.post.model.request.BoardPostRecommendVO;
import com.cjw.reactivecommunityproject.web.board.post.model.request.BoardPostReplyVO;
import com.cjw.reactivecommunityproject.web.board.post.service.BoardPostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class BoardPostServiceImpl implements BoardPostService {
    private final BoardPostDao boardPostDao;

    private final RcUserComponent rcUserComponent;


    @Override
    public RestResponseVO<Void> create(String bbs, BoardPostCreateVO boardPostCreateVO) {
        return null;
    }

    @Override
    public RestResponseVO<Void> reply(String bbs, BoardPostReplyVO boardPostReplyVO) {
        return null;
    }

    @Override
    public RestResponseVO<List<BoardPostListEntity>> list(BoardPostListVO boardPostListVO, PaginationOffsetRequestVO paginationOffsetRequestVO) {
        return null;
    }

    @Override
    public RestResponseVO<BoardPostListEntity> detail(String bbs, long uid) {
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
    public RestResponseVO<Void> modify(String bbs, BoardPostModifyVO boardPostModifyVO) {
        return null;
    }

    @Override
    public RestResponseVO<Void> recommend(String bbs, BoardPostRecommendVO boardPostRecommendVO) {
        return null;
    }

}
