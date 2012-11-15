package ar.com.fi.uba.tecnicas.controlador.cadena;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import ar.com.fi.uba.tecnicas.controlador.comun.Constantes;
import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.entidades.Regla;
import ar.com.fi.uba.tecnicas.modelo.entidades.accion.Accion;


/**
 * Representa el eslabon de una cadena
 * @author ramiro
 */
abstract class Eslabon {
	
	private Eslabon eslabon;
	private Regla regla;
	private Mediador mediador;
	
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

}
