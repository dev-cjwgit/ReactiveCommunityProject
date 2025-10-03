package com.cjw.reactivecommunityproject.web.board.post.service;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.request.PaginationOffsetRequestVO;
import com.cjw.reactivecommunityproject.web.board.post.model.entity.BoardPostListEntity;
import com.cjw.reactivecommunityproject.web.board.post.model.request.BoardPostCreateVO;
import com.cjw.reactivecommunityproject.web.board.post.model.request.BoardPostListVO;
import com.cjw.reactivecommunityproject.web.board.post.model.request.BoardPostModifyVO;
import com.cjw.reactivecommunityproject.web.board.post.model.request.BoardPostRecommendVO;
import com.cjw.reactivecommunityproject.web.board.post.model.request.BoardPostReplyVO;
import java.util.List;

public interface BoardPostService {
    RestResponseVO<Void> create(String bbs, BoardPostCreateVO boardPostCreateVO);

    RestResponseVO<Void> reply(String bbs, BoardPostReplyVO boardPostReplyVO);

    RestResponseVO<List<BoardPostListEntity>> list(BoardPostListVO boardPostListVO, PaginationOffsetRequestVO paginationOffsetRequestVO);

    RestResponseVO<BoardPostListEntity> detail(String bbs, long uid);

    RestResponseVO<Void> remove(String bbs, long boardUid);

    RestResponseVO<Void> perfectRemove(String bbs, long boardUid);

    RestResponseVO<Void> modify(String bbs, BoardPostModifyVO boardPostModifyVO);

    RestResponseVO<Void> recommend(String bbs, BoardPostRecommendVO boardPostRecommendVO);

}
