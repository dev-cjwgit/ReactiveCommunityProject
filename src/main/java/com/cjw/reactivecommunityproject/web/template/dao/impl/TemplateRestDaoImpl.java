package com.cjw.reactivecommunityproject.web.template.dao.impl;

import com.cjw.reactivecommunityproject.web.auth.mapper.AuthMapper;
import com.cjw.reactivecommunityproject.web.auth.model.entity.AuthLoginVO;
import com.cjw.reactivecommunityproject.web.auth.model.entity.AuthRegisterVO;
import com.cjw.reactivecommunityproject.web.auth.model.entity.AuthRestRcUserVO;
import com.cjw.reactivecommunityproject.web.template.dao.TemplateRestDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TemplateRestDaoImpl implements TemplateRestDao {
    private final AuthMapper authMapper;

    private final RedisTemplate<String, Object> redisTemplate;


    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void registerTransactional(AuthRegisterVO authRegisterVO, String salt) {
        int res = authMapper.register(authRegisterVO);
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
        return authMapper.selectRcUserByEmail(email);
    }

    @Override
    public AuthRestRcUserVO selectRcUserByUserUid(String uid) {
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
}
