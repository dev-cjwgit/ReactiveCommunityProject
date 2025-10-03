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
    private static final int LIMIT = 500;

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
            case String s -> {
                String str = String.valueOf(v);
                return (str.length() > LIMIT) ? s.substring(0, LIMIT) + "...(truncated)" : str;
            }
            default ->
                throw new IllegalArgumentException("Unsupported value type: " + v);

        }

    }
}
