package ar.com.fi.uba.tecnicas.controlador.cadena;

/**
 * Es el ultimo eslabon de la cadena
 * @author ramiro
 *
 */
public class EslabonFinal implements Eslabon {

	private Eslabon eslavon;
	private String regex;
	private Mediador mediador;
	
	public EslabonFinal(String regex) {
		setRegex(regex);
	}
	
	/**
	 * En este metodo realizo el labor que tiene que hacer el eslavon y
	 * sino tengo nada para hacer entonces llamo al otro eslavon
	 * @throws Exception 
	 */
	@Override
	public void sendToEslabon(String mesg) throws Exception {
//		System.out.println("Eslavon Final: " + mesg);
		
		//String nuevoMesg = ParserUtils.parsearRegexPorMatch(mesg, getRegex(), "");
		
//		System.out.println("Eslavon Final: " + nuevoMesg);
		
//		if (nuevoMesg.equals(mesg)) {
//			if (eslavon != null) {
//				eslavon.sendToEslabon(mesg);
//				return;
//			} else {
//
//				getMediador().setFuncionAParsear(null);
//				
//			}
//		} else {
//			getMediador().setFuncionAParsear(nuevoMesg);
//			if (getMediador().getGenerarEvaluable()) {
//				String operacion = ParserUtils.getGrupoMacheado();
//				Evaluable regla1 = null;
//				List<String> entradasOperacion = ParserUtils.parsearEntradas(operacion, Constantes.REGEX_ENTRADAS_REALES, "");
//				for (String nroEntradaEnFuncion : entradasOperacion) {
//					if (Integer.parseInt(nroEntradaEnFuncion, 10) > Constantes.MAXIMO_NRO_ENTRADAS) {
//						regla1 = getMediador().getEvaluablesIntermedios().get(Integer.parseInt(nroEntradaEnFuncion, 10));
//					} else {
//						regla1 = new ValidarEntradaEncendida(Integer.parseInt(nroEntradaEnFuncion, 10));
//					}
//				}
//				getMediador().getEvaluablesIntermedios().put(getMediador().getCountEvaluables(), regla1);
//			}
//		}
	}

	@Override
	public Eslabon getEslabon() {
		return eslavon;
	}

	@Override
	public void addEslabon(Eslabon c) {
		eslavon = c;
	}
	
	public void setEslavon(Eslabon eslavon) {
		this.eslavon = eslavon;
	}

	public String getRegex() {
		return regex;
	}

	@Override
	public void setRegex(String regex) {
		this.regex = regex;
	}

	public Mediador getMediador() {
		return mediador;
	}

	public void setMediador(Mediador mediador) {
		this.mediador = mediador;
	}

}
