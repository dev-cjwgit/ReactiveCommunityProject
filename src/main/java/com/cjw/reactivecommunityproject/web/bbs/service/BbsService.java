package com.cjw.reactivecommunityproject.web.bbs.service;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.web.bbs.model.entity.BbsListEntity;
import com.cjw.reactivecommunityproject.web.bbs.model.request.BbsCreateVO;
import com.cjw.reactivecommunityproject.web.bbs.model.request.BbsModifyVO;
import java.util.List;

public interface BbsService {
    RestResponseVO<Void> create(BbsCreateVO bbsContent);

    RestResponseVO<Void> modify(BbsModifyVO bbsModifyVO);

    RestResponseVO<Void> remove(Long uid);

    RestResponseVO<List<BbsListEntity>> list();
}
