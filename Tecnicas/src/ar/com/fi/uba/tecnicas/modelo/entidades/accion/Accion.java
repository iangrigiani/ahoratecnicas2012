/**
 * 
 */
package ar.com.fi.uba.tecnicas.modelo.entidades.accion;

import java.util.List;

import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;

/**
 * @author ramiro
 *
 */
public interface Accion {
	
	void ejecutar(Mensaje mensaje, List<Parametro> parametros);
	
}
