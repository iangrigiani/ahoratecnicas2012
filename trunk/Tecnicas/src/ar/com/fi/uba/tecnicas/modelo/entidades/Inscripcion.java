/**
 * 
 */
package ar.com.fi.uba.tecnicas.modelo.entidades;

import java.util.Date;

/**
 * @author ramsal
 *
 */
public class Inscripcion {

	private String padron;
	private String codigoMateria;
	private Date fechaInscripcion;
	private Boolean primerCuatrimestre;
	
	@SuppressWarnings("deprecation")
	public Boolean pertenecePrimerCuatrimestre(Date fecha) {
		Date limiteSuperiorPrimerCuatri = new Date(fecha.getYear(), 6, 30);
		Date limiteInferiorPrimerCuatri = new Date(fecha.getYear(), 1, 1);
		if (fecha.compareTo(limiteInferiorPrimerCuatri) >= 0 && fecha.compareTo(limiteSuperiorPrimerCuatri) <= 0) {
			return true;
		}
		return  false; 
	}
	
	@SuppressWarnings("deprecation")
	public Boolean perteneceSegundoCuatrimestre(Date fecha) {
		Date limiteSuperiorSegundoCuatri = new Date(fecha.getYear(), 12, 31);
		Date limiteInferiorSegundoCuatri = new Date(fecha.getYear(), 7, 1);
		if (fecha.compareTo(limiteInferiorSegundoCuatri) >= 0 && fecha.compareTo(limiteSuperiorSegundoCuatri) <= 0) {
			return true;
		}
		return  false;
	}
	
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
	 * @return the codigoMateria
	 */
	public String getCodigoMateria() {
		return codigoMateria;
	}
	/**
	 * @param codigoMateria the codigoMateria to set
	 */
	public void setCodigoMateria(String codigoMateria) {
		this.codigoMateria = codigoMateria;
	}

	/**
	 * @return the fechaInscripcion
	 */
	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	/**
	 * @param fechaInscripcion the fechaInscripcion to set
	 */
	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
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

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoMateria == null) ? 0 : codigoMateria.hashCode());
		result = prime * result + ((padron == null) ? 0 : padron.hashCode());
		result = prime
				* result
				+ ((primerCuatrimestre == null) ? 0 : primerCuatrimestre
						.hashCode());
		return result;
	}

	/**
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
		Inscripcion other = (Inscripcion) obj;
		if (codigoMateria == null) {
			if (other.codigoMateria != null)
				return false;
		} else if (!codigoMateria.equals(other.codigoMateria))
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
