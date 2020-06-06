package az.maqa.project.service;

import az.maqa.project.request.ReqClass;
import az.maqa.project.request.ReqLesson;
import az.maqa.project.request.ReqInformation;
import az.maqa.project.response.RespClass;
import az.maqa.project.response.RespClassList;
import az.maqa.project.response.RespInformation;
import az.maqa.project.response.RespInformationList;
import az.maqa.project.response.RespLesson;
import az.maqa.project.response.RespLessonList;
import az.maqa.project.response.RespStatus;
import java.util.List;

public interface GeneralService {

    RespClassList getClassList();

    RespStatus addClass(ReqClass reqClass);

    RespStatus updateClass(ReqClass reqClass);

    RespClass getClassById(Long classId);

    RespStatus deleteClass(Long classId);

    RespLessonList getLessonList();

    RespStatus addLesson(ReqLesson reqLesson);

    RespLesson getLessonById(Long lessonId);
    
    RespStatus updateLesson(ReqLesson reqLesson);
    
    RespStatus deleteLesson(Long lessonId);
    
    RespInformationList getInformationList();
    
    RespStatus addInformation(ReqInformation reqInformation);

    RespInformation getInformationById(Long informationId);

}
