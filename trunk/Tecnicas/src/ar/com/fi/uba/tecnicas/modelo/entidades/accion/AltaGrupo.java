/**
 * 
 */
package ar.com.fi.uba.tecnicas.modelo.entidades.accion;

import java.util.Set;

import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;

/**
 * @author nacho
 * Parsea el txt de los padrones
 * Chequea que los padrones sean validos
 * Chequea que los padrones existan en la materia en el cuatrimestre
 * Chequea que los padrones pertenezcan al mismo grupo
 * 
 *
 */
public class AltaGrupo implements Accion {

	/**
	 * @see ar.com.fi.uba.tecnicas.modelo.entidades.accion.Accion#ejecutar(ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje, java.util.List)
	 */
	@Override
	public void ejecutar(Mensaje mensaje, Set<Parametro> parametros) {
		System.out.println("Envio un mail!");
	}

	@Override
	public String puedeEjecutar(Mensaje mesg, Set<Parametro> parametrosParaAccion) {
		System.out.println("Envio un mail!");
		return "";
	}

}
