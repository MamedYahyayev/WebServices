package az.maqa.project.webservices;

import az.maqa.project.request.ReqInformation;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import az.maqa.project.response.RespClass;
import az.maqa.project.request.ReqClass;
import az.maqa.project.request.ReqLesson;
import az.maqa.project.response.RespClassList;
import az.maqa.project.response.RespInformation;
import az.maqa.project.response.RespInformationList;
import az.maqa.project.response.RespLesson;
import az.maqa.project.response.RespLessonList;
import az.maqa.project.response.RespStatus;
import az.maqa.project.service.GeneralService;
import az.maqa.project.service.GeneralServiceImpl;
import az.maqa.project.utility.ExceptionConstants;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/webservices")
public class WebServices {

    GeneralService generalService = new GeneralServiceImpl();

    @GET
    @Path("/getClassList")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
    public RespClassList getClassList() {
        return generalService.getClassList();
    }

    @POST
    @Path("/addClass")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RespStatus addClass(ReqClass reqClass) {
        return generalService.addClass(reqClass);
    }

    @PUT
    @Path("/updateClass")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RespStatus updateClass(ReqClass reqClass) {
        return generalService.updateClass(reqClass);
    }

    @GET
    @Path("/getClassById")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RespClass getClassById(@QueryParam("classId") Long classId) {
        return generalService.getClassById(classId);
    }

    @PUT
    @Path("/deleteClass")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RespStatus deleteClass(@QueryParam("classId") Long classId) {
        return generalService.deleteClass(classId);
    }

    @GET
    @Path("/getLessonList")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RespLessonList getLessonList() {
        return generalService.getLessonList();
    }

    @POST
    @Path("/addLesson")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RespStatus addLesson(ReqLesson reqLesson) {
        return generalService.addLesson(reqLesson);
    }

    @GET
    @Path("/getLessonById")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RespLesson getLessonById(@QueryParam("lessonId") Long lessonId) {
        return generalService.getLessonById(lessonId);
    }

    @PUT
    @Path("/updateLesson")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RespStatus updateLesson(ReqLesson reqLesson) {
        return generalService.updateLesson(reqLesson);
    }

    @PUT
    @Path("/deleteLesson")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RespStatus deleteLesson(@QueryParam("lessonId") Long lessonId) {
        return generalService.deleteLesson(lessonId);
    }

    @GET
    @Path("/getInformationList")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RespInformationList getInformationList() {
        return generalService.getInformationList();
    }

    @POST
    @Path("/addInformation")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RespStatus addInformation(ReqInformation reqInformation) {
        return generalService.addInformation(reqInformation);
    }
    
    @GET
    @Path("/getInformationById")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RespInformation getInformationById(@QueryParam("informationId") Long informationId){
        return generalService.getInformationById(informationId);
    }

}
