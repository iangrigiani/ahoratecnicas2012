package ar.com.fi.uba.tecnicas.controlador.cadena;

import java.util.List;

import ar.com.fi.uba.tecnicas.modelo.entidades.Regla;

/**
 * Se encarga de crear la cadena de reglas
 * @author ramiro
 */
public class CadenaFactory {
	
	public static Eslabon crearCadenaReglas(List<Regla> reglas, Mediador mediador) {
		Eslabon inicioCadena = null;
		Eslabon eslabonActual = null;
		for (Regla regla : reglas) {
			eslabonActual = new EslabonComun();
			eslabonActual.setRegla(regla);
			eslabonActual.setMediador(mediador);
			if (inicioCadena == null) {
				inicioCadena = eslabonActual;
			} else {
				CadenaFactory.agregarEslabon(eslabonActual, inicioCadena);
			}
		}
		CadenaFactory.agregarEslabon(new EslabonFinal(), inicioCadena);
		return inicioCadena;
	}
	
	/**
	 * Vincula un eslabon con el que le sigue
	 * @param nuevoEslabon Eslabon a agregar
	 * @param extremoDeCadena Eslabon existente
	 */
	private static void agregarEslabon(Eslabon nuevoEslabon, Eslabon extremoDeCadena){
		
		Eslabon actual = extremoDeCadena;
		
		while (actual.getEslabon() != null){
			actual = actual.getEslabon();
		}
		actual.setEslabon(nuevoEslabon);
	}
	
}
