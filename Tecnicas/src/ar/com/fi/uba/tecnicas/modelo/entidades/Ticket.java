/**
 * 
 */
package ar.com.fi.uba.tecnicas.modelo.entidades;

import java.util.List;

/**
 * @author ramsal
 *
 */
public class Ticket {

	private String id;
	private Boolean privada;
	private String titulo;
	private String estado;
	private List<Mensaje> mensajes;
	/**
	 * @return the privada
	 */
	public Boolean getPrivada() {
		return privada;
	}
	/**
	 * @param privada the privada to set
	 */
	public void setPrivada(Boolean privada) {
		this.privada = privada;
	}
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return the mensajes
	 */
	public List<Mensaje> getMensajes() {
		return mensajes;
	}
	/**
	 * @param mensajes the mensajes to set
	 */
	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	/* (non-Javadoc)
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
		Ticket other = (Ticket) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
