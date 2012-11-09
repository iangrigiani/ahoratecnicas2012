package ar.com.fi.uba.tecnicas.controlador.cadena;

import java.util.HashMap;
import java.util.Map;

import ar.com.fi.uba.tecnicas.controlador.comun.Constantes;
import ar.com.fi.uba.tecnicas.modelo.evaluable.Evaluable;


public class Mediador {
	
	private Eslabon extremoCadena;
	private Boolean generarEvaluable;
	private String funcionAParsear;
	private Map<Integer, Evaluable> evaluablesIntermedios = new HashMap<Integer, Evaluable>();
	private Integer countEvaluables = Constantes.MAXIMO_NRO_ENTRADAS + 1;

	public Mediador() {
//		eslabon = new EslabonParentesisRepetidos(Constantes.REGEX_PARENTESIS_EXTRA);  
//		eslavon.addEslabon(new EslabonNot(Constantes.REGEX_NOT));
//		eslavon.getEslabon().addEslabon(new EslabonAnd(Constantes.REGEX_AND));
//		eslavon.getEslabon().getEslabon().addEslabon(new EslabonXor(Constantes.REGEX_XOR));
//		eslavon.getEslabon().getEslabon().getEslabon().addEslabon(new EslabonXor(Constantes.REGEX_XOR));
//		eslavon.getEslabon().getEslabon().getEslabon().addEslabon(new EslabonOr(Constantes.REGEX_OR));
//		eslavon.getEslabon().getEslabon().getEslabon().getEslabon().addEslabon(new EslabonFinal(Constantes.REGEX_ESLAVON_FINAL));
	
		this.extremoCadena = new EslabonParentesisRepetidos(Constantes.REGEX_PARENTESIS_EXTRA);
		this.extremoCadena.setMediador(this);
		CadenaFactory.agregarEslabon(new EslabonNot(Constantes.REGEX_NOT), this.extremoCadena);
		CadenaFactory.agregarEslabon(new EslabonAnd(Constantes.REGEX_AND), this.extremoCadena);
		CadenaFactory.agregarEslabon(new EslabonXor(Constantes.REGEX_XOR), this.extremoCadena);
		CadenaFactory.agregarEslabon(new EslabonOr(Constantes.REGEX_OR), this.extremoCadena);
		CadenaFactory.agregarEslabon(new EslabonFinal(Constantes.REGEX_ESLAVON_FINAL), this.extremoCadena);
		
	}

	/**
	 * el mediador Ejecuta el primer eslavon de la cadena para que despues
	 * se recorra la cadena hasta que algun eslabon realice algo
	 * @param funcionLogica
	 * @return
	 * @throws Exception 
	 */
	public String validarFuncionLogica(String funcionLogica) throws Exception {
		if (funcionLogica.isEmpty()) {
			return null;
		}
		funcionAParsear = funcionLogica;
//		System.out.println("String Entrante: " + funcionAParsear);
		while (funcionAParsear != null && !funcionAParsear.isEmpty()) {
			extremoCadena.sendToEslabon(funcionAParsear);	
			//System.out.println("String Siguiente: " + funcionAParsear);	
		}
		
		if (funcionAParsear != null && funcionAParsear.isEmpty()) {
			//Significa que esta bien formada
			return funcionAParsear;
		} else {
			//Me dio un error en el ultimo eslavon
			return null;
		}
	}
		

	public Evaluable generarFuncionLogicaEvaluable(String funcionLogica) throws Exception {
		if (funcionLogica.isEmpty()) {
			return null;
		}
		funcionAParsear = funcionLogica;
//		System.out.println("String Entrante: " + funcionAParsear);
		while (funcionAParsear != null && !funcionAParsear.isEmpty()) {
			extremoCadena.sendToEslabon(funcionAParsear);
			countEvaluables++;
			//System.out.println("String Siguiente: " + funcionAParsear);	
		}
		return evaluablesIntermedios.get(countEvaluables - 1);
	}
    public Eslabon getEslavon() {
		return extremoCadena;
	}
	public void setEslavon(Eslabon eslavon) {
		this.extremoCadena = eslavon;
	}

	public Boolean getGenerarEvaluable() {
		return generarEvaluable;
	}

	public void setGenerarEvaluable(Boolean generarEvaluable) {
		this.generarEvaluable = generarEvaluable;
	}

	public String getFuncionAParsear() {
		return funcionAParsear;
	}

	public void setFuncionAParsear(String funcionAParsear) {
		this.funcionAParsear = funcionAParsear;
	}

	public Map<Integer, Evaluable> getEvaluablesIntermedios() {
		return evaluablesIntermedios;
	}

	public void setEvaluablesIntermedios(
			Map<Integer, Evaluable> evaluablesIntermedios) {
		this.evaluablesIntermedios = evaluablesIntermedios;
	}

	public Integer getCountEvaluables() {
		return countEvaluables;
	}

	public void setCountEvaluables(Integer countEvaluables) {
		this.countEvaluables = countEvaluables;
	}

}
