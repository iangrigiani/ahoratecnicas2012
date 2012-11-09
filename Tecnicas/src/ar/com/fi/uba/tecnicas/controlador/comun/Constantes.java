package ar.com.fi.uba.tecnicas.controlador.comun;

public class Constantes {

	public static final String REGEX_ESLAVON_FINAL = "(^(E)(\\d)+$|(^\\()(E)(\\d)+(\\))$)";
	public static final String REGEX_OR = "((E)(\\d)+OR(E)(\\d)+|(\\()(E)(\\d)+OR(E)(\\d)+(\\)))";
	public static final String REGEX_AND = "((E)(\\d)+AND(E)(\\d)+|(\\()(E)(\\d)+AND(E)(\\d)+(\\)))";
	public static final String REGEX_NOT = "(NOT(E)(\\d)+|NOT(\\()(E)(\\d)+(\\)))";
	public static final String REGEX_XOR = "((E)(\\d)+XOR(E)(\\d)+|(\\()(E)(\\d)+XOR(E)(\\d)+(\\)))";
	public static final String REGEX_PARENTESIS_EXTRA = "(\\()(E)(\\d)+(\\))";

	//Regular expresion en que toda E este acompañada de un numero regexp -nocase -all -line -- {( |^|\()(E|e)(\d)*( |$|\))} string match v1 v2 v3 v4
	public static final String REGEX_ENTRADAS_VALIDAS = "( |^|\\()(E|e)(\\d)+( |$|\\))";
	public static final String REGEX_ENTRADAS_REALES= "(E|e)(\\d)+";
	public static final String STRING_EMPTY = "";
	
	public static final Integer MAXIMO_NRO_ENTRADAS = 999;
	
}
