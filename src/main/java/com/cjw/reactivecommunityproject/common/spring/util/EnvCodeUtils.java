package com.cjw.reactivecommunityproject.common.spring.util;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.entity.RcCommonEnvCodeTypeEnum;
import com.cjw.reactivecommunityproject.server.cache.info.custom.model.CacheInfoCustomEnvCodeVO;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class EnvCodeUtils {
    private EnvCodeUtils(){}

    @Nullable
    public static <T> T convertEnvCodeByValue(@NotNull CacheInfoCustomEnvCodeVO cacheInfoCustomEnvCodeVO, @NotNull Class<T> clazz) {
        try {
            if (cacheInfoCustomEnvCodeVO == null
                    || cacheInfoCustomEnvCodeVO.getType() == null
                    || cacheInfoCustomEnvCodeVO.getValue() == null
                    || StringUtils.isBlank(cacheInfoCustomEnvCodeVO.getValue())) {
                return null;
            }
            if (cacheInfoCustomEnvCodeVO.getEnabled() == CommonEnabledEnum.N) {
                log.warn("env code is enabled n : {}", cacheInfoCustomEnvCodeVO);
                return null;
            }
            String value = cacheInfoCustomEnvCodeVO.getValue();
            RcCommonEnvCodeTypeEnum type = cacheInfoCustomEnvCodeVO.getType();

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
