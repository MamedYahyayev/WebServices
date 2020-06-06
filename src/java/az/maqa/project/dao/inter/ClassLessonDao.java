package az.maqa.project.dao.inter;

import az.maqa.project.model.AdvancedSearch;
import az.maqa.project.model.ClassLesson;

import java.util.List;

public interface ClassLessonDao {
    List<ClassLesson> getClassLessonList() throws Exception;

    List<ClassLesson> getClassLessonListByLessonIdOrClassId(AdvancedSearch advancedSearch) throws Exception;

    List<ClassLesson> getClassLessonListByClassId(Long classId) throws Exception;

    List<ClassLesson> getClassLessonListByLessonId(Long lessonId) throws Exception;


}
