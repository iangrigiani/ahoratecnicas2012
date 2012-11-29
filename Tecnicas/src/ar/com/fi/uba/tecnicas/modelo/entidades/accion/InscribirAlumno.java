/**
 * 
 */
package ar.com.fi.uba.tecnicas.modelo.entidades.accion;

import java.util.Date;
import java.util.Set;

import ar.com.fi.uba.tecnicas.controlador.comun.CuatrimestreHelper;
import ar.com.fi.uba.tecnicas.controlador.comun.Mensajes;
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

	private Inscripcion inscripcion;
	
	/**
	 * @see ar.com.fi.uba.tecnicas.modelo.entidades.accion.Accion#ejecutar(ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje, java.util.List)
	 */
	@Override
	public String ejecutar(Mensaje mensaje, Set<Parametro> parametros) {
		Repositorio<Inscripcion> repositorioInscripcion = RepositorioIncripciones.getInstance();
		//Valido que no exista una misma inscripcion
		if (!repositorioInscripcion.obtenerTodos().contains(inscripcion)) {
			try {
				repositorioInscripcion.agregar(inscripcion);
			} catch (ValidacionExcepcion e) {
				System.out.println(Mensajes.REPOSITORIO_YA_EXISTE_INSCRIPCION + " Padron: " + inscripcion.getPadron() + ", Materia: " + inscripcion.getCodigoMateria());
				return Mensajes.REPOSITORIO_YA_EXISTE_INSCRIPCION;
			}			
		} else {
			return Mensajes.REPOSITORIO_YA_EXISTE_INSCRIPCION;
		}
		return "";
	}

	private void armarInscripcion(Set<Parametro> parametros) {
		inscripcion = new Inscripcion();
		for (Parametro parametro : parametros) {
			if (parametro.getValidador().getDescripcion().equalsIgnoreCase("CODIGO_MATERIA")) {
				inscripcion.setCodigoMateria(parametro.getValor());
			}
			if (parametro.getValidador().getDescripcion().equalsIgnoreCase("PADRON_NUEVO")) {
				inscripcion.setPadron(parametro.getValor());
			}
		}
		inscripcion.setFechaInscripcion(new Date());
		inscripcion.setPrimerCuatrimestre(CuatrimestreHelper.pertenecePrimerCuatrimestre(inscripcion.getFechaInscripcion()));
	}
	@Override
	public String puedeEjecutar(Mensaje mesg, Set<Parametro> parametrosParaAccion) {
		Repositorio<Inscripcion> repositorioInscripcion = RepositorioIncripciones.getInstance();
		armarInscripcion(parametrosParaAccion);
		if (repositorioInscripcion.obtenerTodos().contains(inscripcion)) {
			System.out.println(Mensajes.REPOSITORIO_YA_EXISTE_INSCRIPCION + " Padron: " + inscripcion.getPadron() + ", Materia: " + inscripcion.getCodigoMateria());
			return Mensajes.REPOSITORIO_YA_EXISTE_INSCRIPCION;
		}
		return "";
	}

}

