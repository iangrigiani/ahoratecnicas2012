package ar.com.fi.uba.tecnicas.controlador.validador;

import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;

/**
 * Interfaz que deben implentar las clases que validen parametros.
 * @author Jonathan
 *
 */
public interface ValidadorParametro {
	 
	/**
	 * Obtengo la descripcion del parametro que estoy validando
	 * @return La descripcion 
	 */
	String getDescripcion();
	
	/**
	 * Valida el valor recibido
	 * @param parametro El valor que viene por parametro
	 * @return True si valida, false sino valida.
	 */
	boolean validar(Parametro parametro);

}
