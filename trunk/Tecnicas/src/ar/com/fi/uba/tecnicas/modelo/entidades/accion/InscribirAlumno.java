/**
 * 
 */
package ar.com.fi.uba.tecnicas.modelo.entidades.accion;

import java.util.Date;
import java.util.Set;

import ar.com.fi.uba.tecnicas.modelo.entidades.Inscripcion;
import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;
import ar.com.fi.uba.tecnicas.modelo.excepciones.ValidacionExcepcion;
import ar.com.fi.uba.tecnicas.persistencia.Repositorio;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioIncripciones;

/**
 * @author nacho
 * crea la inscripcion que linkea la materia con el alumno
 *
 */
public class InscribirAlumno implements Accion {

	/**
	 * @see ar.com.fi.uba.tecnicas.modelo.entidades.accion.Accion#ejecutar(ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje, java.util.List)
	 */
	@Override
	public String ejecutar(Mensaje mensaje, Set<Parametro> parametros) {
		Repositorio<Inscripcion> repositorioInscripcion = new RepositorioIncripciones();
		Inscripcion inscripcion = new Inscripcion();
		for (Parametro parametro : parametros) {
			if (parametro.getNombre().equalsIgnoreCase("CODIGO")) {
				inscripcion.setCodigoMateria(parametro.getValor());
			}
			if (parametro.getNombre().equalsIgnoreCase("PADRON")) {
				inscripcion.setPadron(parametro.getValor());
			}
		}
		inscripcion.setFechaInscripcion(new Date());
		inscripcion.setPrimerCuatrimestre(inscripcion.pertenecePrimerCuatrimestre(inscripcion.getFechaInscripcion()));
				
		
		//Valido que no exista una misma inscripcion
		if (!repositorioInscripcion.obtenerTodos().contains(inscripcion)) {
			try {
				repositorioInscripcion.agregar(inscripcion);
			} catch (ValidacionExcepcion e) {
				// Ya existe el usuario
				e.printStackTrace();
				return "Ya existe el usuario inscripto";
			}			
		} else {
			return "Ya existe el usuario inscripto";
		}
		return "";
	}

	@Override
	public String puedeEjecutar(Mensaje mesg, Set<Parametro> parametrosParaAccion) {
		System.out.println("Envio un mail!");
		return "";
	}

}
