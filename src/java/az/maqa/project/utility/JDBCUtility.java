package az.maqa.project.utility;

import java.sql.*;

public class JDBCUtility {
    public static void closeJDBC(Connection c, PreparedStatement ps, ResultSet rs) throws Exception {

        if (c != null) {
            c.close();
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }


    public static void closeJDBC(Connection c, CallableStatement cs, ResultSet rs) throws Exception {

        if (c != null) {
            c.close();
            if (cs != null) {
                cs.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

}

