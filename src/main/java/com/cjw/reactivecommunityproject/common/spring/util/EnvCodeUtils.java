package com.cjw.reactivecommunityproject.common.spring.util;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.entity.RcCommonEnvCodeTypeEnum;
import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomEnvCodeVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class EnvCodeUtils {
    private EnvCodeUtils(){}
    
    public static <T> T convertEnvCodeByValue(CacheCustomEnvCodeVO cacheCustomEnvCodeVO, Class<T> clazz) {
        try {
            if (cacheCustomEnvCodeVO == null
                    || cacheCustomEnvCodeVO.getType() == null
                    || cacheCustomEnvCodeVO.getValue() == null
                    || StringUtils.isBlank(cacheCustomEnvCodeVO.getValue())) {
                return null;
            }
            if (cacheCustomEnvCodeVO.getEnabled() == CommonEnabledEnum.N) {
                log.warn("env code is enabled n : {}", cacheCustomEnvCodeVO);
                return null;
            }
            String value = cacheCustomEnvCodeVO.getValue();
            RcCommonEnvCodeTypeEnum type = cacheCustomEnvCodeVO.getType();

            Object result = switch (type) {
                case INTEGER -> Integer.valueOf(value);
                case LONG -> Long.valueOf(value);
                case BOOLEAN -> Boolean.valueOf(value);
                case STRING -> value;
            };

            // 형변환 검증 및 반환
            if (clazz.isInstance(result)) {
                return clazz.cast(result);
            } else {
                throw new ClassCastException("Cannot cast value to the specified class: " + clazz.getName());
            }
        } catch (Exception ex) {
            log.error("EnvCodeUtils.convertEnvCodeByValue exception", ex);
            return null;
        }
    }
}
