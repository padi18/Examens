import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "row")
public class Covid {

    private Long codiCentre;
    private List<Confinats> confinats;
    private List<Acum> positius;
    private List<Vig_11> vig_11;

    @XmlElement(name = "codcentre")
    public Long getCodiCentre() {
        return this.codiCentre;
    }

    public void setCodiCentre(Long codiCentre) {
        this.codiCentre = codiCentre;
    }

    @XmlElement(name = "confin")
    public List<Confinats> getConfinats() {
        return this.confinats;
    }

    public void setConfinats(List<Confinats> confinats) {
        this.confinats = confinats;
    }

    @XmlElement(name = "acum")
    public List<Acum> getPositius() {
        return this.positius;
    }

    public void setPositius(List<Acum> positius) {
        this.positius = positius;
    }

    @XmlElement(name = "vig_11")
    public List<Vig_11> getVig_11() {
        return this.vig_11;
    }

    public void setVig_11(List<Vig_11> vig_11) {
        this.vig_11 = vig_11;
    }

    @Override
    public String toString() {
        return "{" +
            " codiCentre='" + getCodiCentre() + "'" +
            ", confinats='" + getConfinats() + "'" +
            ", positius='" + getPositius() + "'" +
            ", vig_11='" + getVig_11() + "'" +
            "}";
    }
    
}
