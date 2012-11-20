package ar.com.fi.uba.tecnicas.controlador.validador;

import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;


/**
 * Calse que implementa ValidadorParametro y que se encarga 
 * de validar un padron nuevo
 * @author Jonathan
 *
 */
public class ValidadorPadronNuevo implements ValidadorParametro {
	
	/**
	 * @see ar.com.fi.uba.tecnicas.controlador.validador.ValidadorParametro#getDescripcion()
	 */
	@Override
	public String getDescripcion() {
		return "PADRON_NUEVO";
	}
	
	/**
	 * @see ar.com.fi.uba.tecnicas.controlador.validador.ValidadorParametro#validar(java.lang.String)
	 */
	@Override
	public boolean validar(Parametro parametro) {
		try{
			int padron = Integer.parseInt(parametro.getValor());
			if (padron<=0) {
				return false;
			}
			// TODO checkear que sea un padron que no esta en la base de datos de este cuatrimestre stub
			return true;
			
			
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
