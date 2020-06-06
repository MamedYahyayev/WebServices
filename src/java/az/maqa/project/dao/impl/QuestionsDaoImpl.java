package az.maqa.project.dao.impl;

import az.maqa.project.dao.helper.DbHelper;
import az.maqa.project.dao.inter.QuestionsDao;
import az.maqa.project.model.ClassRoom;
import az.maqa.project.model.Lesson;
import az.maqa.project.model.Questions;
import az.maqa.project.model.Topic;
import az.maqa.project.utility.JDBCUtility;




import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDaoImpl implements QuestionsDao {
    public List<Questions> getQuestionsList() throws Exception {
        List<Questions> questionsList = new ArrayList();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{? = call QUESTION_PACK.GET_QUESTION_LIST}";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
              //  cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    Questions questions = new Questions();
                    questions.setId(rs.getLong("ID"));
                    questions.setRownum(rs.getLong("r"));
                    questions.setQuestions(rs.getString("QUESTION"));
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("lesson_id"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    ClassRoom classRoom = new ClassRoom();
                    classRoom.setId(rs.getLong("class_id"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));
                    Topic topic = new Topic();
                    topic.setTopicName(rs.getString("NAME"));
                    topic.setLesson(lesson);
                    topic.setClassRoom(classRoom);
                    questions.setTopic(topic);
                    questionsList.add(questions);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception var13) {
            var13.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, rs);
        }

        return questionsList;
    }

    public boolean add(Questions questions) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{ call QUESTION_PACK.ADD_QUESTION(?,?)}";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setString(1, questions.getQuestions());
                cs.setLong(2, questions.getTopic().getId());
                cs.execute();
                result = true;
                c.commit();
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, (ResultSet) null);
        }

        return result;
    }

    public List<Questions> getQuestionByTopicId(Long id) throws Exception {
        List<Questions> questionsList = new ArrayList();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{? = call QUESTION_PACK.GET_QUESTION_LIST_BY_TOPIC_ID(?)}";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
               // cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.setLong(2, id);
                cs.execute();
                rs = (ResultSet) cs.getObject(1);

                while (rs.next()) {
                    Questions questions = new Questions();
                    questions.setId(rs.getLong("ID"));
                    questions.setRownum(rs.getLong("r"));
                    questions.setQuestions(rs.getString("QUESTION"));
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("lesson_id"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    ClassRoom classRoom = new ClassRoom();
                    classRoom.setId(rs.getLong("class_id"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));
                    Topic topic = new Topic();
                    topic.setTopicName(rs.getString("NAME"));
                    topic.setLesson(lesson);
                    topic.setClassRoom(classRoom);
                    questions.setTopic(topic);
                    questionsList.add(questions);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, rs);
        }

        return questionsList;
    }

    public Questions getQuestionById(Long id) throws Exception {
        Questions questions = new Questions();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{? = call QUESTION_PACK.GET_QUESTION_BY_ID(?)}";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
               // cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.setLong(2, id);
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    questions.setId(rs.getLong("ID"));
                    questions.setRownum(rs.getLong("r"));
                    questions.setQuestions(rs.getString("QUESTION"));
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("lesson_id"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    ClassRoom classRoom = new ClassRoom();
                    classRoom.setId(rs.getLong("class_id"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));
                    Topic topic = new Topic();
                    topic.setId(rs.getLong("topic_id"));
                    topic.setTopicName(rs.getString("NAME"));
                    topic.setLesson(lesson);
                    topic.setClassRoom(classRoom);
                    questions.setTopic(topic);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception var13) {
            var13.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, rs);
        }

        return questions;
    }

    public boolean update(Questions question, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call QUESTION_PACK.UPDATE_QUESTION(?,?,?)}";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setString(1, question.getQuestions());
                cs.setLong(2, question.getTopic().getId());
                cs.setLong(3, id);
                cs.execute();
                result = true;
                c.commit();
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, (ResultSet) null);
        }

        return result;
    }

    public boolean delete(Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call QUESTION_PACK.DELETE_QUESTION(?,?)}";

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
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, (ResultSet) null);
        }

        return result;
    }

    public List<Questions> search(String keyword) throws Exception {
        List<Questions> questionsList = new ArrayList();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{? = call QUESTION_PACK.SEARCH_QUESTION(?,?,?,?)}";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
           //     cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.setString(2, "%" + keyword + "%");
                cs.setString(3, "%" + keyword + "%");
                cs.setString(4, "%" + keyword + "%");
                cs.setString(5, "%" + keyword + "%");
                cs.execute();
                rs = (ResultSet) cs.getObject(1);

                while (rs.next()) {
                    Questions questions = new Questions();
                    questions.setId(rs.getLong("ID"));
                    questions.setRownum(rs.getLong("r"));
                    questions.setQuestions(rs.getString("QUESTION"));
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("lesson_id"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    ClassRoom classRoom = new ClassRoom();
                    classRoom.setId(rs.getLong("class_id"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));
                    Topic topic = new Topic();
                    topic.setTopicName(rs.getString("NAME"));
                    topic.setLesson(lesson);
                    topic.setClassRoom(classRoom);
                    questions.setTopic(topic);
                    questionsList.add(questions);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, rs);
        }

        return questionsList;
    }
}
