package ar.com.fi.uba.tecnicas.controlador.validador;


/**
 * Calse que implementa ValidadorParametro y que se encarga 
 * de validar un padron nuevo
 * @author Jonathan
 *
 */
public class ValidadorPadronDeInscripto implements ValidadorParametro {
	
	/**
	 * @see ar.com.fi.uba.tecnicas.controlador.validador.ValidadorParametro#getDescripcion()
	 */
	@Override
	public String getDescripcion() {
		return "PADRON_INSCRIPTO";
	}
	
	/**
	 * @see ar.com.fi.uba.tecnicas.controlador.validador.ValidadorParametro#validar(java.lang.String)
	 */
	@Override
	public boolean validar(String parametro) {
		try{
			if (Integer.parseInt(parametro)>0) {
				return true;
			}
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	
}
