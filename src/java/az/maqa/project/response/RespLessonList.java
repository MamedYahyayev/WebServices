package az.maqa.project.response;

import az.maqa.project.model.Lesson;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RespLessonList {

    private List<RespLesson> respLessonList;
    private RespStatus status;

    public List<RespLesson> getRespLessonList() {
        return respLessonList;
    }

    public void setRespLessonList(List<RespLesson> respLessonList) {
        this.respLessonList = respLessonList;
    }

    public RespStatus getStatus() {
        return status;
    }

    public void setStatus(RespStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RespLessonList{" + "respLessonList=" + respLessonList + ", status=" + status + '}';
    }

}
