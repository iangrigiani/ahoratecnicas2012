package ar.com.fi.uba.tecnicas.modelo.entidades.accion;

import java.util.Set;

import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;

// Chequea que el padron no exista en la materia en el cuatrimestre
public class PadronEnMateria implements Accion {

	@Override
	public void ejecutar(Mensaje mensaje, Set<Parametro> parametros) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String puedeEjecutar(Mensaje mesg,
			Set<Parametro> parametrosParaAccion) {
		// TODO Auto-generated method stub
		return null;
	}

}
