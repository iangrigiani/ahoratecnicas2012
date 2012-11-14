package ar.com.fi.uba.tecnicas.controlador.validador;


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
	public boolean validar(String parametro) {
		Boolean ret = Boolean.FALSE;
		try{
			if (parametro.equalsIgnoreCase("PUBLICA") || parametro.equalsIgnoreCase("PRIVADA")) {
				ret = Boolean.TRUE;
			}
		} catch (NumberFormatException e) {
		}
		return ret;
	}
}
