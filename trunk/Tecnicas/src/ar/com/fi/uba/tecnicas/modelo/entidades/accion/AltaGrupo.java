/**
 * 
 */
package ar.com.fi.uba.tecnicas.modelo.entidades.accion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import ar.com.fi.uba.tecnicas.controlador.validador.ValidadorPadronDeInscripto;
import ar.com.fi.uba.tecnicas.modelo.entidades.Alumno;
import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;
import ar.com.fi.uba.tecnicas.persistencia.Repositorio;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioAlumno;

/**
 * @author nacho
 * Parsea el txt de los padrones
 * Chequea que los padrones sean validos
 * Chequea que los padrones existan en la materia en el cuatrimestre
 * Chequea que los padrones NO pertenezcan ya a algun grupo
 * 
 *
 */
public class AltaGrupo implements Accion {

	/**
	 * @see ar.com.fi.uba.tecnicas.modelo.entidades.accion.Accion#ejecutar(ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje, java.util.List)
	 */
	@Override
	public String ejecutar(Mensaje mensaje, Set<Parametro> parametros) {
		String retorno = "";
		
		// Aca tengo que obtener el listado de padrones
		List<String> padrones;
		Repositorio<Alumno> repositorioAlumno = new RepositorioAlumno();
		ValidadorPadronDeInscripto validadorPadron =  new ValidadorPadronDeInscripto();
		
		// Recorro la lista de padrones y verifico:
		// * que sean validos
		// * que existan en la materia en el cuatrimestre
		// * que NO pertenezcan a un grupo
		Iterator<String> it = padrones.iterator();
		while ( it.hasNext() ){
			String padron = (String)it.next();
			
			Parametro padronParametro = new Parametro();
			padronParametro.setNombre("PADRON");
			padronParametro.setValor(padron);
			if (validadorPadron.validar(padronParametro)){
				if (repositorioAlumno.obtener(padron) == null) {
					return "El usuario no esta anotado en el curso";
				}
			} else {
				retorno = "Uno de los padrones no es valido: " + padron;
				break;
			}
		}
		
		// Dar de alta si no hubo error
		if (retorno.isEmpty()){
			
		}
		
		
		System.out.println("Envio un mail!");
		return retorno;
	}

	@Override
	public String puedeEjecutar(Mensaje mesg, Set<Parametro> parametrosParaAccion) {
		String retorno = "";
		List<String> pathAdjuntos = mesg.getPathAdjunto();
		if (pathAdjuntos.isEmpty()){
			retorno = "El mensaje no tiene un archivo adjunto";
		}
		
		return retorno;
	}

}
