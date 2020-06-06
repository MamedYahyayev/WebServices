package az.maqa.project.dao.impl;

import az.maqa.project.dao.helper.DbHelper;
import az.maqa.project.dao.inter.LessonDao;
import az.maqa.project.model.Lesson;
import az.maqa.project.utility.JDBCUtility;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LessonDaoImpl implements LessonDao {

    public List<Lesson> getLessonList() throws Exception {
        List<Lesson> lessonList = new ArrayList();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from qanda.lesson where qanda.lesson.active = 1";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("ID"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    lessonList.add(lesson);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, ps, rs);
        }

        return lessonList;
    }

    public boolean add(Lesson lesson) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "insert into qanda.lesson(lesson_name) "
                + " values(?)";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, lesson.getLessonName());
                ps.execute();
                result = true;
                // c.commit();
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, ps, (ResultSet) null);
        }

        return result;
    }

    public Lesson getLessonById(Long id) throws Exception {
        Lesson lesson = new Lesson();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from qanda.lesson where qanda.lesson.active = 1 and qanda.lesson.id = ?";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    lesson.setId(rs.getLong("ID"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                } else {
                    lesson = null;
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, ps, rs);
        }

        return lesson;
    }

    public boolean update(Lesson lesson, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update qanda.lesson set qanda.lesson.lesson_name = ? where qanda.lesson.id = ?";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareCall(sql);
                ps.setString(1, lesson.getLessonName());
                ps.setLong(2, id);
                ps.execute();
                result = true;
                //  c.commit();
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, ps, (ResultSet) null);
        }

        return result;
    }

    public boolean delete(Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update qanda.lesson set qanda.lesson.active = 0 where qanda.lesson.id = ?";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                ps.execute();
                result = true;
                //  c.commit();
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, ps, (ResultSet) null);
        }

        return result;
    }

    public List<Lesson> search(String keyword) throws Exception {
        List<Lesson> lessonList = new ArrayList();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{? = call LESSON_PACK.SEARCH_LESSON(?)}";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                //   cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.setString(2, "%" + keyword + "%");
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("ID"));
                    lesson.setRownum(rs.getLong("r"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    lessonList.add(lesson);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, rs);
        }

        return lessonList;
    }
}
