package ar.com.fi.uba.tecnicas.controlador.cadena;

public class CadenaFactory {
	
	public static void agregarEslabon(Eslabon nuevoEslabon, Eslabon extremoDeCadena){
		
		Eslabon actual = extremoDeCadena;
		
		while (actual.getEslabon() != null){
			actual = actual.getEslabon();
		}
		nuevoEslabon.setMediador(actual.getMediador());
		actual.addEslabon(nuevoEslabon);
	}
	
}
