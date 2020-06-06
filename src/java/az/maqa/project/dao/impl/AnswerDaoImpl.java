package az.maqa.project.dao.impl;

import az.maqa.project.dao.helper.DbHelper;
import az.maqa.project.dao.inter.AnswerDao;
import az.maqa.project.model.Answer;
import az.maqa.project.model.AnswerStatus;
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

public class AnswerDaoImpl implements AnswerDao {
    public List<Answer> getAnswerList() throws Exception {
        List<Answer> answerList = new ArrayList();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{? = call ANSWER_PACK.GET_ANSWER_LIST}";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
               // cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(1);

                while (rs.next()) {
                    Answer answer = new Answer();
                    ClassRoom classRoom = new ClassRoom();
                    Lesson lesson = new Lesson();
                    Questions questions = new Questions();
                    Topic topic = new Topic();
                    AnswerStatus answerStatus = new AnswerStatus();
                    answer.setId(rs.getLong("ID"));
                    answer.setAnswer(rs.getString("ANSWER"));
                    answerStatus.setId(rs.getLong("answerstatus_id"));
                    answerStatus.setStatusName(rs.getString("STATUS_NAME"));
                    questions.setId(rs.getLong("question_id"));
                    questions.setQuestions(rs.getString("QUESTION"));
                    topic.setId(rs.getLong("topic_id"));
                    topic.setTopicName(rs.getString("topic_name"));
                    classRoom.setId(rs.getLong("class_id"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));
                    lesson.setId(rs.getLong("lesson_id"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    topic.setClassRoom(classRoom);
                    topic.setLesson(lesson);
                    questions.setTopic(topic);
                    answer.setAnswerStatus(answerStatus);
                    answer.setQuestions(questions);
                    answerList.add(answer);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, rs);
        }

        return answerList;
    }

    public boolean add(Answer answer) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call ANSWER_PACK.ADD_ANSWER(?,?,?)}";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setString(1, answer.getAnswer());
                cs.setLong(2, answer.getQuestions().getId());
                cs.setLong(3, answer.getAnswerStatus().getId());
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

    public Answer getAnswerById(Long id) throws Exception {
        Answer answer = new Answer();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{? = call ANSWER_PACK.GET_ANSWER_BY_ID(?)}";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                //cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.setLong(2, id);
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    ClassRoom classRoom = new ClassRoom();
                    Lesson lesson = new Lesson();
                    Questions questions = new Questions();
                    Topic topic = new Topic();
                    AnswerStatus answerStatus = new AnswerStatus();
                    answer.setId(rs.getLong("ID"));
                    answer.setAnswer(rs.getString("ANSWER"));
                    answerStatus.setId(rs.getLong("answerstatus_id"));
                    answerStatus.setStatusName(rs.getString("STATUS_NAME"));
                    questions.setId(rs.getLong("question_id"));
                    questions.setQuestions(rs.getString("QUESTION"));
                    topic.setId(rs.getLong("topic_id"));
                    topic.setTopicName(rs.getString("topic_name"));
                    classRoom.setId(rs.getLong("class_id"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));
                    lesson.setId(rs.getLong("lesson_id"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    topic.setClassRoom(classRoom);
                    topic.setLesson(lesson);
                    questions.setTopic(topic);
                    answer.setAnswerStatus(answerStatus);
                    answer.setQuestions(questions);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, rs);
        }

        return answer;
    }

    public boolean update(Answer answer, Long id) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call ANSWER_PACK.UPDATE_ANSWER(?,?,?,?)}";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setString(1, answer.getAnswer());
                cs.setLong(2, answer.getQuestions().getId());
                cs.setLong(3, answer.getAnswerStatus().getId());
                cs.setLong(4, id);
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
        String sql = "{call ANSWER_PACK.DELETE_ANSWER(?,?)}";

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

    public List<Answer> search(String keyword) throws Exception {
        List<Answer> answerList = new ArrayList();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{? = call ANSWER_PACK.SEARCH_ANSWER(?,?,?,?,?,?)}";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
               // cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.setString(2, "%" + keyword + "%");
                cs.setString(3, "%" + keyword + "%");
                cs.setString(4, "%" + keyword + "%");
                cs.setString(5, "%" + keyword + "%");
                cs.setString(6, "%" + keyword + "%");
                cs.setString(7, "%" + keyword + "%");
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    Answer answer = new Answer();
                    ClassRoom classRoom = new ClassRoom();
                    Lesson lesson = new Lesson();
                    Questions questions = new Questions();
                    Topic topic = new Topic();
                    AnswerStatus answerStatus = new AnswerStatus();
                    answer.setId(rs.getLong("ID"));
                    answer.setAnswer(rs.getString("ANSWER"));
                    answerStatus.setId(rs.getLong("answerstatus_id"));
                    answerStatus.setStatusName(rs.getString("STATUS_NAME"));
                    questions.setId(rs.getLong("question_id"));
                    questions.setQuestions(rs.getString("QUESTION"));
                    topic.setId(rs.getLong("topic_id"));
                    topic.setTopicName(rs.getString("topic_name"));
                    classRoom.setId(rs.getLong("class_id"));
                    classRoom.setClassNumber(rs.getString("CLASS_NUMBER"));
                    lesson.setId(rs.getLong("lesson_id"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    topic.setClassRoom(classRoom);
                    topic.setLesson(lesson);
                    questions.setTopic(topic);
                    answer.setAnswerStatus(answerStatus);
                    answer.setQuestions(questions);
                    answerList.add(answer);
                }
            } else {
                System.out.println("Connection is failure !!!");
            }
        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            JDBCUtility.closeJDBC(c, cs, rs);
        }

        return answerList;
    }
}
