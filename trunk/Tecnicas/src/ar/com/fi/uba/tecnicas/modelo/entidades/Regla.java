/**
 * 
 */
package ar.com.fi.uba.tecnicas.modelo.entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import ar.com.fi.uba.tecnicas.controlador.validador.ValidadorParametro;

/**
 * La regla
 * @author ramiro
 */
public class Regla {

	private String nombre;
	private String asunto;
	private Map<Parametro, ValidadorParametro> parametros;
	private List<String> acciones;
	
	public Boolean validar(Mensaje mensaje) {
		Boolean valida = Boolean.TRUE;
		valida = valida && validarAsunto(mensaje);
		if (!valida) {
			return valida;
		}
		parsearParametros();
		valida = valida && validarParametros();
		return valida;
	}

	private boolean validarAsunto(Mensaje mensaje) {
		return mensaje.getAsunto().contains(asunto);
	}

	private void parsearParametros() {
		for (Entry<Parametro, ValidadorParametro> parParametroValidador : parametros.entrySet()) {
			//le hardcodeo un valor hasta que hagamos el parser de los parametros
			parParametroValidador.getKey().setValor("83350");
		}
	}
	
	private boolean validarParametros() {
		Boolean ret = Boolean.TRUE;
		for (Entry<Parametro, ValidadorParametro> parParametroValidador : parametros.entrySet()) {
			parParametroValidador.getValue().validar(parParametroValidador.getKey());
		}
		return ret;
	}	
	
	public List<String> getParametros(Mensaje mensaje) {
		return null;
	}
	
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
	 * @return the parametros
	 */
	public Map<Parametro, ValidadorParametro> getParametros() {
		return parametros;
	}
	/**
	 * @param parametros the parametros to set
	 */
	public void setParametros(Map<Parametro, ValidadorParametro> parametros) {
		this.parametros = parametros;
	}
	
	/**
	 * @return the parametros
	 */
	public Set<Parametro> getParametrosParaAccion() {
		return parametros.keySet();
	}

	/**
	 * @return the acciones
	 */
	public List<String> getAcciones() {
		return acciones;
	}

	/**
	 * @param acciones the acciones to set
	 */
	public void setAcciones(List<String> acciones) {
		this.acciones = acciones;
	}

	/**
	 * Agrego un parametro pero primero valido que no se repita
	 * @param parametro Parametro a agregar
	 * @param validador Validador vinculado al parametro
	 * @return True se pudo agregar o false si el parametro ya existe
	 */
	public Boolean addParametro(Parametro parametro, ValidadorParametro validador) {
		Boolean ret = Boolean.FALSE;
		
		if (this.parametros == null) {
			this.parametros = new HashMap<Parametro, ValidadorParametro>();
		}
		
		if (!this.parametros.containsKey(parametro)) {
			this.parametros.put(parametro, validador);
			ret = Boolean.TRUE;
		}
		return ret;
	}
	
	/**
	 * Agrego una accion pero primero valido que no se repita
	 * @param accion Accion a agregar
	 * @return True se pudo agregar o false si el parametro ya existe
	 */
	public Boolean addAccion(String accion) {
		Boolean ret = Boolean.FALSE;
		
		if (this.acciones == null) {
			this.acciones = new ArrayList<String>();
		}
		
		if (!this.acciones.contains(accion)) {
			this.acciones.add(accion);
			ret = Boolean.TRUE;
		}
		return ret;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Regla other = (Regla) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
}
