package az.maqa.project.dao.impl;

import az.maqa.project.dao.helper.DbHelper;
import az.maqa.project.dao.inter.RoleDao;
import az.maqa.project.model.Role;
import az.maqa.project.utility.JDBCUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {
    @Override
    public List<Role> getRoleList() throws Exception {
        List<Role> roleList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ID,ROLE_NAME FROM  ROLE WHERE  ACTIVE = 1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Role role = new Role();
                    role.setId(rs.getLong("ID"));
                    role.setRoleName(rs.getString("ROLE_NAME"));

                    roleList.add(role);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, ps, rs);
        }
        return roleList;
    }
}
