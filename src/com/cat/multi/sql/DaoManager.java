package com.cat.multi.sql;

import com.sun.istack.internal.Nullable;

import javax.xml.transform.Source;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;

/**
 * Created by cat on 2018/1/28.
 * db sql
 */
public class DaoManager {

    private static final boolean SHOW_SQL = false;
    private static final boolean SHOW_WARING = false;
    private static final String DB_NAME = "multiThreads.sqlite";
    private static final String TABLE_NAME = "url2destPath";
    private static final String COLUMN_URL = "URL";
    private static final String COLUMN_DEST_PATH = "DEST_PATH";
    private static final String COLUMN_KW_ARGS = "KW_ARGS";
    ;

    static Connection openDbConnection(String dbName) {

        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            // jdbc:sqlite:{file::identifier.sqlite}?
            if (!dbName.endsWith(".sqlite")) {
                dbName = dbName + ".sqlite";
            }
            c = DriverManager.getConnection("jdbc:sqlite:raw/db/" + dbName);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        }
        // never close connection here
        return c;
    }

    public static Connection openDbConnection() {
        return openDbConnection(DB_NAME);
    }

    public static boolean createTable() {

        Connection c = null;
        Statement stmt = null;
        try {
            c = openDbConnection();
            stmt = c.createStatement();
            String sql = String.format("CREATE TABLE IF NOT EXISTS %s " +
                            "(%s CHAR(500) PRIMARY KEY NOT NULL,%s CHAR(300) NOT NULL,%s CHAR(400))",
                    TABLE_NAME, COLUMN_URL, COLUMN_DEST_PATH, COLUMN_KW_ARGS);
            if (SHOW_SQL) {
                System.out.println("create table sql=" + sql);
            }
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            try {
                if (stmt != null) {
                    stmt.cancel();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null)
                    c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static boolean dropTable() {
        Connection c = null;
        Statement stmt = null;
        try {
            c = openDbConnection();
            stmt = c.createStatement();
            String sql = String.format("DROP TABLE %s;", TABLE_NAME);
            if (SHOW_SQL) {
                System.out.println("drop table sql=" + sql);
            }
            return stmt.execute(sql);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Set<UriBean> select() {

        String sql = String.format("SELECT * FROM %s;", TABLE_NAME);
        if (SHOW_SQL) {
            System.out.println("select sql=" + sql);
        }
        HashSet<UriBean> beanArrayList = new HashSet<>();

        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {

            c = openDbConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            rs = stmt.executeQuery(sql);
            //noinspection Duplicates
            while (rs.next()) {
                String url = rs.getString("URL");
                String destPath = rs.getString("DEST_PATH");
                UriBean bean = new UriBean(url, destPath);
                beanArrayList.add(bean);
            }
        } catch (Exception e) {
            beanArrayList.clear();
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return beanArrayList;
    }

    @Nullable
    public static String select(String key) {
        HashSet<UriBean> beanArrayList = new HashSet<>();

        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            c = openDbConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = String.format("SELECT * FROM %s WHERE %s = \"%s\";", TABLE_NAME, COLUMN_URL, key);
            if (SHOW_SQL) {
                System.out.println("select sql:" + sql);
            }
            rs = stmt.executeQuery(sql);
            //noinspection Duplicates
            while (rs.next()) {
                String url = rs.getString("URL");
                String destPath = rs.getString("DEST_PATH");
                UriBean bean = new UriBean(url, destPath);
                beanArrayList.add(bean);
            }
            if (beanArrayList.size() == 1) {
                return beanArrayList.stream().findFirst().get().getDestPah();
            } else {
                return null;
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static int insert(String url, String destPath) {
        return insert(new UriBean(url, destPath));
    }

    public static int insert(UriBean bean) {
        String key = encode(bean.getUrl());
        String value = encode(bean.getDestPah());
        String sql = String.format("INSERT INTO %s (%s,%s) VALUES(\"%s\",\"%s\");", TABLE_NAME, COLUMN_URL, COLUMN_DEST_PATH,
                key, value);
        if (SHOW_SQL) {
            System.out.println("insert sqlOrigin=" + String.format("INSERT INTO %s (%s,%s) VALUES(\"%s\",\"%s\");", TABLE_NAME, COLUMN_URL, COLUMN_DEST_PATH,
                    decode(key), decode(value)));
            System.out.println("insert sql=" + sql);
        }
        String select = select(key);
        if (select != null) {
            if (SHOW_WARING) {
                System.err.println("this key has already insert: " + bean);
            }
            return -1;
        }
        Connection c = null;
        Statement stmt = null;
        int ret = -1;
        try {
            c = openDbConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ret = stmt.executeUpdate(sql);
            c.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static int update(String key, String value) {

        Connection c = null;
        Statement stmt = null;
        int ret = -1;
        try {
            c = openDbConnection();
            c.setAutoCommit(false);

            stmt = c.createStatement();

            String sql = String.format("UPDATE %s set %s=%s where %s=%s;",
                    TABLE_NAME, COLUMN_DEST_PATH, value, COLUMN_URL, key);

            if (SHOW_SQL) {
                System.out.println("update sql==" + sql);
            }
            ret = stmt.executeUpdate(sql);
            c.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static int delete(String key) {
        Connection c = null;
        Statement stmt = null;
        int ret = -1;
        try {
            c = openDbConnection();
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = String.format("DELETE from %s where %s=\"%s\";", TABLE_NAME, COLUMN_URL, key);

            if (SHOW_SQL) {
                System.out.println("delete sql=" + sql);
            }
            ret = stmt.executeUpdate(sql);
            c.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }


    /**
     * uri 必须转码 https:// 这个中间的 : 会导致sql 语法错误。
     *
     * @param src uri
     * @return normal str
     */
    private static String encode(String src) {
        try {
            return Base64.getUrlEncoder().encodeToString(src.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String decode(String encoded) {
        try {
            return new String(Base64.getUrlDecoder().decode(encoded), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
