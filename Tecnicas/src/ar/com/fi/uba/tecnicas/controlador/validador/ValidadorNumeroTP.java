package ar.com.fi.uba.tecnicas.controlador.validador;

import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;


/**
 * Calse que implementa ValidadorParametro y que se encarga 
 * de validar un padron nuevo
 * @author Jonathan
 *
 */
public class ValidadorNumeroTP implements ValidadorParametro {
	
	/**
	 * @see ar.com.fi.uba.tecnicas.controlador.validador.ValidadorParametro#getDescripcion()
	 */
	@Override
	public String getDescripcion() {
		return "Numero de TP";
	}
	
	/**
	 * @see ar.com.fi.uba.tecnicas.controlador.validador.ValidadorParametro#validar(java.lang.String)
	 */
	@Override
	public boolean validar(Parametro parametro) {
		try{
			if (Integer.parseInt(parametro.getValor())>0) {
				return true;
			}
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
