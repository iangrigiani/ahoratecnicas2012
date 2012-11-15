package ar.com.fi.uba.tecnicas.controlador.validador;

import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;


/**
 * Calse que implementa ValidadorParametro y que se encarga 
 * de validar un padron nuevo
 * @author Jonathan
 *
 */
public class ValidadorPrivacidad implements ValidadorParametro {
	
	/**
	 * @see ar.com.fi.uba.tecnicas.controlador.validador.ValidadorParametro#getDescripcion()
	 */
	@Override
	public String getDescripcion() {
		return "Privacidad";
	}
	
	/**
	 * @see ar.com.fi.uba.tecnicas.controlador.validador.ValidadorParametro#validar(java.lang.String)
	 */
	@Override
	public boolean validar(Parametro parametro) {
		Boolean ret = Boolean.FALSE;
		try{
			if (parametro.getValor().equalsIgnoreCase("PUBLICA") || parametro.getValor().equalsIgnoreCase("PRIVADA")) {
				ret = Boolean.TRUE;
			}
		} catch (NumberFormatException e) {
		}
		return ret;
	}
}
