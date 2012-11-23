package ar.com.fi.uba.tecnicas.modelo.entidades.accion;

import java.util.Date;
import java.util.Set;

import ar.com.fi.uba.tecnicas.modelo.entidades.Inscripcion;
import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;
import ar.com.fi.uba.tecnicas.persistencia.Repositorio;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioIncripciones;

// Chequea que el padron no exista en la materia en el cuatrimestre
public class PadronEnMateria implements Accion {

	@Override
	public String ejecutar(Mensaje mensaje, Set<Parametro> parametros) {
		Repositorio<Inscripcion> repo = new RepositorioIncripciones();
		Inscripcion ins = new Inscripcion();
		for (Parametro parametro : parametros) {
			if (parametro.getNombre().equalsIgnoreCase("CODIGO")) {
				ins.setCodigoMateria(parametro.getValor());
			}
			if (parametro.getNombre().equalsIgnoreCase("PADRON")) {
				ins.setPadron(parametro.getValor());
			}
		}
		ins.setPrimerCuatrimestre(ins.pertenecePrimerCuatrimestre(new Date()));
		
		//TODO: Ver que devolver en cada caso
		if (repo.obtenerTodos().contains(ins)) {
			return "Ya existe el padron inscripto";
		}
		return "El padron no exite";
	}

	@Override
	public String puedeEjecutar(Mensaje mesg,
			Set<Parametro> parametrosParaAccion) {
		// TODO Auto-generated method stub
		return null;
	}

}