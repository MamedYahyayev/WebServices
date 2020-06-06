package az.maqa.project.dao.impl;

import az.maqa.project.dao.helper.DbHelper;
import az.maqa.project.dao.inter.LoginDao;
import az.maqa.project.model.Role;
import az.maqa.project.model.Student;
import az.maqa.project.utility.JDBCUtility;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoginDaoImpl implements LoginDao {

    @Override
    public List<Student> getStudentList() throws Exception {
        List<Student> studentList = new ArrayList<>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{ ? = call LOGIN_PACK.GET_LOGIN_LIST}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
           //     cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getLong("ID"));
                    student.setRownum(rs.getLong("r"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    student.setUsername(rs.getString("USERNAME"));
                    student.setPassword(rs.getString("PASSWORD"));

                    Role role = new Role();
                    role.setId(rs.getLong("role_id"));
                    role.setRoleName(rs.getString("ROLE_NAME"));

                    student.setRole(role);

                    studentList.add(student);
                }
            } else {
                System.out.println("Connection is failure!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, rs);
        }
        return studentList;
    }

    @Override
    public boolean add(Student student) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call LOGIN_PACK.ADD_LOGIN(?,?,?,?,?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setString(1, student.getName());
                cs.setString(2, student.getSurname());
                cs.setString(3, student.getUsername());
                cs.setString(4, student.getPassword());
                cs.setLong(5, student.getRole().getId());
                cs.execute();
                result = true;
                c.commit();
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, null);
        }
        return result;
    }

    @Override
    public Student getStudentById(Long id) throws Exception {
        Student student = new Student();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{ ? = call LOGIN_PACK.GET_LOGIN_BY_ID(?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
               // cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.setLong(2, id);
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {

                    student.setId(rs.getLong("ID"));
                    student.setRownum(rs.getLong("r"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    student.setUsername(rs.getString("USERNAME"));
                    student.setPassword(rs.getString("PASSWORD"));

                    Role role = new Role();
                    role.setId(rs.getLong("role_id"));
                    role.setRoleName(rs.getString("ROLE_NAME"));

                    student.setRole(role);
                }
            } else {
                System.out.println("Connection is failure!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, rs);
        }
        return student;
    }

    @Override
    public boolean update(Student student, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{ call LOGIN_PACK.UPDATE_LOGIN(?,?,?,?,?,?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setString(1, student.getName());
                cs.setString(2, student.getSurname());
                cs.setString(3, student.getUsername());
                cs.setString(4, student.getPassword());
                cs.setLong(5, student.getRole().getId());
                cs.setLong(6, id);
                cs.execute();
                result = true;
                c.commit();
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, null);
        }
        return result;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call LOGIN_PACK.DELETE_LOGIN(0,?)}";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setLong(1, id);
                cs.execute();
                result = true;
                c.commit();
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, null);
        }
        return result;
    }

    @Override
    public List<Student> search(String keyword) throws Exception {
        List<Student> studentList = new ArrayList<>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{? = call LOGIN_PACK.SEARCH_LOGIN(?,?,?,?,?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
              //  cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.setString(2, "%" + keyword + "%");
                cs.setString(3, "%" + keyword + "%");
                cs.setString(4, "%" + keyword + "%");
                cs.setString(5, "%" + keyword + "%");
                cs.setString(6, "%" + keyword + "%");
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getLong("ID"));
                    student.setRownum(rs.getLong("r"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    student.setUsername(rs.getString("USERNAME"));
                    student.setPassword(rs.getString("PASSWORD"));

                    Role role = new Role();
                    role.setId(rs.getLong("role_id"));
                    role.setRoleName(rs.getString("ROLE_NAME"));

                    student.setRole(role);

                    studentList.add(student);
                }
            } else {
                System.out.println("Connection is failure!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, rs);
        }
        return studentList;
    }

    @Override
    public List<Student> advancedSearch(Long id) throws Exception {
        List<Student> studentList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM  r,\n" +
                "       STUDENT.ID,\n" +
                "       STUDENT.NAME,\n" +
                "       STUDENT.SURNAME,\n" +
                "       STUDENT.USERNAME,\n" +
                "       STUDENT.PASSWORD,\n" +
                "       ROLE.ROLE_NAME,\n" +
                "       ROLE.ID role_id\n" +
                "FROM STUDENT\n" +
                "         INNER JOIN ROLE ON STUDENT.ROLE_ID = ROLE.ID\n" +
                "WHERE ((STUDENT.ACTIVE = 1) AND (role_id = ?))";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getLong("ID"));
                    student.setRownum(rs.getLong("r"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    student.setUsername(rs.getString("USERNAME"));
                    student.setPassword(rs.getString("PASSWORD"));

                    Role role = new Role();
                    role.setId(rs.getLong("role_id"));
                    role.setRoleName(rs.getString("ROLE_NAME"));

                    student.setRole(role);

                    studentList.add(student);
                }
            } else {
                System.out.println("Connection is failure!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, ps, rs);
        }
        return studentList;
    }

    @Override
    public Student checkLogin(String username, String password) throws Exception {
        Student student = new Student();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{? = call LOGIN_PACK.CHECK_LOGIN(?,?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
            //    cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.setString(2, username);
                cs.setString(3, password);
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {

                    student.setId(rs.getLong("ID"));
                    student.setRownum(rs.getLong("r"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    student.setUsername(rs.getString("USERNAME"));
                    student.setPassword(rs.getString("PASSWORD"));

                    Role role = new Role();
                    role.setId(rs.getLong("role_id"));
                    role.setRoleName(rs.getString("ROLE_NAME"));

                    student.setRole(role);
                }
            } else {
                System.out.println("Connection is failure!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, rs);
        }
        return student;
    }

    @Override
    public Student checkEmail(String email) throws Exception {
        Student student = new Student();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{ ? = call LOGIN_PACK.CHECK_EMAIL(?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
             //   cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.setString(2, email);
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                if (rs.next()) {

                    student.setId(rs.getLong("ID"));
                    student.setRownum(rs.getLong("r"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    student.setUsername(rs.getString("USERNAME"));
                    student.setPassword(rs.getString("PASSWORD"));

                    Role role = new Role();
                    role.setId(rs.getLong("role_id"));
                    role.setRoleName(rs.getString("ROLE_NAME"));

                    student.setRole(role);
                } else {
                    student = null;
                }
            } else {
                System.out.println("Connection is failure!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, rs);
        }
        return student;
    }

    @Override
    public boolean updateTokenById(Student loginUser) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call LOGIN_PACK.UPDATE_TOKEN_BY_ID(?,?)}";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setString(1, loginUser.getToken());
                cs.setLong(2, loginUser.getId());
                cs.execute();
                result = true;
                c.commit();
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, null);
        }
        return result;
    }

    @Override
    public Student checkToken(String token) throws Exception {
        Student student = new Student();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{ ? = call LOGIN_PACK.CHECK_TOKEN(?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
            //    cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.setString(2, token);
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                if (rs.next()) {
                    student.setId(rs.getLong("ID"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    student.setUsername(rs.getString("USERNAME"));
                    student.setPassword(rs.getString("PASSWORD"));

                    Role role = new Role();
                    role.setId(rs.getLong("role_id"));
                    role.setRoleName(rs.getString("ROLE_NAME"));

                    student.setRole(role);
                } else {
                    student = null;
                }
            } else {
                System.out.println("Connection is failure!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, rs);
        }
        return student;
    }

    @Override
    public boolean changePassword(String password, Long userId) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call LOGIN_PACK.CHANGE_PASSWORD(?,?)}";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setString(1, password);
                cs.setLong(2, userId);
                cs.execute();
                result = true;
                c.commit();
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, null);
        }
        return result;
    }

    @Override
    public boolean createAccount(Student register) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "{call LOGIN_PACK.CREATE_ACCOUNT(?,?,?,?,2)}";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, register.getName());
                ps.setString(2, register.getSurname());
                ps.setString(3, register.getUsername());
                ps.setString(4, register.getPassword());
                ps.execute();
                result = true;
                c.commit();
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, ps, null);
        }
        return result;
    }
}
