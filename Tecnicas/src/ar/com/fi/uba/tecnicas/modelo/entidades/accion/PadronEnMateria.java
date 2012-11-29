package ar.com.fi.uba.tecnicas.modelo.entidades.accion;

import java.util.Date;
import java.util.Set;

import ar.com.fi.uba.tecnicas.controlador.comun.CuatrimestreHelper;
import ar.com.fi.uba.tecnicas.controlador.comun.Mensajes;
import ar.com.fi.uba.tecnicas.modelo.entidades.Inscripcion;
import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;
import ar.com.fi.uba.tecnicas.persistencia.Repositorio;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioIncripciones;

/**
 *  Chequea que el padron no exista en la materia en el cuatrimestre
 */
public class PadronEnMateria implements Accion {

	private Inscripcion inscripcion;
	
	/**
	 * @see ar.com.fi.uba.tecnicas.modelo.entidades.accion.Accion#ejecutar(ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje, java.util.Set)
	 */
	@Override
	public String ejecutar(Mensaje mensaje, Set<Parametro> parametros) {
		return validarPadronEnMateria(parametros);
	}


	/**
	 * @see ar.com.fi.uba.tecnicas.modelo.entidades.accion.Accion#puedeEjecutar(ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje, java.util.Set)
	 */
	@Override
	public String puedeEjecutar(Mensaje mesg, Set<Parametro> parametrosParaAccion) {
		return validarPadronEnMateria(parametrosParaAccion);
	}

	
	private String validarPadronEnMateria(Set<Parametro> parametros) {
		Repositorio<Inscripcion> repo = RepositorioIncripciones.getInstance();
		this.inscripcion = new Inscripcion();
		for (Parametro parametro : parametros) {
			if (parametro.getValidador().getDescripcion().equalsIgnoreCase("CODIGO")) {
				inscripcion.setCodigoMateria(parametro.getValor());
			}
			if (parametro.getValidador().getDescripcion().equalsIgnoreCase("PADRON_INSCRIPTO")) {
				inscripcion.setPadron(parametro.getValor());
			}
		}
		inscripcion.setPrimerCuatrimestre(CuatrimestreHelper.pertenecePrimerCuatrimestre(new Date()));
		if (repo.obtenerTodos().contains(inscripcion)) {
			return "";
		}
		System.out.println(Mensajes.ACCION_PADRON_MATERIA_ALUMNO_NO_INSCRIPTO + " Padron: " + inscripcion.getPadron() + ", Materia: " + inscripcion.getCodigoMateria());
		return Mensajes.ACCION_PADRON_MATERIA_ALUMNO_NO_INSCRIPTO + " Padron: " + inscripcion.getPadron() + ", Materia: " + inscripcion.getCodigoMateria();
	}
}
