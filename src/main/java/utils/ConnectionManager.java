package utils;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class ConnectionManager {

    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";
    private static final int DEFAULT_POOL_SIZE = 10;
    private static final String POOL_SIZE_KEY = "db.pool.size";
    private static BlockingQueue<Connection> pool;
    static {
        initConnectionPool();
    }

    private static void initConnectionPool() {
        String poolSize = PropertiesUtil.get("db.pool.size");
        int size = poolSize == null ? DEFAULT_POOL_SIZE : Integer.parseInt(poolSize);
        pool = new ArrayBlockingQueue(size);
        for (int i = 0; i < size; i++) {
            Connection conn = open();
            var proxyConnection = (Connection) Proxy.newProxyInstance(ConnectionManager.class.getClassLoader(),
                    new Class[]{Connection.class},
                    (proxy, method, args) -> method.getName().equals("close") ?
                            pool.add((Connection) proxy) :
                            method.invoke(conn, args));
            pool.add(proxyConnection);
        }
    }

    public static Connection get() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private ConnectionManager() {}

    private static Connection open() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}