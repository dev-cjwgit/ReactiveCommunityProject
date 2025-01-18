package com.cjw.reactivecommunityproject.web.auth.dao.impl;

import com.cjw.reactivecommunityproject.web.auth.dao.AuthRestDao;
import com.cjw.reactivecommunityproject.web.auth.mapper.AuthRestMapper;
import com.cjw.reactivecommunityproject.web.auth.model.entity.AuthLoginVO;
import com.cjw.reactivecommunityproject.web.auth.model.entity.AuthRegisterVO;
import com.cjw.reactivecommunityproject.web.auth.model.entity.AuthRestRcUserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthRestDaoImpl implements AuthRestDao {
    private final AuthRestMapper authRestMapper;

    private final RedisTemplate<String, Object> redisTemplate;


    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void registerTransactional(AuthRegisterVO authRegisterVO, String salt) {
        int res = authRestMapper.register(authRegisterVO);
        log.info("AuthDao.register : {}", res);

        redisTemplate.opsForValue().set(StringUtils.join(authRegisterVO.uid(), ".salt"), salt);
        log.info("AuthServiceImpl.register - redisTemplate.opsForValue.set : [String] {}", salt);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void loginTransactional(AuthLoginVO authLoginVO) {
        redisTemplate.opsForValue().set(StringUtils.join(authLoginVO.userUid(), ".refresh"), authLoginVO.refreshToken());
        log.info("AuthServiceImpl.login - redisTemplate.opsForValue.set : [AuthLoginVO] {}", authLoginVO);
    }

    @Override
    public AuthRestRcUserVO selectRcUserByEmail(String email) {
        return authRestMapper.selectRcUserByEmail(email);
    }

    @Override
    public AuthRestRcUserVO selectRcUserByUserUid(String uid) {
        return authRestMapper.selectRcUserByUserUid(uid);
    }

    @Override
    public Boolean isExistUserByEmail(String email) {
        return authRestMapper.isExistUserByEmail(email);
    }

    @Override
    public Boolean isExistUserByNickname(String nickname) {
        return authRestMapper.isExistUserByNickname(nickname);
    }
}
