package com.cjw.reactivecommunityproject.web.bbs.service;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.web.bbs.model.request.BbsCreateVO;

public interface BbsService {
    RestResponseVO<Void> create(BbsCreateVO bbsContent);
}
