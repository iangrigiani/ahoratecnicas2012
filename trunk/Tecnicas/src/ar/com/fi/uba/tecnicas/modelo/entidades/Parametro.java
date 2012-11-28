package ar.com.fi.uba.tecnicas.modelo.entidades;

import ar.com.fi.uba.tecnicas.controlador.validador.ValidadorParametro;

/**
 * Representa a un parametro del asunto de un mensaje
 * @author ramiro
 */
public class Parametro {
	
	private String valor;
	private ValidadorParametro validador;

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
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((validador.getDescripcion() == null) ? 0 : validador.getDescripcion().hashCode());
		return result;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parametro other = (Parametro) obj;
		if (validador.getDescripcion() == null) {
			if (other.validador.getDescripcion() != null)
				return false;
		} else if (!validador.getDescripcion().equals(other.validador.getDescripcion()))
			return false;
		return true;
	}
	/**
	 * @return the validador
	 */
	public ValidadorParametro getValidador() {
		return validador;
	}
	/**
	 * @param validador the validador to set
	 */
	public void setValidador(ValidadorParametro validador) {
		this.validador = validador;
	}
	
	/**
	 * El parametro se autovalida
	 * @return
	 */
	public boolean validar() {
		return this.validador.validar(this);
	}
	
}
