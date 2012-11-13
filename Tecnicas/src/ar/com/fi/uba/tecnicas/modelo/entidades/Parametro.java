package ar.com.fi.uba.tecnicas.modelo.entidades;

/**
 * Representa a un parametro del asunto de un mensaje
 * @author ramiro
 */
public class Parametro {
	
	private String nombre;
	private String valor;
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}
	/**
	 * @param valor the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
