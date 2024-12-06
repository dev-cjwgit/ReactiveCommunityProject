package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.service.impl;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.service.PaginationService;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.dao.SysResourceMgmtRestDAO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtListVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request.SysResourceMgmtSelectListVO;
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

    private final PaginationService paginationService;

    @Override
    public RestResponseVO<List<SysResourceMgmtListVO>> getResourceMgmtList(SysResourceMgmtSelectListVO sysResourceMgmtSelectListVO, PaginationRequestVO paginationRequestVO) {
        var list = sysResourceMgmtRestDAO.selectList(
                paginationService.createPagination(sysResourceMgmtSelectListVO, paginationRequestVO)
        );

        return RestResponseVO.<List<SysResourceMgmtListVO>>builder()
                .result(true)
                .data(list)
                .build()
                ;
    }
}
