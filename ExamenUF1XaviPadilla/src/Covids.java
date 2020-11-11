import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class Covids {

    private List<Covid> llistaRow;

    @XmlElementWrapper(name = "rows")
    @XmlElement(name = "row")
    public List<Covid> getLlistaRow() {
        return this.llistaRow;
    }

    public void setLlistaRow(List<Covid> llistaRow) {
        this.llistaRow = llistaRow;
    }

}
