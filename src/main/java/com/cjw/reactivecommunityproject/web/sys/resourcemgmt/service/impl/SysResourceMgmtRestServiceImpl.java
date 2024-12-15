package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.service.impl;

import com.cjw.reactivecommunityproject.common.spring.component.RcUserComponent;
import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.service.PaginationService;
import com.cjw.reactivecommunityproject.server.sys.resourcemgmt.model.SysResourceMgmtInsertVO;
import com.cjw.reactivecommunityproject.server.sys.resourcemgmt.model.SysResourceMgmtUpdateVO;
import com.cjw.reactivecommunityproject.server.sys.resourcemgmt.service.SysResourceMgmtService;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.dao.SysResourceMgmtRestDAO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.exception.SysResourceMgmtRestErrorMessage;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.exception.SysResourceMgmtRestException;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtDetailVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtListVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request.SysResourceMgmtCreateVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request.SysResourceMgmtModifyVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request.SysResourceMgmtReadListVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.service.SysResourceMgmtRestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysResourceMgmtRestServiceImpl implements SysResourceMgmtRestService {
    private final SysResourceMgmtRestDAO sysResourceMgmtRestDAO;

    private final SysResourceMgmtService sysResourceMgmtService;

    private final PaginationService paginationService;

    private final RcUserComponent rcUserComponent;

    @Override
    public RestResponseVO<List<SysResourceMgmtListVO>> readResourceMgmtList(SysResourceMgmtReadListVO sysResourceMgmtReadListVO, PaginationRequestVO paginationRequestVO) {
        var list = sysResourceMgmtRestDAO.selectList(
                paginationService.createPagination(sysResourceMgmtReadListVO, paginationRequestVO)
        );

        return RestResponseVO.<List<SysResourceMgmtListVO>>builder()
                .result(true)
                .data(list)
                .build();
    }

    @Override
    public RestResponseVO<SysResourceMgmtDetailVO> readDetail(Long uid) {
        var detail = sysResourceMgmtRestDAO.selectDetail(uid);
        if (detail == null) {
            throw new SysResourceMgmtRestException(SysResourceMgmtRestErrorMessage.NOT_FOUND_RESOURCE_DETAIL);
        }
        return RestResponseVO.<SysResourceMgmtDetailVO>builder()
                .result(true)
                .data(detail)
                .build();
    }

    @Override
    public RestResponseVO<Void> create(SysResourceMgmtCreateVO sysResourcemgmtCreateVO) {
        var isDuplicate = sysResourceMgmtRestDAO.isDuplicate(
                sysResourcemgmtCreateVO.method(),
                sysResourcemgmtCreateVO.urlPattern()
        );

        if (isDuplicate) {
            throw new SysResourceMgmtRestException(SysResourceMgmtRestErrorMessage.DUPLICATE_RESOURCE_INFO);
        }
        sysResourceMgmtService.insert(
                SysResourceMgmtInsertVO.builder()
                        .method(sysResourcemgmtCreateVO.method())
                        .urlPattern(sysResourcemgmtCreateVO.urlPattern())
                        .description(sysResourcemgmtCreateVO.description())
                        .userUid(rcUserComponent.getUserUid())
                        .build()
        );

        return RestResponseVO.<Void>builder()
                .result(true)
                .build();
    }


    @Override
    public RestResponseVO<Void> modify(SysResourceMgmtModifyVO sysResourceMgmtModifyVO) {
        var isOwner = sysResourceMgmtRestDAO.isOwner(sysResourceMgmtModifyVO.uid(), rcUserComponent.getUserUid());

        if (Boolean.FALSE.equals(isOwner)) {
            throw new SysResourceMgmtRestException(SysResourceMgmtRestErrorMessage.UNAUTHORIZED_ACCESS);
        }

        sysResourceMgmtService.update(
                SysResourceMgmtUpdateVO.builder()
                        .uid(sysResourceMgmtModifyVO.uid())
                        .method(sysResourceMgmtModifyVO.method())
                        .urlPattern(sysResourceMgmtModifyVO.urlPattern())
                        .description(sysResourceMgmtModifyVO.description())
                        .enabled(sysResourceMgmtModifyVO.enabled())
                        .userUid(rcUserComponent.getUserUid())
                        .build()
        );

        return RestResponseVO.<Void>builder()
                .result(true)
                .build();
    }


    @Override
    public RestResponseVO<Void> remove(Long uid) {
        var isOwner = sysResourceMgmtRestDAO.isOwner(uid, rcUserComponent.getUserUid());

        if (Boolean.FALSE.equals(isOwner)) {
            throw new SysResourceMgmtRestException(SysResourceMgmtRestErrorMessage.UNAUTHORIZED_ACCESS);
        }

        sysResourceMgmtService.delete(uid);

        return RestResponseVO.<Void>builder()
                .result(true)
                .build();
    }
}
