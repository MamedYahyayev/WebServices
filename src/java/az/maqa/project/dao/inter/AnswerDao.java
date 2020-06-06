package az.maqa.project.dao.inter;

import az.maqa.project.model.Answer;

import java.util.List;

public interface AnswerDao {
    List<Answer> getAnswerList() throws Exception;

    boolean add(Answer answer) throws Exception;

    Answer getAnswerById(Long id) throws Exception;

    boolean update(Answer answer, Long id) throws Exception;

    boolean delete(Long id) throws Exception;

   List<Answer> search(String keyword) throws Exception;
}
