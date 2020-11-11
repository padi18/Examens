import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "acum")
public class Acum {
    private int alumnPos;
    private int personalPos;
    private int altresPos;

    @XmlElement(name = "alumn_positiu_acum")
    public int getAlumnPos() {
        return this.alumnPos;
    }

    public void setAlumnPos(int alumnPos) {
        this.alumnPos = alumnPos;
    }

    @XmlElement(name = "personal_positiu_acum")
    public int getPersonalPos() {
        return this.personalPos;
    }

    public void setPersonalPos(int personalPos) {
        this.personalPos = personalPos;
    }

    @XmlElement(name = "altres_positiu_acum")
    public int getAltresPos() {
        return this.altresPos;
    }

    public void setAltresPos(int altresPos) {
        this.altresPos = altresPos;
    }


    @Override
    public String toString() {
        return "{" +
            " alumnPos='" + getAlumnPos() + "'" +
            ", personalPos='" + getPersonalPos() + "'" +
            ", altresPos='" + getAltresPos() + "'" +
            "}";
    }

}
