package ar.com.fi.uba.tecnicas.controlador.validador;

import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioBuilder;


/**
 * Calse que implementa ValidadorParametro y que se encarga 
 * de validar un padron nuevo
 * @author Jonathan
 *
 */
public class ValidadorNumeroGrupo implements ValidadorParametro {
	
	/**
	 * @see ar.com.fi.uba.tecnicas.controlador.validador.ValidadorParametro#getDescripcion()
	 */
	@Override
	public String getDescripcion() {
		return "NUMERO_GRUPO";
	}
	
	/**
	 * @see ar.com.fi.uba.tecnicas.controlador.validador.ValidadorParametro#validar(java.lang.String)
	 */
	@Override
	public boolean validar(Parametro parametro) {
		try{
			RepositorioBuilder repoB = new RepositorioBuilder();
			Boolean ret = Boolean.TRUE;
			if (Integer.parseInt(parametro.getValor())>0) {
				ret = Boolean.TRUE;
			}
			if (repoB.getRepositorioGrupo().obtener(parametro.getValor()) == null) {
				ret = Boolean.FALSE;
			}
			return ret;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
