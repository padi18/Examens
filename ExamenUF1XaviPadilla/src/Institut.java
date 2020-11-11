import java.io.Serializable;


public class Institut implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long Codi;
	private String Nom;
	private String Naturalesa;
	private String Municipi;
	private Integer CodiComarca;
	
	public Institut() {
		super();
	}

    public Institut(Long codi, String nom, String naturalesa, String municipi, Integer codiComarca) {
        super();
        Codi = codi;
        Nom = nom;
        Naturalesa = naturalesa;
        Municipi = municipi;
        CodiComarca = codiComarca;
    }

	public Long getCodi() {
		return Codi;
	}

    public void setCodi(Long codi) {
        Codi = codi;
    }

    
	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getNaturalesa() {
		return Naturalesa;
	}

	public void setNaturalesa(String naturalesa) {
		Naturalesa = naturalesa;
	}

	public String getMunicipi() {
		return Municipi;
	}

	public void setMunicipi(String municipi) {
		Municipi = municipi;
	}

	public Integer getCodiComarca() {
		return CodiComarca;
	}

	public void setCodiComarca(Integer codiComarca) {
		CodiComarca = codiComarca;
	}

	@Override
	public String toString() {
		return "Institut [Codi=" + Codi + ", Nom=" + Nom + ", Naturalesa=" + Naturalesa + ", Municipi=" + Municipi
				+ ", CodiComarca=" + CodiComarca + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Codi == null) ? 0 : Codi.hashCode());
		result = prime * result + ((CodiComarca == null) ? 0 : CodiComarca.hashCode());
		result = prime * result + ((Municipi == null) ? 0 : Municipi.hashCode());
		result = prime * result + ((Naturalesa == null) ? 0 : Naturalesa.hashCode());
		result = prime * result + ((Nom == null) ? 0 : Nom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Institut other = (Institut) obj;
		if (Codi == null) {
			if (other.Codi != null)
				return false;
		} else if (!Codi.equals(other.Codi))
			return false;
		if (CodiComarca == null) {
			if (other.CodiComarca != null)
				return false;
		} else if (!CodiComarca.equals(other.CodiComarca))
			return false;
		if (Municipi == null) {
			if (other.Municipi != null)
				return false;
		} else if (!Municipi.equals(other.Municipi))
			return false;
		if (Naturalesa == null) {
			if (other.Naturalesa != null)
				return false;
		} else if (!Naturalesa.equals(other.Naturalesa))
			return false;
		if (Nom == null) {
			if (other.Nom != null)
				return false;
		} else if (!Nom.equals(other.Nom))
			return false;
		return true;
	}

	

}