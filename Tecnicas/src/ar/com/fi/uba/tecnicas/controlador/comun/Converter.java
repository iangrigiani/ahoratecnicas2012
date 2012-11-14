/**
 * 
 */
package ar.com.fi.uba.tecnicas.controlador.comun;

import java.util.ArrayList;
import java.util.List;

import ar.com.fi.uba.tecnicas.controlador.validador.ValidadorParametro;

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
}
