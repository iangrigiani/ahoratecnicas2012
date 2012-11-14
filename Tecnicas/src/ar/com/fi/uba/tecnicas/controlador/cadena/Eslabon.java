package ar.com.fi.uba.tecnicas.controlador.cadena;

import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.entidades.Regla;


/**
 * Representa el eslabon de una cadena
 * @author ramiro
 */
abstract class Eslabon {
	
	private Eslabon eslabon;
	private Regla regla;
	private Mediador mediador;
	private String regex;
	
	abstract void sendToEslabon(Mensaje mesg) throws Exception;
	
	/**
	 * @return the eslabon
	 */
	public Eslabon getEslabon() {
		return eslabon;
	}
	/**
	 * @param eslabon the eslabon to set
	 */
	public void setEslabon(Eslabon eslabon) {
		this.eslabon = eslabon;
	}
	/**
	 * @return the regla
	 */
	public Regla getRegla() {
		return regla;
	}
	/**
	 * @param regla the regla to set
	 */
	public void setRegla(Regla regla) {
		this.regla = regla;
	}
	/**
	 * @return the mediador
	 */
	public Mediador getMediador() {
		return mediador;
	}
	/**
	 * @param mediador the mediador to set
	 */
	public void setMediador(Mediador mediador) {
		this.mediador = mediador;
	}

	/**
	 * @return the regex
	 */
	public String getRegex() {
		return regex;
	}

	/**
	 * @param regex the regex to set
	 */
	public void setRegex(String regex) {
		this.regex = regex;
	}

}
