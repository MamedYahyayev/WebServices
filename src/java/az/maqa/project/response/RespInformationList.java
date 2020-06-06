
package az.maqa.project.response;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RespInformationList {
    private List<RespInformation> respInformationList;
    private RespStatus status;

    public List<RespInformation> getRespInformationList() {
        return respInformationList;
    }

    public void setRespInformationList(List<RespInformation> respInformationList) {
        this.respInformationList = respInformationList;
    }

    public RespStatus getStatus() {
        return status;
    }

    public void setStatus(RespStatus status) {
        this.status = status;
    }
    
    
    
}
