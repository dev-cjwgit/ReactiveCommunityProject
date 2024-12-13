package com.cjw.reactivecommunityproject.server.auth.service.impl;

import com.cjw.reactivecommunityproject.server.auth.model.AuthLoginVO;
import com.cjw.reactivecommunityproject.server.auth.service.AuthService;
import com.cjw.reactivecommunityproject.server.auth.dao.AuthDAO;
import com.cjw.reactivecommunityproject.server.auth.model.AuthRegisterVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthDAO authDAO;

    private final RedisTemplate<String, Object> redisTemplate;


    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void register(AuthRegisterVO authRegisterVO, String salt) {
        int res = authDAO.register(authRegisterVO);
        log.info("AuthDAO.register : {}", res);

        redisTemplate.opsForValue().set(StringUtils.join(authRegisterVO.uid(), ".salt"), salt);
        log.info("AuthServiceImpl.register - redisTemplate.opsForValue.set : [String] {}", salt);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void login(AuthLoginVO authLoginVO) {
        redisTemplate.opsForValue().set(StringUtils.join(authLoginVO.userUid(), ".refresh"), authLoginVO.refreshToken());
        log.info("AuthServiceImpl.login - redisTemplate.opsForValue.set : [AuthLoginVO] {}", authLoginVO);
    }
}
