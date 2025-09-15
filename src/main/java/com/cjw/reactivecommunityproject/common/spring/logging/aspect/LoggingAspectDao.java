package com.cjw.reactivecommunityproject.common.spring.logging.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspectDao {

    @Around("execution(public * com.cjw..*Dao.*(..)) || execution(public * com.cjw..*DaoImpl.*(..))")
    public Object logAroundDaoImplOnly(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getSignature().getDeclaringTypeName(); // 프록시 기준 시그니처(인터페이스)일 수 있음
        String method = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();

        long start = System.currentTimeMillis();
        log.info(">>> {}.{} args={}", className, method, this.safeArgs(args));

        try {
            Object result = pjp.proceed(); // 실제 DaoImpl로 위임되어 실행됨
            long elapsed = System.currentTimeMillis() - start;
            log.info("<<< {}.{} return={} elapsed={}ms", className, method, this.safeValue(result), elapsed);
            return result;
        } catch (Throwable ex) {
            long elapsed = System.currentTimeMillis() - start;

            log.error("!!! {}.{} threw after {}ms: {}", className, method, elapsed, ex, ex);
            throw ex;
        }
    }

    private String safeArgs(Object[] args) {
        if (args == null) return "[]";
        int max = Math.min(args.length, 5); // 최대 5개까지만
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < max; i++) {
            sb.append(this.safeValue(args[i]));
            if (i < max - 1) sb.append(", ");
        }
        if (args.length > max) sb.append(", ...");
        return sb.append("]").toString();
    }

    private String safeValue(Object v) {
        switch (v) {
            case null -> {
                return "null";
            }
            case byte[] b -> {
                return "byte[" + b.length + "]";
            }
            case java.util.Collection<?> c -> {
                return c.getClass().getSimpleName() + "(size=" + c.size() + ")";
            }
            case java.util.Map<?, ?> m -> {
                return m.getClass().getSimpleName() + "(size=" + m.size() + ")";
            }
            default -> {
            }
        }
        String s = String.valueOf(v);
        int LIMIT = 500; // 로그 1건당 최대 500자
        return (s.length() > LIMIT) ? s.substring(0, LIMIT) + "...(truncated)" : s;
    }
}
