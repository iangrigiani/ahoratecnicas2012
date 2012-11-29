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
		Repositorio<Alumno> repositorioAlumno = RepositorioAlumno.getInstance();
		Alumno alumno = new Alumno();
		alumno.setMail(mensaje.getDe());
		for (Parametro parametro : parametros) {
			if (parametro.getValidador().getDescripcion().equalsIgnoreCase("PADRON_NUEVO")) {
				alumno.setPadron(parametro.getValor());
			}
			if (parametro.getValidador().getDescripcion().equalsIgnoreCase("NOMBRE_ALUMNO")) {
				alumno.setNombre(parametro.getValor());
			}
		}
		try {
			repositorioAlumno.agregar(alumno);
			System.out.println("Alta Usuario: Se a creado un nuevo alumno [" + alumno.getNombre() + "] con padron: " + alumno.getPadron());
		} catch (ValidacionExcepcion e) {
			return "Alta Alumno: Ya existe un alumno con ese padrón [" + alumno.getPadron() + "]";
		}
		return "";
	}

	@Override
	public String puedeEjecutar(Mensaje mesg, Set<Parametro> parametrosParaAccion) {
		Repositorio<Alumno> repositorioAlumno = RepositorioAlumno.getInstance();
		String padron = "";
		for (Parametro parametro : parametrosParaAccion) {
			if (parametro.getValidador().getDescripcion().equalsIgnoreCase("PADRON_NUEVO")) {
				padron = parametro.getValor();
			}
		}
		if (repositorioAlumno.obtener(padron) != null) {
			return "Alta de alumno: El alumno con padrón [" + padron + "] ya existe. No puede agregarse el alumno.";
		}
		return "";
	}

}

