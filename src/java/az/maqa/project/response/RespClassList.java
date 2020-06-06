package az.maqa.project.response;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RespClassList {

    private List<RespClass> respClassList;
    private RespStatus status;

    public List<RespClass> getRespClassList() {
        return respClassList;
    }

    public void setRespClassList(List<RespClass> respClassList) {
        this.respClassList = respClassList;
    }

    public RespStatus getStatus() {
        return status;
    }

    public void setStatus(RespStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RespClassList{" + "respClassList=" + respClassList + ", status=" + status + '}';
    }

}
