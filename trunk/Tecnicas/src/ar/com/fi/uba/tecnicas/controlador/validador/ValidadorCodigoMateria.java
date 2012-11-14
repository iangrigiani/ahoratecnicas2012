package ar.com.fi.uba.tecnicas.controlador.validador;


/**
 * Calse que implementa ValidadorParametro y que se encarga 
 * de validar un padron nuevo
 * @author Jonathan
 *
 */
public class ValidadorCodigoMateria implements ValidadorParametro {
	
	/**
	 * @see ar.com.fi.uba.tecnicas.controlador.validador.ValidadorParametro#getDescripcion()
	 */
	@Override
	public String getDescripcion() {
		return "Codigo de la materia";
	}
	
	/**
	 * @see ar.com.fi.uba.tecnicas.controlador.validador.ValidadorParametro#validar(java.lang.String)
	 */
	@Override
	public boolean validar(String parametro) {
		Boolean ret = Boolean.FALSE;
		try{
			//Agregar el existe materia con un repositorio
			if (Integer.parseInt(parametro)>0) {
				ret = Boolean.TRUE;
			}
		} catch (NumberFormatException e) {
		}
		return ret;
	}
}
