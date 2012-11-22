/**
 * 
 */
package ar.com.fi.uba.tecnicas.modelo.entidades.accion;

import java.util.List;
import java.util.Set;

import ar.com.fi.uba.tecnicas.modelo.entidades.Alumno;
import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;
import ar.com.fi.uba.tecnicas.persistencia.Repositorio;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioAlumno;

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
	public String ejecutar(Mensaje mensaje, Set<Parametro> parametros) {
		Repositorio<Alumno> repositorioAlumno = new RepositorioAlumno();
		List<Alumno> obtenerTodos = repositorioAlumno.obtenerTodos();
		for (Alumno alumno : obtenerTodos) {
			if (alumno.getMail().equalsIgnoreCase(mensaje.getDe())) {
				return "";
			}
		}
		return "No existe";
	}

	@Override
	public String puedeEjecutar(Mensaje mesg, Set<Parametro> parametrosParaAccion) {
		System.out.println("Envio un mail!");
		return "";
	}

}
