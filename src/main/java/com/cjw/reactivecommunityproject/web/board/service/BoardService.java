package com.cjw.reactivecommunityproject.web.board.service;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.request.PaginationOffsetRequestVO;
import com.cjw.reactivecommunityproject.web.board.model.entity.BoardListEntity;
import com.cjw.reactivecommunityproject.web.board.model.request.BoardCreateVO;
import com.cjw.reactivecommunityproject.web.board.model.request.BoardListVO;
import com.cjw.reactivecommunityproject.web.board.model.request.BoardModifyVO;
import com.cjw.reactivecommunityproject.web.board.model.request.BoardRecommendVO;
import java.util.List;

public interface BoardService {
    RestResponseVO<Void> create(String bbs, BoardCreateVO boardCreateVO);

    RestResponseVO<List<BoardListEntity>> list(BoardListVO boardListVO, PaginationOffsetRequestVO paginationOffsetRequestVO);

    RestResponseVO<BoardListEntity> detail(String bbs, long uid);

    RestResponseVO<Void> remove(String bbs, long boardUid);

    RestResponseVO<Void> perfectRemove(String bbs, long boardUid);

    RestResponseVO<Void> modify(String bbs, BoardModifyVO boardModifyVO);

    RestResponseVO<Void> recommend(String bbs, BoardRecommendVO boardRecommendVO);

}
