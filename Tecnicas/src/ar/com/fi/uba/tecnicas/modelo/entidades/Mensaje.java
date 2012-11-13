package ar.com.fi.uba.tecnicas.modelo.entidades;

import java.util.List;

public class Mensaje {
	
	private String textoPlano;
	private String de;
	private String para;
	private String asunto;
	private String copia;
	private String copiaOculta;
	
	private List<String> pahtAdjunto;

	/**
	 * @return the textoPlano
	 */
	public String getTextoPlano() {
		return textoPlano;
	}

	/**
	 * @param textoPlano the textoPlano to set
	 */
	public void setTextoPlano(String textoPlano) {
		this.textoPlano = textoPlano;
	}

	/**
	 * @return the de
	 */
	public String getDe() {
		return de;
	}

	/**
	 * @param de the de to set
	 */
	public void setDe(String de) {
		this.de = de;
	}

	/**
	 * @return the para
	 */
	public String getPara() {
		return para;
	}

	/**
	 * @param para the para to set
	 */
	public void setPara(String para) {
		this.para = para;
	}

	/**
	 * @return the asunto
	 */
	public String getAsunto() {
		return asunto;
	}

	/**
	 * @param asunto the asunto to set
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	/**
	 * @return the copia
	 */
	public String getCopia() {
		return copia;
	}

	/**
	 * @param copia the copia to set
	 */
	public void setCopia(String copia) {
		this.copia = copia;
	}

	/**
	 * @return the copiaOculta
	 */
	public String getCopiaOculta() {
		return copiaOculta;
	}

	/**
	 * @param copiaOculta the copiaOculta to set
	 */
	public void setCopiaOculta(String copiaOculta) {
		this.copiaOculta = copiaOculta;
	}

	/**
	 * @return the pahtAdjunto
	 */
	public List<String> getPahtAdjunto() {
		return pahtAdjunto;
	}

	/**
	 * @param pahtAdjunto the pahtAdjunto to set
	 */
	public void setPahtAdjunto(List<String> pahtAdjunto) {
		this.pahtAdjunto = pahtAdjunto;
	}

}
