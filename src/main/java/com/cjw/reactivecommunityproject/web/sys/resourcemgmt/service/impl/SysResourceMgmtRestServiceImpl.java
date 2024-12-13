package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.service.impl;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.service.PaginationService;
import com.cjw.reactivecommunityproject.server.sys.resourcemgmt.model.SysResourceMgmtInsertVO;
import com.cjw.reactivecommunityproject.server.sys.resourcemgmt.service.SysResourceMgmtService;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.dao.SysResourceMgmtRestDAO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.exception.SysResourceMgmtRestErrorMessage;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.exception.SysResourceMgmtRestException;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtDetailVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtListVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request.SysResourceMgmtCreateVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request.SysResourceMgmtReadListVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.service.SysResourceMgmtRestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysResourceMgmtRestServiceImpl implements SysResourceMgmtRestService {
    private final SysResourceMgmtRestDAO sysResourceMgmtRestDAO;

    private final SysResourceMgmtService sysResourceMgmtService;

    private final PaginationService paginationService;

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
    public RestResponseVO<SysResourceMgmtDetailVO> readResourceMgmtDetail(Long uid) {
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
    public RestResponseVO<Void> createResource(SysResourceMgmtCreateVO sysResourcemgmtCreateVO) {
        var isDuplicate = sysResourceMgmtRestDAO.isDuplicate(
                sysResourcemgmtCreateVO.method(),
                sysResourcemgmtCreateVO.urlPattern()
        );

        if (isDuplicate) {
            throw new SysResourceMgmtRestException(SysResourceMgmtRestErrorMessage.DUPLICATE_RESOURCE_INFO);
        }
        sysResourceMgmtService.insertResourceMgmt(
                SysResourceMgmtInsertVO.builder()
                        .method(sysResourcemgmtCreateVO.method())
                        .urlPattern(sysResourcemgmtCreateVO.urlPattern())
                        .description(sysResourcemgmtCreateVO.description())
                        .userUid(SecurityContextHolder.getContext().getAuthentication().getName())
                        .build()
        );

        return RestResponseVO.<Void>builder()
                .result(true)
                .build();
    }
}
