package ch.juventus.demo.performance.jdbc;

import org.junit.Test;

import java.sql.Connection;

public class JdbcDemoTest {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/jpademo?useSSL=false&serverTimezone=UTC";

    private static final String USER = "jpauser";
    private static final String PASS = "password";

    private static final int N = 10000;

    @Test
    public void connectWithNewConnection() {
        JdbcDemo demo = new JdbcDemo();
        int n = N;
        long t1 = System.nanoTime();
        for (int i=0; i<n; i++) {
            Connection con = null;
            try {
                con = demo.createConnection(DB_URL, USER, PASS);
                demo.executeSillyStatement(con);
            } finally {
                try {
                    con.close();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        long time = System.nanoTime() - t1;
        System.out.printf("Recreating connection. Ran %d queries with %d ns per query.\n", n, time/n);
    }

    @Test
    public void connectWithReusedConnection() {
        JdbcDemo demo = new JdbcDemo();
        int n = N;
        long t1 = System.nanoTime();
        Connection con = null;
        try {
            con = demo.createConnection(DB_URL, USER, PASS);
            for (int i=0; i<n; i++) {
                demo.executeSillyStatement(con);
            }
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        long time = System.nanoTime() - t1;
        System.out.printf("Reusing connection. Ran %d queries with %d ns per query.\n", n, time/n);
    }

}