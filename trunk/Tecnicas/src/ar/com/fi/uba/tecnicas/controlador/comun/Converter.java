/**
 * 
 */
package ar.com.fi.uba.tecnicas.controlador.comun;

import java.util.ArrayList;
import java.util.List;

import ar.com.fi.uba.tecnicas.controlador.validador.ValidadorParametro;
import ar.com.fi.uba.tecnicas.modelo.entidades.accion.Accion;

/**
 * @author ramiro
 *
 */
public class Converter {

	public static List<ValidadorParametro> getValidadoresParametros(List<Object> lista) {
		List<ValidadorParametro> validadores = new ArrayList<ValidadorParametro>();
		for (Object object : lista) {
			validadores.add((ValidadorParametro) object);
		}
		return validadores;
	}
	
	public static List<Accion> getAcciones(List<Object> lista) {
		List<Accion> acciones = new ArrayList<Accion>();
		for (Object object : lista) {
			acciones.add((Accion) object);
		}
		return acciones;
	}
}
