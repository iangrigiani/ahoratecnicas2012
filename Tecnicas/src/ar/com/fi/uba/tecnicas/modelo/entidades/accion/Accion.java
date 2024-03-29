/**
 * 
 */
package ar.com.fi.uba.tecnicas.modelo.entidades.accion;

import java.util.Set;

import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;

/**
 * @author ramiro
 *
 */
public interface Accion {
	
    String ejecutar(Mensaje mensaje, Set<Parametro> parametros);

    String puedeEjecutar(Mensaje mesg, Set<Parametro> parametrosParaAccion);
	
}
