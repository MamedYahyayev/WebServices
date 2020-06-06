package az.maqa.project.dao.impl;

import az.maqa.project.dao.helper.DbHelper;
import az.maqa.project.dao.inter.TopicDao;
import az.maqa.project.model.*;
import az.maqa.project.utility.JDBCUtility;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TopicDaoImpl implements TopicDao {
    @Override
    public List<Topic> getTopicList() throws Exception {
        List<Topic> topicList = new ArrayList<>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{? = call TOPIC_PACK.GET_TOPIC_LIST}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
            //    cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    Topic topic = new Topic();
                    ClassRoom classRoom = new ClassRoom();
                    Lesson lesson = new Lesson();

                    topic.setId(rs.getLong("ID"));
                    topic.setTopicName(rs.getString("NAME"));

                    classRoom.setId(rs.getLong("class_id"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));

                    lesson.setId(rs.getLong("lesson_id"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));

                    topic.setLesson(lesson);
                    topic.setClassRoom(classRoom);

                    topicList.add(topic);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, rs);
        }
        return topicList;
    }

    @Override
    public boolean add(Topic topic) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call TOPIC_PACK.ADD_TOPIC(?,?,?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setString(1, topic.getTopicName());
                cs.setLong(2, topic.getClassRoom().getId());
                cs.setLong(3, topic.getLesson().getId());
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
    public Topic getTopicById(Long id) throws Exception {
        Topic topic = new Topic();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{? = call TOPIC_PACK.GET_TOPIC_BY_ID(?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
             //   cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.setLong(2, id);
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    ClassRoom classRoom = new ClassRoom();
                    Lesson lesson = new Lesson();

                    topic.setId(rs.getLong("ID"));
                    topic.setTopicName(rs.getString("NAME"));

                    classRoom.setId(rs.getLong("class_id"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));

                    lesson.setId(rs.getLong("lesson_id"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));

                    topic.setLesson(lesson);
                    topic.setClassRoom(classRoom);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, rs);
        }
        return topic;
    }

    @Override
    public boolean update(Topic topic, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call TOPIC_PACK.UPDATE_TOPIC(?,?,?,?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setString(1, topic.getTopicName());
                cs.setLong(2, topic.getClassRoom().getId());
                cs.setLong(3, topic.getLesson().getId());
                cs.setLong(4, id);
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
        String sql = "{call TOPIC_PACK.DELETE_TOPIC(?,?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setInt(1, 0);
                cs.setLong(2, id);
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
    public List<Topic> search(String keyword) throws Exception {
        List<Topic> topicList = new ArrayList<>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{? = call TOPIC_PACK.SEARCH_TOPIC(?,?,?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
            //    cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.setString(2, "%" + keyword + "%");
                cs.setString(3, "%" + keyword + "%");
                cs.setString(4, "%" + keyword + "%");
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    Topic topic = new Topic();
                    ClassRoom classRoom = new ClassRoom();
                    Lesson lesson = new Lesson();

                    topic.setId(rs.getLong("ID"));
                    topic.setTopicName(rs.getString("NAME"));

                    classRoom.setId(rs.getLong("class_id"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));

                    lesson.setId(rs.getLong("lesson_id"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));

                    topic.setLesson(lesson);
                    topic.setClassRoom(classRoom);

                    topicList.add(topic);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, rs);
        }
        return topicList;
    }


}
