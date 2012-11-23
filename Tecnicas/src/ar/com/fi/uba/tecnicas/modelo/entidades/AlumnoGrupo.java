/**
 * 
 */
package ar.com.fi.uba.tecnicas.modelo.entidades;

/**
 * @author ramsal
 *
 */
public class AlumnoGrupo {

	private String padron;
	private String grupo;
	private Boolean primerCuatrimestre;
	/**
	 * @return the padron
	 */
	public String getPadron() {
		return padron;
	}
	/**
	 * @param padron the padron to set
	 */
	public void setPadron(String padron) {
		this.padron = padron;
	}
	/**
	 * @return the grupo
	 */
	public String getGrupo() {
		return grupo;
	}
	/**
	 * @param grupo the grupo to set
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	/**
	 * @return the primerCuatrimestre
	 */
	public Boolean getPrimerCuatrimestre() {
		return primerCuatrimestre;
	}
	/**
	 * @param primerCuatrimestre the primerCuatrimestre to set
	 */
	public void setPrimerCuatrimestre(Boolean primerCuatrimestre) {
		this.primerCuatrimestre = primerCuatrimestre;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		result = prime * result + ((padron == null) ? 0 : padron.hashCode());
		result = prime
				* result
				+ ((primerCuatrimestre == null) ? 0 : primerCuatrimestre
						.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlumnoGrupo other = (AlumnoGrupo) obj;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.equals(other.grupo))
			return false;
		if (padron == null) {
			if (other.padron != null)
				return false;
		} else if (!padron.equals(other.padron))
			return false;
		if (primerCuatrimestre == null) {
			if (other.primerCuatrimestre != null)
				return false;
		} else if (!primerCuatrimestre.equals(other.primerCuatrimestre))
			return false;
		return true;
	}
}
