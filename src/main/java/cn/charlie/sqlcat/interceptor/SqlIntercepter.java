package cn.charlie.sqlcat.interceptor;

import cn.charlie.sqlcat.utils.InterceptorUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.jdbc.PreparedStatementLogger;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author charlie
 * @date 4/10/2023 10:35 AM
 **/
@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class})})
public class SqlIntercepter implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (SqlIntercepterHandler.isOpen()) {
            Validate.notNull(invocation, "invocation can't be null");
            RoutingStatementHandler routingStatementHandler = InterceptorUtils.getRoutingStatementHandler(invocation.getTarget());
            try {
                String sqlStr = parseSqlIncludeParam(invocation, routingStatementHandler);
                SqlIntercepterHandler.setSql(sqlStr);
            } catch (Exception e) {
                System.out.println("sql解析错误");
            }
        }
        return invocation.proceed();
    }

    private String parseSqlIncludeParam(Invocation invocationForParam, RoutingStatementHandler routingStatementHandlerForSql) throws IllegalAccessException {
        Validate.notNull(routingStatementHandlerForSql, "routingStatementHandler can't be null");

        BoundSql sql = routingStatementHandlerForSql.getBoundSql();
        String sqlStr = sql.getSql();

        Statement statement = (Statement) invocationForParam.getArgs()[0];
        PreparedStatementLogger logger = (PreparedStatementLogger) SystemMetaObject.forObject(statement).getValue("h");
        Field field = FieldUtils.getField(logger.getClass(), "columnValues", true);

        sqlStr = sqlStr.replace("?", "'%s'");
        sqlStr = String.format(sqlStr, ((ArrayList<?>) field.get(logger)).toArray());

        return sqlStr;
    }

    @Override
    public Object plugin(Object o) {
        return InterceptorUtils.isRoutingStatementHandlerProxy(o) ? Plugin.wrap(o, this) : o;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
