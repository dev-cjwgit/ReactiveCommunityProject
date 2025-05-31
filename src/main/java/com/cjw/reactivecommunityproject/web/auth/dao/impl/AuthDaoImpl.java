package com.cjw.reactivecommunityproject.web.auth.dao.impl;

import com.cjw.reactivecommunityproject.web.auth.dao.AuthDao;
import com.cjw.reactivecommunityproject.web.auth.mapper.AuthMapper;
import com.cjw.reactivecommunityproject.web.auth.model.entity.AuthLoginEntity;
import com.cjw.reactivecommunityproject.web.auth.model.entity.AuthRcUserEntity;
import com.cjw.reactivecommunityproject.web.auth.model.entity.AuthRegisterEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AuthDaoImpl implements AuthDao {
    private final AuthMapper authMapper;

    private final RedisTemplate<String, Object> redisTemplate;


    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void registerTransactional(AuthRegisterEntity authRegisterEntity, String salt) {
        int res = authMapper.register(authRegisterEntity);
        log.info("AuthDao.register() : {}", res);

        redisTemplate.opsForValue().set(StringUtils.join(authRegisterEntity.uid(), ".salt"), salt);
        log.info("AuthServiceImpl.register() - redisTemplate.opsForValue.set() : [String] {}", salt);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void loginTransactional(AuthLoginEntity authLoginEntity) {
        redisTemplate.opsForValue().set(StringUtils.join(authLoginEntity.userUid(), ".refresh"), authLoginEntity.refreshToken());
        log.info("AuthServiceImpl.login() - redisTemplate.opsForValue.set() : [AuthLoginVO] {}", authLoginEntity);
    }

    @Override
    public AuthRcUserEntity selectRcUserByEmail(String email) {
        return authMapper.selectRcUserByEmail(email);
    }

    @Override
    public AuthRcUserEntity selectRcUserByUserUid(String uid) {
        return authMapper.selectRcUserByUserUid(uid);
    }

    @Override
    public Boolean isExistUserByEmail(String email) {
        return authMapper.isExistUserByEmail(email);
    }

    @Override
    public Boolean isExistUserByNickname(String nickname) {
        return authMapper.isExistUserByNickname(nickname);
    }

    @Override
    public Boolean isExistUserByPhoneNumber(String phoneNumber) {
        return authMapper.isExistUserByPhoneNumber(phoneNumber);
    }
}
