package ar.com.fi.uba.tecnicas.controlador.cadena;

/**
 * Implementacion del patron Mediador junto con la cadena de responsabilidades
 * @author ramiro
 *
 */
public class Mediador {
	
	private Eslabon extremoCadena;

	public Mediador() {	
//		this.extremoCadena = new EslabonParentesisRepetidos(Constantes.REGEX_PARENTESIS_EXTRA);
//		this.extremoCadena.setMediador(this);
//		CadenaFactory.agregarEslabon(new EslabonNot(Constantes.REGEX_NOT), this.extremoCadena);
//		CadenaFactory.agregarEslabon(new EslabonAnd(Constantes.REGEX_AND), this.extremoCadena);
//		CadenaFactory.agregarEslabon(new EslabonXor(Constantes.REGEX_XOR), this.extremoCadena);
//		CadenaFactory.agregarEslabon(new EslabonOr(Constantes.REGEX_OR), this.extremoCadena);
//		CadenaFactory.agregarEslabon(new EslabonFinal(Constantes.REGEX_ESLAVON_FINAL), this.extremoCadena);
//		
	}

	
    public Eslabon getEslavon() {
		return extremoCadena;
	}
	public void setEslavon(Eslabon eslavon) {
		this.extremoCadena = eslavon;
	}

}
