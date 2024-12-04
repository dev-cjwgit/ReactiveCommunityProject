package com.cjw.reactivecommunityproject.server.auth.service.impl;

import com.cjw.reactivecommunityproject.server.auth.dao.AuthDAO;
import com.cjw.reactivecommunityproject.server.auth.model.AuthRegisterVO;
import com.cjw.reactivecommunityproject.server.auth.service.AuthService;
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
        log.info("AuthDAO.register() : {}", res);

        redisTemplate.opsForValue().set(StringUtils.join(authRegisterVO.uid(), ".salt"), salt);
        log.info("redisTemplate.opsForValue.set : {}", salt);
    }
}
