package az.maqa.project.service;

import az.maqa.project.dao.impl.ClassDaoImpl;
import az.maqa.project.dao.impl.InformationsDaoImpl;
import az.maqa.project.dao.impl.LessonDaoImpl;
import az.maqa.project.dao.inter.ClassDao;
import az.maqa.project.dao.inter.InformationsDao;
import az.maqa.project.dao.inter.LessonDao;
import az.maqa.project.model.ClassRoom;
import az.maqa.project.model.Informations;
import az.maqa.project.model.Lesson;
import az.maqa.project.request.ReqClass;
import az.maqa.project.request.ReqInformation;
import az.maqa.project.request.ReqLesson;
import az.maqa.project.response.RespClass;
import az.maqa.project.response.RespClassList;
import az.maqa.project.response.RespInformation;
import az.maqa.project.response.RespInformationList;
import az.maqa.project.response.RespLesson;
import az.maqa.project.response.RespLessonList;
import az.maqa.project.response.RespStatus;
import az.maqa.project.utility.ExceptionConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneralServiceImpl implements GeneralService {

    ClassDao classDao = new ClassDaoImpl();
    LessonDao lessonDao = new LessonDaoImpl();
    InformationsDao infoDao = new InformationsDaoImpl();

    @Override
    public RespClassList getClassList() {
        RespClassList response = new RespClassList();
        List<RespClass> respClassList = new ArrayList<>();
        try {
            List<ClassRoom> classList = classDao.getClassList();
            if (classList.isEmpty()) {
                response.setStatus(new RespStatus(ExceptionConstants.NOT_FOUND, "Data Not Found"));
                return response;
            }
            for (ClassRoom classRoom : classList) {
                RespClass respClass = new RespClass();
                respClass.setId(classRoom.getId());
                respClass.setClassNumber(classRoom.getClassNumber());

                respClassList.add(respClass);
            }
            response.setRespClassList(respClassList);
            response.setStatus(RespStatus.getSuccessMessage());

        } catch (Exception e) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "internal exception"));
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public RespStatus addClass(ReqClass reqClass) {
        RespStatus response = null;
        try {
            if (reqClass.getClassNumber() == null) {
                response = new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "invalid");
                return response;
            }
            ClassRoom classRoom = new ClassRoom();
            classRoom.setClassNumber(reqClass.getClassNumber());
            boolean isAdded = classDao.add(classRoom);
            if (!isAdded) {
                response = new RespStatus(ExceptionConstants.ADD_FAIL, "Add Class Fail");
                return response;
            }
            response = RespStatus.getSuccessMessage();
        } catch (Exception e) {
            response = new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception");
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public RespStatus updateClass(ReqClass reqClass) {
        RespStatus response = null;
        try {
            if (reqClass.getClassNumber() == null || reqClass.getClassId() == null) {
                response = new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "invalid request data");
                return response;
            }
            ClassRoom classRoom = new ClassRoom();
            classRoom.setId(reqClass.getClassId());
            classRoom.setClassNumber(reqClass.getClassNumber());
            boolean isUpdated = classDao.update(classRoom, reqClass.getClassId());
            if (!isUpdated) {
                response = new RespStatus(ExceptionConstants.UPDATE_FAIL, "update fail");
                return response;
            }
            response = RespStatus.getSuccessMessage();

        } catch (Exception ex) {
            response = new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception");
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public RespClass getClassById(Long classId) {
        RespClass response = new RespClass();
        try {
            if (classId == null) {
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "invalid id"));
                return response;
            }
            ClassRoom classRoom = classDao.getClassRoomById(classId);
            if (classRoom == null) {
                response.setStatus(new RespStatus(ExceptionConstants.NOT_FOUND, "Class Not Found"));
                return response;
            }
            response.setId(classRoom.getId());
            response.setClassNumber(classRoom.getClassNumber());
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "internal exception"));
            Logger.getLogger(GeneralServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    @Override
    public RespStatus deleteClass(Long classId) {
        RespStatus response = null;
        try {
            if (classId == null) {
                response = new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "invalid id");
                return response;
            }

            ClassRoom classRoom = new ClassRoom();
            classRoom.setId(classId);

            boolean isDeleted = classDao.delete(classId);
            if (!isDeleted) {
                response = new RespStatus(ExceptionConstants.DELETE_FAIL, "delete fail");
                return response;
            }
            response = RespStatus.getSuccessMessage();

        } catch (Exception ex) {
            response = new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "internal exception");
            Logger.getLogger(GeneralServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;

    }

    @Override
    public RespLessonList getLessonList() {
        RespLessonList response = new RespLessonList();
        List<RespLesson> respLessonList = new ArrayList<>();
        try {
            List<Lesson> lessonList = lessonDao.getLessonList();
            if (lessonList.isEmpty()) {
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "invalid request data"));
                return response;
            }
            for (Lesson lesson : lessonList) {
                RespLesson respLesson = new RespLesson();
                respLesson.setId(lesson.getId());
                respLesson.setLessonName(lesson.getLessonName());

                respLessonList.add(respLesson);

            }
            response.setRespLessonList(respLessonList);
            response.setStatus(RespStatus.getSuccessMessage());

        } catch (Exception e) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "internal exception"));
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public RespStatus addLesson(ReqLesson reqLesson) {
        RespStatus response = null;
        try {
            if (reqLesson.getLessonName() == null) {
                response = new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "invalid request data");
                return response;
            }
            Lesson lesson = new Lesson();
            lesson.setId(reqLesson.getId());
            lesson.setLessonName(reqLesson.getLessonName());

            boolean isAdded = lessonDao.add(lesson);
            if (!isAdded) {
                response = new RespStatus(ExceptionConstants.ADD_FAIL, "add fail");
                return response;
            }
            response = RespStatus.getSuccessMessage();

        } catch (Exception ex) {
            response = new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "internal exception");
            Logger.getLogger(GeneralServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    @Override
    public RespLesson getLessonById(Long lessonId) {
        RespLesson response = new RespLesson();
        try {
            if (lessonId == null) {
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "invalid request data"));
                return response;
            }
            Lesson lesson = lessonDao.getLessonById(lessonId);
            if (lesson == null) {
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "lesson not found"));
                return response;
            }
            response.setId(lesson.getId());
            response.setLessonName(lesson.getLessonName());

            response.setStatus(RespStatus.getSuccessMessage());

        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "internal exception"));
            Logger.getLogger(GeneralServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    @Override
    public RespStatus updateLesson(ReqLesson reqLesson) {
        RespStatus response = null;
        try {
            if (reqLesson.getId() == null || reqLesson.getLessonName() == null) {
                response = new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "invalid request data");
                return response;
            }
            Lesson lesson = new Lesson();
            lesson.setId(reqLesson.getId());
            lesson.setLessonName(reqLesson.getLessonName());

            boolean isUpdated = lessonDao.update(lesson, reqLesson.getId());
            if (!isUpdated) {
                response = new RespStatus(ExceptionConstants.UPDATE_FAIL, "update lesson fail");
                return response;
            }
            response = RespStatus.getSuccessMessage();

        } catch (Exception ex) {
            response = new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "internal exception");
            Logger.getLogger(GeneralServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    @Override
    public RespStatus deleteLesson(Long lessonId) {
        RespStatus response = null;
        try {
            if (lessonId == null) {
                response = new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "invalid request data");
                return response;
            }

            boolean isDeleted = lessonDao.delete(lessonId);
            if (!isDeleted) {
                response = new RespStatus(ExceptionConstants.DELETE_FAIL, "delete lesson fail");
                return response;
            }

            response = RespStatus.getSuccessMessage();

        } catch (Exception ex) {
            response = new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "internal exception");
            Logger.getLogger(GeneralServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    @Override
    public RespInformationList getInformationList() {
        RespInformationList response = new RespInformationList();
        try {
            List<RespInformation> respInformationList = new ArrayList<RespInformation>();
            List<Informations> informationList = infoDao.getInformationsList();
            if (informationList.isEmpty()) {
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "data not found"));
                return response;
            }
            for (Informations information : informationList) {
                RespInformation respInformation = new RespInformation();
                respInformation.setId(information.getId());
                respInformation.setInfo(information.getInformations());

                respInformationList.add(respInformation);
            }

            response.setRespInformationList(respInformationList);
            response.setStatus(RespStatus.getSuccessMessage());

        } catch (Exception e) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "internal exception"));
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public RespStatus addInformation(ReqInformation reqInformation) {
        RespStatus response = null;
        try {
            if (reqInformation.getInfo() == null) {
                response = new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "invalid request data");
                return response;
            }
            Informations information = new Informations();
            information.setId(reqInformation.getId());
            information.setInformations(reqInformation.getInfo());

            boolean isAdded = infoDao.add(information);
            if (!isAdded) {
                response = new RespStatus(ExceptionConstants.ADD_FAIL, "add information fail");
                return response;
            }

            response = RespStatus.getSuccessMessage();

        } catch (Exception ex) {
            response = new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "internal exception");
            Logger.getLogger(GeneralServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    @Override
    public RespInformation getInformationById(Long informationId) {
        RespInformation response = new RespInformation();
        try {
            if (informationId == null) {
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "invalid request data"));
                return response;
            }
            Informations information = infoDao.getInformationById(informationId);
            if (information.getId() == null || information.getInformations() == null) {
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "information not found"));
                return response;
            }
            
            response.setId(information.getId());
            response.setInfo(information.getInformations());
            
            response.setStatus(RespStatus.getSuccessMessage());

        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "internal exception"));
            Logger.getLogger(GeneralServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

}
