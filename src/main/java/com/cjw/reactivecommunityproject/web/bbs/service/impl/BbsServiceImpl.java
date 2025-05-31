package com.cjw.reactivecommunityproject.web.bbs.service.impl;

import com.cjw.reactivecommunityproject.common.spring.component.RcUserComponent;
import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.web.bbs.dao.BbsDao;
import com.cjw.reactivecommunityproject.web.bbs.exception.BbsErrorMessage;
import com.cjw.reactivecommunityproject.web.bbs.exception.BbsException;
import com.cjw.reactivecommunityproject.web.bbs.model.entity.BbsInsertEntity;
import com.cjw.reactivecommunityproject.web.bbs.model.entity.BbsModifyEntity;
import com.cjw.reactivecommunityproject.web.bbs.model.request.BbsCreateVO;
import com.cjw.reactivecommunityproject.web.bbs.model.request.BbsModifyVO;
import com.cjw.reactivecommunityproject.web.bbs.service.BbsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BbsServiceImpl implements BbsService {
    private final BbsDao bbsDao;

    private final RcUserComponent rcUserComponent;

    @Override
    public RestResponseVO<Void> create(BbsCreateVO bbsContent) {
        var isDuplicatePath = bbsDao.isExistPath(bbsContent.path());
        if (Boolean.TRUE.equals(isDuplicatePath))
            throw new BbsException(BbsErrorMessage.DUPLICATE_PATH);

        var isDuplicateName = bbsDao.isExistName(bbsContent.name());
        if (Boolean.TRUE.equals(isDuplicateName))
            throw new BbsException(BbsErrorMessage.DUPLICATE_NAME);

        bbsDao.insertTransactional(BbsInsertEntity.builder()
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
    public RestResponseVO<Void> modify(BbsModifyVO bbsModifyVO) {
        var isDuplicatePath = bbsDao.isExistPath(bbsModifyVO.path(), bbsModifyVO.uid());
        if (Boolean.TRUE.equals(isDuplicatePath))
            throw new BbsException(BbsErrorMessage.DUPLICATE_PATH);

        var isDuplicateName = bbsDao.isExistName(bbsModifyVO.name(), bbsModifyVO.uid());
        if (Boolean.TRUE.equals(isDuplicateName))
            throw new BbsException(BbsErrorMessage.DUPLICATE_NAME);

        bbsDao.updateTransactional(BbsModifyEntity.builder()
                .uid(bbsModifyVO.uid())
                .path(bbsModifyVO.path())
                .name(bbsModifyVO.name())
                .description(bbsModifyVO.description())
                .userUid(rcUserComponent.getUserUid())
                .enabled(bbsModifyVO.enabled())
                .build());
        return RestResponseVO.<Void>builder()
                .result(true)
                .build();
    }

    @Override
    public RestResponseVO<Void> remove(Long uid) {
        var isDuplicateUid = bbsDao.isExistUid(uid);
        if (Boolean.FALSE.equals(isDuplicateUid))
            throw new BbsException(BbsErrorMessage.NOT_FOUND_BBS_UID);

        bbsDao.deleteTransactional(uid);

        return RestResponseVO.<Void>builder()
                .result(true)
                .build();
    }
}
