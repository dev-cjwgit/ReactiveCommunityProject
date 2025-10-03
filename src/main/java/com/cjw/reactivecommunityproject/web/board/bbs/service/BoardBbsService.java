package com.cjw.reactivecommunityproject.web.board.bbs.service;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.web.board.bbs.model.entity.BoardBbsListEntity;
import com.cjw.reactivecommunityproject.web.board.bbs.model.request.BoardBbsCreateVO;
import com.cjw.reactivecommunityproject.web.board.bbs.model.request.BoardBbsModifyVO;
import java.util.List;

public interface BoardBbsService {
    RestResponseVO<Void> create(BoardBbsCreateVO bbsContent);

    RestResponseVO<Void> modify(BoardBbsModifyVO boardBbsModifyVO);

    RestResponseVO<Void> remove(Long uid);

    RestResponseVO<List<BoardBbsListEntity>> list();
}
