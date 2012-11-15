package ar.com.fi.uba.tecnicas.modelo.entidades;

import java.util.List;

public class Mensaje {
	
	private String textoPlano;
	private String de;
	private List <String> para;
	private String asunto;
	//private String copia; AL PARECER NO LOS ENCONTRE EN LA CLASE 
	//private String copiaOculta; AL PARECER NO LOS ENCONTRE EN LA CLASE
	
	private List<String> pathAdjunto;

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
	public List <String> getPara() {
		return para;
	}

	/**
	 * @param para the para to set
	 */
	public void setPara(List <String> para) {
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

//	/**
//	 * @return the copia
//	 */
//	public String getCopia() {
//		return copia;
//	}
//
//	/**
//	 * @param copia the copia to set
//	 */
//	public void setCopia(String copia) {
//		this.copia = copia;
//	}
//
//	/**
//	 * @return the copiaOculta
//	 */
//	public String getCopiaOculta() {
//		return copiaOculta;
//	}
//
//	/**
//	 * @param copiaOculta the copiaOculta to set
//	 */
//	public void setCopiaOculta(String copiaOculta) {
//		this.copiaOculta = copiaOculta;
//	}

	/**
	 * @return the pahtAdjunto
	 */
	public List<String> getPathAdjunto() {
		return pathAdjunto;
	}

	/**
	 * @param pathAdjunto the pathAdjunto to set
	 */
	public void setPahtAdjunto(List<String> pathAdjunto) {
		this.pathAdjunto = pathAdjunto;
	}
	
	public void agregarPathAdjunto(String newPath){
		this.pathAdjunto.add(newPath); 
	}
	
	public void agregarTextoPlano(String newTexto){
		this.textoPlano+=newTexto;
	}
	
	public void agregarPara(String newPara){
		this.para.add(newPara);
	}

}
