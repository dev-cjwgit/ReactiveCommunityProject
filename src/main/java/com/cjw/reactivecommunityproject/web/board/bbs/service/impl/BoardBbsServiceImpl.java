package com.cjw.reactivecommunityproject.web.board.bbs.service.impl;

import com.cjw.reactivecommunityproject.common.spring.component.RcUserComponent;
import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.web.board.bbs.dao.BoardBbsDao;
import com.cjw.reactivecommunityproject.web.board.bbs.exception.BoardBbsErrorMessage;
import com.cjw.reactivecommunityproject.web.board.bbs.exception.BoardBbsException;
import com.cjw.reactivecommunityproject.web.board.bbs.model.entity.BoardBbsInsertEntity;
import com.cjw.reactivecommunityproject.web.board.bbs.model.entity.BoardBbsListEntity;
import com.cjw.reactivecommunityproject.web.board.bbs.model.entity.BoardBbsModifyEntity;
import com.cjw.reactivecommunityproject.web.board.bbs.model.request.BoardBbsCreateVO;
import com.cjw.reactivecommunityproject.web.board.bbs.model.request.BoardBbsModifyVO;
import com.cjw.reactivecommunityproject.web.board.bbs.service.BoardBbsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardBbsServiceImpl implements BoardBbsService {
    private final BoardBbsDao boardBbsDao;

    private final RcUserComponent rcUserComponent;

    @Override
    public RestResponseVO<Void> create(BoardBbsCreateVO bbsContent) {
        var isDuplicatePath = boardBbsDao.isExistPath(bbsContent.path());
        if (Boolean.TRUE.equals(isDuplicatePath))
            throw new BoardBbsException(BoardBbsErrorMessage.DUPLICATE_PATH);

        var isDuplicateName = boardBbsDao.isExistName(bbsContent.name());
        if (Boolean.TRUE.equals(isDuplicateName))
            throw new BoardBbsException(BoardBbsErrorMessage.DUPLICATE_NAME);

        boardBbsDao.insertTransactional(BoardBbsInsertEntity.builder()
                .path(bbsContent.path())
                .name(bbsContent.name())
                .description(bbsContent.description())
                .userUid(rcUserComponent.getUserUid())
                .build());

        return RestResponseVO.<Void>builder()
                .result(true)
                .build();
    }

    @Override
    public RestResponseVO<Void> modify(BoardBbsModifyVO boardBbsModifyVO) {
        var isDuplicatePath = boardBbsDao.isExistPath(boardBbsModifyVO.path(), boardBbsModifyVO.uid());
        if (Boolean.TRUE.equals(isDuplicatePath))
            throw new BoardBbsException(BoardBbsErrorMessage.DUPLICATE_PATH);

        var isDuplicateName = boardBbsDao.isExistName(boardBbsModifyVO.name(), boardBbsModifyVO.uid());
        if (Boolean.TRUE.equals(isDuplicateName))
            throw new BoardBbsException(BoardBbsErrorMessage.DUPLICATE_NAME);

        boardBbsDao.updateTransactional(BoardBbsModifyEntity.builder()
                .uid(boardBbsModifyVO.uid())
                .path(boardBbsModifyVO.path())
                .name(boardBbsModifyVO.name())
                .description(boardBbsModifyVO.description())
                .userUid(rcUserComponent.getUserUid())
                .enabled(boardBbsModifyVO.enabled())
                .build());
        return RestResponseVO.<Void>builder()
                .result(true)
                .build();
    }

    @Override
    public RestResponseVO<Void> remove(Long uid) {
        var isDuplicateUid = boardBbsDao.isExistUid(uid);
        if (Boolean.FALSE.equals(isDuplicateUid))
            throw new BoardBbsException(BoardBbsErrorMessage.NOT_FOUND_BBS_UID);

        boardBbsDao.deleteTransactional(uid);

        return RestResponseVO.<Void>builder()
                .result(true)
                .build();
    }

    @Override
    public RestResponseVO<List<BoardBbsListEntity>> list() {
        var list = CollectionUtils.emptyIfNull(boardBbsDao.selectListAll())
                .parallelStream()
                .filter(o -> CommonEnabledEnum.Y.equals(o.enabled()))
                .toList();
        return RestResponseVO.<List<BoardBbsListEntity>>builder()
                .result(true)
                .data(list)
                .build();
    }
}
