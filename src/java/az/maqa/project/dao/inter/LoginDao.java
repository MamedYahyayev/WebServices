package az.maqa.project.dao.inter;

import az.maqa.project.model.Student;

import java.util.List;
import java.lang.Exception;

public interface LoginDao {

    List<Student> getStudentList() throws Exception;

    boolean add(Student student) throws Exception;

    Student getStudentById(Long id) throws Exception;

    boolean update(Student student, Long id) throws Exception;

    boolean delete(Long id) throws Exception;

    List<Student> search(String keyword) throws Exception;

    List<Student> advancedSearch(Long id) throws Exception;

    Student checkLogin(String username, String password) throws Exception;

    Student checkEmail(String email) throws Exception;

    boolean updateTokenById(Student loginUser) throws Exception;

    Student checkToken(String token) throws Exception;

    boolean changePassword(String password, Long userId) throws Exception;

    boolean createAccount(Student register) throws Exception;
}
