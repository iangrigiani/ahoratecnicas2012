package ar.com.fi.uba.tecnicas.controlador.validador;

import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioBuilder;


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
		RepositorioBuilder repoB = new RepositorioBuilder();
		Boolean ret = Boolean.TRUE;
		try{
			int padron = Integer.parseInt(parametro.getValor());
			if (padron<=0) {
				ret = Boolean.FALSE;
			}
			// TODO checkear que sea un padron que no esta en la base de datos de este cuatrimestre stub
			if (repoB.getRepositorioAlumno().obtener(parametro.getValor()) != null) {
				ret = Boolean.FALSE;
			}
			return ret;
			
			
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
