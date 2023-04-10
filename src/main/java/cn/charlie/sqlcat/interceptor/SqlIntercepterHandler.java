package cn.charlie.sqlcat.interceptor;

/**
 * @author charlie
 * @date 4/10/2023 10:36 AM
 **/
public class SqlIntercepterHandler {

    private static final ThreadLocal<Boolean> openKey = new ThreadLocal<>();
    private static final ThreadLocal<String> sql = new ThreadLocal<>();

    public SqlIntercepterHandler() {
    }

    public static void open() {
        System.out.println("开启sql拦截功能");
        openKey.set(true);
    }

    public static void finish() {
        System.out.println("关闭sql拦截功能");
        openKey.remove();
    }

    public static void setSql(String sqlStr) {
        System.out.println("缓存sql");
        sql.set(sqlStr);
    }

    public static String getSql() {
        System.out.println("获取sql");
        return sql.get();
    }

    public static void finishSql() {
        System.out.println("关闭sql缓存");
        sql.remove();
    }

    public static boolean isOpen() {
        return openKey.get() != null ? (Boolean) openKey.get() : false;
    }
}
