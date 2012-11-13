package ar.com.fi.uba.tecnicas.controlador.validador;

/**
 * Interfaz que deben implentar las clases que validen parametros.
 * @author Jonathan
 *
 */
interface ValidadorParametro {
	 
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
	boolean validar(String parametro);

}
