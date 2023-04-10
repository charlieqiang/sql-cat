package cn.charlie.sqlcat.interceptor;

import cn.charlie.sqlcat.utils.InterceptorUtils;
import org.apache.commons.lang3.Validate;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.Properties;

/**
 * @author charlie
 * @date 4/10/2023 10:35 AM
 **/
@Component
@Intercepts({
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class})
})
public class SqlIntercepter implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (SqlIntercepterHandler.isOpen()) {
            if (invocation.getTarget() instanceof StatementHandler) {
                RoutingStatementHandler routingStatementHandler = InterceptorUtils.getRoutingStatementHandler(invocation.getTarget());
                try {
                    Validate.notNull(routingStatementHandler, "routingStatementHandler can't be null");
                    BoundSql sql = routingStatementHandler.getBoundSql();
                    String sqlStr = sql.getSql();
                    System.out.println("拦截成功, sql: " + sqlStr);
                    SqlIntercepterHandler.setSql(sqlStr);
                } catch (Exception e) {
                    System.out.println("sql参数解析错误");
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return InterceptorUtils.isRoutingStatementHandlerProxy(o) ? Plugin.wrap(o, this) : o;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
