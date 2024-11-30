package com.cjw.reactivecommunityproject.server.auth.service.impl;

import com.cjw.reactivecommunityproject.server.auth.dao.AuthDAO;
import com.cjw.reactivecommunityproject.server.auth.model.AuthRegisterVO;
import com.cjw.reactivecommunityproject.server.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthDAO authDAO;

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void register(AuthRegisterVO authRegisterVO) {
        int res = authDAO.register(authRegisterVO);
        log.info("AuthDAO.register() : {}", res);
    }
}
