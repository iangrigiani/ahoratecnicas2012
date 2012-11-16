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

import ar.com.fi.uba.tecnicas.controlador.BuscadorClases;
import ar.com.fi.uba.tecnicas.controlador.validador.ValidadorParametro;
import ar.com.fi.uba.tecnicas.modelo.entidades.accion.Accion;
import java.util.LinkedList;
import java.util.regex.Pattern;

/**
 * La regla
 * @author ramiro
 */
public class Regla {

	private String nombre;
	private String asunto;
	private Map<Parametro, ValidadorParametro> parametros;
	private List<String> nombreAcciones;
	
	/**
	 * Valida que el filtro de la regla concuerde con el asunto del mensaje
	 * @param mensaje Mensaje a procesar
	 * @return True si valida y false sino.
	 */
	public Boolean validar(Mensaje mensaje) {
		return validarAsunto(mensaje);
	}

	/**
	 * Parsea los parametros del mensaje, los valida y ejecuta las acciones que la regla tiene configurada
	 * para dicho mensaje.
	 * @param mensaje El mensaje a procesar.
	 */
	public String procesar(Mensaje mensaje) {
	    String error = "";
            System.out.println("Valido la regla " + getNombre() + " con asunto: " + mensaje.getAsunto());
	    
	    error = parsearParametros(mensaje);
            if (!error.isEmpty())
                return error;
	    
            error = validarParametros();
            if (!error.isEmpty())
                return error;

            List<Accion> accionesDeReglas = BuscadorClases.getAcciones(nombreAcciones);
	    for (Accion accion : accionesDeReglas) {
	    	error = accion.puedeEjecutar(mensaje, getParametrosParaAccion());
                if (!error.isEmpty())
                    return error;
	    }
            for (Accion accion : accionesDeReglas) {
	    	accion.ejecutar(mensaje, getParametrosParaAccion());
	    }
            return error;
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
		
		if (this.nombreAcciones == null) {
			this.nombreAcciones = new ArrayList<String>();
		}
		
		if (!this.nombreAcciones.contains(accion)) {
			this.nombreAcciones.add(accion);
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
	
	private boolean validarAsunto(Mensaje mensaje) {
		return mensaje.getAsunto().contains('['+asunto+']');
	}

	private String parsearParametros(Mensaje mensaje) {

                String[] valoresParametros = obtenerParametrosDelAsunto(mensaje.getAsunto());
                if(valoresParametros.length != parametros.size())
                    return "Cantidad de parametros incorrecto.";
                int i = 0;
                for (Entry<Parametro, ValidadorParametro> parParametroValidador : parametros.entrySet()) {
			parParametroValidador.getKey().setValor(valoresParametros[i]);
                        i++;
		}
                return "";
	}
	
	private String validarParametros() {
		String error = "";
		for (Entry<Parametro, ValidadorParametro> parParametroValidador : parametros.entrySet()) {
			/*error = */parParametroValidador.getValue().validar(parParametroValidador.getKey());
                        if(!error.isEmpty())
                            return error;
		}
		return error;
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
	 * @return the nombreAcciones
	 */
	public List<String> getNombreAcciones() {
		return nombreAcciones;
	}

	/**
	 * @param nombreAcciones the nombreAcciones to set
	 */
	public void setAcciones(List<String> acciones) {
		this.nombreAcciones = acciones;
	}

        String[] obtenerParametrosDelAsunto(String asuntoMensaje) {
            String formatoAsunto = '['+asunto+']';
            String listaDeparametros = asuntoMensaje.substring(asuntoMensaje.lastIndexOf(formatoAsunto)+formatoAsunto.length());
            String[] valoresParametros = Pattern.compile("\\s*-\\s*").split(listaDeparametros);
            while(valoresParametros[0].startsWith(" ")){
                valoresParametros[0].substring(1);
            }
            return valoresParametros;
        }

}