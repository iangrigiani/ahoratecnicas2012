/**
 * 
 */
package ar.com.fi.uba.tecnicas.persistencia;

import ar.com.fi.uba.tecnicas.modelo.entidades.Alumno;
import ar.com.fi.uba.tecnicas.modelo.entidades.AlumnoGrupo;
import ar.com.fi.uba.tecnicas.modelo.entidades.Grupo;
import ar.com.fi.uba.tecnicas.modelo.entidades.Inscripcion;
import ar.com.fi.uba.tecnicas.modelo.entidades.Materia;
import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.entidades.Regla;

/**
 * @author ramiro
 *
 */
public class RepositorioBuilder {
	
	public Repositorio<Regla> getRep3ositorioRegla() {
		return RepositorioReglas.getInstance();
	}
	
	public Repositorio<Materia> getRepositorioMateria() {
		return RepositorioMateria.getInstance();
	}
	
	public Repositorio<Alumno> getRepositorioAlumno() {
		return RepositorioAlumno.getInstance();
	}
	public Repositorio<AlumnoGrupo> getRepositorioAlumnoGrupo() {
		return RepositorioAlumnoGrupo.getInstance();
	}
	public Repositorio<Inscripcion> getRepositorioInscripciones() {
		return RepositorioIncripciones.getInstance();
	}
	public Repositorio<Grupo> getRepositorioGrupo() {
		return RepositorioGrupo.getInstance();
	}
	public Repositorio<Mensaje> getRepositorioTickets() {
		return RepositorioTickets.getInstance();
	}
}

