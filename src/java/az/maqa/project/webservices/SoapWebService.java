package az.maqa.project.webservices;

import az.maqa.project.response.RespClass;
import az.maqa.project.response.RespClassList;
import az.maqa.project.response.RespInformation;
import az.maqa.project.response.RespInformationList;
import az.maqa.project.response.RespLesson;
import az.maqa.project.response.RespLessonList;
import az.maqa.project.service.GeneralService;
import az.maqa.project.service.GeneralServiceImpl;
import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService(serviceName = "SoapWebService")
public class SoapWebService {

    GeneralService generalService = new GeneralServiceImpl();

    @WebMethod(operationName = "getClassList")
    public RespClassList getClassList() {
        return generalService.getClassList();
    }

    @WebMethod(operationName = "getClassById")
    public RespClass getClassById(Long classId) {
        return generalService.getClassById(classId);
    }

    @WebMethod(operationName = "getLessonList")
    public RespLessonList getLessonList() {
        return generalService.getLessonList();
    }

    @WebMethod(operationName = "getLessonById")
    public RespLesson getLessonById(Long lessonId) {
        return generalService.getLessonById(lessonId);
    }

    @WebMethod(operationName = "getInformationList")
    public RespInformationList getInformationList() {
        return generalService.getInformationList();
    }

    @WebMethod(operationName = "getInformationById")
    public RespInformation getInformationById(Long informationId) {
        return generalService.getInformationById(informationId);
    }
}
