package ar.com.fi.uba.tecnicas.controlador.cadena;

/**
 * Se encarga de crear la cadena de reglas
 * @author ramiro
 */
public class CadenaFactory {
	
	public static Eslabon crearCadenaReglas() {
		Eslabon inicioCadena = null;
		
		return inicioCadena;
	}
	
	/**
	 * Vincula un eslabon con el que le sigue
	 * @param nuevoEslabon Eslabon a agregar
	 * @param extremoDeCadena Eslabon existente
	 */
	public static void agregarEslabon(Eslabon nuevoEslabon, Eslabon extremoDeCadena){
		
		Eslabon actual = extremoDeCadena;
		
		while (actual.getEslabon() != null){
			actual = actual.getEslabon();
		}
		nuevoEslabon.setMediador(actual.getMediador());
		actual.setEslabon(nuevoEslabon);
	}
	
}
