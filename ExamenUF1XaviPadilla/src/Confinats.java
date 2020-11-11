import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "confin")
public class Confinats {
    private int grupConf;
    private int alumnConf;
    private int docentConf;
    private int altresConf;

    @XmlElement(name = "grup_confin")
    public int getGrupConf() {
        return this.grupConf;
    }

    public void setGrupConf(int grupConf) {
        this.grupConf = grupConf;
    }

    @XmlElement(name = "alumn_confin")
    public int getAlumnConf() {
        return this.alumnConf;
    }

    public void setAlumnConf(int alumnConf) {
        this.alumnConf = alumnConf;
    }

    @XmlElement(name = "docent_confin")
    public int getDocentConf() {
        return this.docentConf;
    }

    public void setDocentConf(int docentConf) {
        this.docentConf = docentConf;
    }

    @XmlElement(name = "altres_confin")
    public int getAltresConf() {
        return this.altresConf;
    }

    public void setAltresConf(int altresConf) {
        this.altresConf = altresConf;
    }
}
