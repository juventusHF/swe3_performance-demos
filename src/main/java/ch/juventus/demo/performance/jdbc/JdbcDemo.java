package ch.juventus.demo.performance.jdbc;

import java.sql.*;


public class JdbcDemo {

    public Connection createConnection(String dbUrl, String dbUser, String dbPass) {
        try {
            return DriverManager.getConnection(dbUrl, dbUser, dbPass);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }


    public void executeSillyStatement(Connection conn) {

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT now()";
            ResultSet rs = stmt.executeQuery(sql);
            // do nothing with rs
            rs.close();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e2) {
                System.err.println(e2.getMessage());
            }
        }
    }
}



