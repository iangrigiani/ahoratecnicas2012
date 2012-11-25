/**
 * 
 */
package ar.com.fi.uba.tecnicas.modelo.entidades.accion;

import java.util.Set;

import ar.com.fi.uba.tecnicas.modelo.entidades.Alumno;
import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;
import ar.com.fi.uba.tecnicas.modelo.excepciones.ValidacionExcepcion;
import ar.com.fi.uba.tecnicas.persistencia.Repositorio;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioAlumno;

/**
 * @author nacho
 * crea la entidad alumno con los datos enviados por mail (padron, nombre, mail)
 *
 */
public class AltaAlumno implements Accion {

	/**
	 * @see ar.com.fi.uba.tecnicas.modelo.entidades.accion.Accion#ejecutar(ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje, java.util.List)
	 */
	@Override
	public String ejecutar(Mensaje mensaje, Set<Parametro> parametros) {
		System.out.println("Se va a crear un nuevo usuario!");
		Repositorio<Alumno> repositorioAlumno = RepositorioAlumno.getInstance();
		Alumno alumno = new Alumno();
		alumno.setMail(mensaje.getDe());
		for (Parametro parametro : parametros) {
			if (parametro.getNombre().equalsIgnoreCase("PADRON")) {
				alumno.setPadron(parametro.getValor());
			}
			if (parametro.getNombre().equalsIgnoreCase("NOMBRE")) {
				alumno.setPadron(parametro.getValor());
			}
		}
		try {
			repositorioAlumno.agregar(alumno);
		} catch (ValidacionExcepcion e) {
			// Ya existe el usuario
			e.printStackTrace();
			return "Ya existe el usuario";
		}
		return "";
	}

	@Override
	public String puedeEjecutar(Mensaje mesg, Set<Parametro> parametrosParaAccion) {
		Repositorio<Alumno> repositorioAlumno = RepositorioAlumno.getInstance();
		String padron = "";
		for (Parametro parametro : parametrosParaAccion) {
			if (parametro.getNombre().equalsIgnoreCase("PADRON")) {
				padron = parametro.getValor();
			}
		}
		if (repositorioAlumno.obtener(padron) != null) {
			return "El usuario ya existe. No puede agregarse el alumno";
		}
		return "";
	}

}
