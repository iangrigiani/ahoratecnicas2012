/**
 * 
 */
package ar.com.fi.uba.tecnicas.modelo.entidades.accion;

import java.util.Set;

import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;

/**
 * @author nacho
 * chequea que el mail el emisor pertenezca a un alumno inscripto en la materia en el cuatrimestre
 *
 */
public class MailDeAlumnoInscripto implements Accion {

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
