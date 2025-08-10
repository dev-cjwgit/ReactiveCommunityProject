package com.cjw.reactivecommunityproject.server.cache.manage.load.service.impl;

import com.cjw.reactivecommunityproject.server.cache.manage.common.interfaces.CacheManageCommonTableNaming;
import com.cjw.reactivecommunityproject.server.cache.manage.common.model.CacheManageCommonInfoCustomTableEnum;
import com.cjw.reactivecommunityproject.server.cache.manage.common.model.CacheManageCommonInfoDataTableEnum;
import com.cjw.reactivecommunityproject.server.cache.manage.common.model.CacheManageCommonInfoTypeEnum;
import com.cjw.reactivecommunityproject.server.cache.info.custom.service.CacheInfoCustomService;
import com.cjw.reactivecommunityproject.server.cache.info.data.service.CacheInfoDataService;
import com.cjw.reactivecommunityproject.server.cache.manage.load.model.CacheManageLoadTableVO;
import com.cjw.reactivecommunityproject.server.cache.manage.load.model.CacheManageLoadVO;
import com.cjw.reactivecommunityproject.server.cache.manage.load.service.CacheManageLoadService;
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
public class CacheManageLoadServiceImpl implements CacheManageLoadService {
    public static final String LOAD_METHOD_PREFIX = "get";

    private final CacheInfoDataService cacheInfoDataService;
    private final CacheInfoCustomService cacheInfoCustomService;


    @Override
    public Flux<CacheManageLoadTableVO> createTableVO(CacheManageLoadVO cacheManageLoadVO) {
        var type = cacheManageLoadVO.type();

        if (type == null) {
            log.warn("type is null");
            return Flux.empty();
        }

        return Flux.fromIterable(CollectionUtils.emptyIfNull(cacheManageLoadVO.table()))
                .flatMap(table -> {
                    try {
                        CacheManageCommonTableNaming enumValue = switch (type) {
                            case DATA -> CacheManageCommonInfoDataTableEnum.valueOf(table.tableName());
                            case CUSTOM -> CacheManageCommonInfoCustomTableEnum.valueOf(table.tableName());
                        };

                        return Mono.just(CacheManageLoadTableVO.builder()
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

    private Object resolveService(CacheManageCommonInfoTypeEnum type) {
        return switch (type) {
            case DATA -> cacheInfoDataService;
            case CUSTOM -> cacheInfoCustomService;
        };
    }

    private String createGetMethodName(String tableName) {
        return StringUtils.join(LOAD_METHOD_PREFIX, this.convertSnakeCaseToMacelCase(tableName));
    }

    private String convertSnakeCaseToMacelCase(String rawName) {
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
    public void load(CacheManageLoadTableVO cacheManageLoadTableVO) {
        String methodName = this.createGetMethodName(cacheManageLoadTableVO.table().getMethodName());
        Object service = this.resolveService(cacheManageLoadTableVO.type());

        this.invokeMethod(service, methodName, cacheManageLoadTableVO.parameters());
    }
}
