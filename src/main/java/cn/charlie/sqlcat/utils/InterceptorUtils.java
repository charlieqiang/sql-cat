package cn.charlie.sqlcat.utils;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;

import java.lang.reflect.Proxy;

/**
 * @author charlie
 * @date 4/10/2023 10:44 AM
 **/
public class InterceptorUtils {
    private InterceptorUtils() {
    }

    public static boolean isRoutingStatementHandlerProxy(Object target) {
        return Proxy.isProxyClass(target.getClass()) ? isRoutingStatementHandlerProxy(ReflectionUtils.getFieldValue(Proxy.getInvocationHandler(target), "target")) : target instanceof RoutingStatementHandler;
    }

    public static RoutingStatementHandler getRoutingStatementHandler(Object target) {
        if (Proxy.isProxyClass(target.getClass())) {
            return getRoutingStatementHandler(ReflectionUtils.getFieldValue(Proxy.getInvocationHandler(target), "target"));
        } else {
            return target instanceof RoutingStatementHandler ? (RoutingStatementHandler) target : null;
        }
    }
}
