package com.cjw.reactivecommunityproject.server.cache.load.service.impl;

import com.cjw.reactivecommunityproject.server.cache.common.interfaces.CacheCommonTableNaming;
import com.cjw.reactivecommunityproject.server.cache.common.model.CacheCommonCustomTableEnum;
import com.cjw.reactivecommunityproject.server.cache.common.model.CacheCommonDataTableEnum;
import com.cjw.reactivecommunityproject.server.cache.common.model.CacheCommonTypeEnum;
import com.cjw.reactivecommunityproject.server.cache.custom.service.CacheCustomService;
import com.cjw.reactivecommunityproject.server.cache.data.service.CacheDataService;
import com.cjw.reactivecommunityproject.server.cache.load.model.CacheLoadTableVO;
import com.cjw.reactivecommunityproject.server.cache.load.model.CacheLoadVO;
import com.cjw.reactivecommunityproject.server.cache.load.service.CacheLoadService;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheLoadServiceImpl implements CacheLoadService {
    public static final String LOAD_METHOD_PREFIX = "get";

    private final CacheDataService cacheDataService;
    private final CacheCustomService cacheCustomService;


    @Override
    public Flux<CacheLoadTableVO> createCacheLoadTableVO(CacheLoadVO cacheLoadVO) {
        var type = cacheLoadVO.type();

        if (type == null) {
            log.warn("type is null");
            return Flux.empty();
        }

        return Flux.fromIterable(CollectionUtils.emptyIfNull(cacheLoadVO.table()))
                .flatMap(table -> {
                    try {
                        CacheCommonTableNaming enumValue = switch (type) {
                            case DATA -> CacheCommonDataTableEnum.valueOf(table.tableName());
                            case CUSTOM -> CacheCommonCustomTableEnum.valueOf(table.tableName());
                        };

                        return Mono.just(CacheLoadTableVO.builder()
                                .type(type)
                                .table(enumValue)
                                .parameters(table.parameters())
                                .build());
                    } catch (IllegalArgumentException ex) {
                        log.warn("Invalid table enum value: [{}] for type [{}]", table, type);
                        return Mono.empty();
                    }
                });
    }

    private Object resolveService(CacheCommonTypeEnum type) {
        return switch (type) {
            case DATA -> cacheDataService;
            case CUSTOM -> cacheCustomService;
        };
    }

    private String createClearMethodName(String tableName) {
        return StringUtils.join(LOAD_METHOD_PREFIX, this.toCamelCase(tableName));
    }

    private String toCamelCase(String rawName) {
        return Arrays.stream(rawName.toLowerCase().split("_"))
                .map(StringUtils::capitalize)
                .collect(Collectors.joining());
    }

    // 인자의 개수를 확인하는 함수
    private boolean hasSameArity(Method method, List<Object> args) {
        return method.getParameterCount() == args.size();
    }

    // 각 파라미터가 호환되는지 확인하는 함수
    private boolean parametersMatch(Class<?>[] paramTypes, List<Object> args) {
        for (int i = 0; i < paramTypes.length; i++) {
            if (!this.isCompatible(paramTypes[i], args.get(i))) {
                return false;
            }
        }
        return true;
    }

    // 리플렉션 전 메소드 + 파라미터 개수 및 타입을 모두 검사하는 함수
    private boolean isSameSignature(Method method, String name, List<Object> args) {
        return method.getName().equals(name)
                && this.hasSameArity(method, args)
                && this.parametersMatch(method.getParameterTypes(), args);
    }


    private Method findMethod(Class<?> clazz, String methodName, List<Object> args) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (this.isSameSignature(method, methodName, args)) {
                return method;
            }
        }
        return null;
    }

    // null 및 원시타입 타입 호환 검사하는 함수
    private boolean isCompatible(Class<?> paramType, Object arg) {
        if (arg == null) {
            return !paramType.isPrimitive();          // primitive 에는 null 불가
        }
        return paramType.isAssignableFrom(arg.getClass());
    }

    private void invokeMethod(Object service, String methodName, List<Object> args) {
        try {
            Method target = this.findMethod(service.getClass(), methodName, args);
            if (target == null) {
                log.error("No matching method [{}] on {} with args {}", methodName, service.getClass().getSimpleName(), args);
                return;
            }
            if (!target.canAccess(service)) {
                target.setAccessible(true);
            }
            target.invoke(service, args.toArray());   // 인자 그대로 전달
            log.info("Call Method [{}] on {} with args {}", methodName, service.getClass().getSimpleName(), args);
        } catch (Exception e) {
            log.error("Failed to invoke method: {}", methodName, e);
        }
    }


    @Override
    public void loadCache(CacheLoadTableVO cacheLoadTableVO) {
        String methodName = this.createClearMethodName(cacheLoadTableVO.table().getTableName());
        Object service = this.resolveService(cacheLoadTableVO.type());

        this.invokeMethod(service, methodName, cacheLoadTableVO.parameters());
    }
}
