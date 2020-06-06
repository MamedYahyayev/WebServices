package az.maqa.project.dao.impl;

import az.maqa.project.dao.helper.DbHelper;
import az.maqa.project.dao.inter.ClassDao;
import az.maqa.project.model.ClassRoom;
import az.maqa.project.utility.JDBCUtility;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassDaoImpl implements ClassDao {

    @Override
    public List<ClassRoom> getClassList() throws Exception {
        List<ClassRoom> classRoomsList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM qanda.class where active = 1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
//                cs.registerOutParameter(1, OracleTypes.CURSOR);
//                cs.execute();
//                rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    ClassRoom classRoom = new ClassRoom();
                    classRoom.setId(rs.getLong("ID"));

                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));

                    classRoomsList.add(classRoom);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, ps, rs);
        }
        return classRoomsList;
    }

    @Override
    public boolean add(ClassRoom classRoom) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "insert into qanda.class(class_number) "
                + " values(?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, classRoom.getClassNumber());
                ps.execute();
                result = true;
                // c.commit();
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

    @Override
    public ClassRoom getClassRoomById(Long id) throws Exception {
        ClassRoom classRoom = new ClassRoom();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from qanda.class where qanda.class.id = ? and qanda.class.active = 1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    classRoom.setId(rs.getLong("ID"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));
                } else {
                    classRoom = null;
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, ps, rs);
        }
        return classRoom;
    }

    @Override
    public boolean update(ClassRoom classRoom, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update qanda.class set class_number = ? where qanda.class.id = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, classRoom.getClassNumber());
                ps.setLong(2, id);
                ps.execute();
                result = true;
                // c.commit();
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

    @Override
    public boolean delete(Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update qanda.class set qanda.class.active = 0 where qanda.class.id = ?";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareCall(sql);
                ps.setLong(1, id);
                ps.execute();
                result = true;
                //   c.commit();
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

    @Override
    public List<ClassRoom> search(String keyword) throws Exception {
        List<ClassRoom> classRoomsList = new ArrayList<>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{? = call CLASS_PACK.SEARCH_CLASSROOM(?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                // cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.setString(2, "%" + keyword + "%");
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    ClassRoom classRoom = new ClassRoom();
                    classRoom.setId(rs.getLong("ID"));
                    classRoom.setRownum(rs.getLong("r"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));

                    classRoomsList.add(classRoom);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, rs);
        }
        return classRoomsList;
    }
}
