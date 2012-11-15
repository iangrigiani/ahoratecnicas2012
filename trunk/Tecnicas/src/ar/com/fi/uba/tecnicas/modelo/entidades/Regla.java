/**
 * 
 */
package ar.com.fi.uba.tecnicas.modelo.entidades;

import ar.com.fi.uba.tecnicas.controlador.comun.Constantes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import ar.com.fi.uba.tecnicas.controlador.validador.ValidadorParametro;
import ar.com.fi.uba.tecnicas.modelo.entidades.accion.Accion;
import java.lang.reflect.InvocationTargetException;

/**
 * La regla
 * @author ramiro
 */
public class Regla {

	private String nombre;
	private String asunto;
	private Map<Parametro, ValidadorParametro> parametros;
	private List<String> nombreAcciones;
	
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
		return mensaje.getAsunto().contains('['+asunto+']');
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

    public void procesar(Mensaje mesg) {
        System.out.println("Valido la regla " + getNombre() + " con asunto: " + mesg.getAsunto());
        //TODO: Generar Parametros
        // mesg.getAsunto() hay que hacer una expresion regular luego de [asunto] y tomar todos los parametros divididos por '-'
        //TODO: ValidarParametros
        List<Accion> accionesDeReglas = getAcciones();
        for (Accion accion : accionesDeReglas) {
                accion.ejecutar(mesg, getParametrosParaAccion());
        }
    }
    
    protected List<Accion> getAcciones() {
            List<Accion> acciones = new ArrayList<Accion>(nombreAcciones.size());
            for (String nombreAccion : nombreAcciones) {
                    try {
                            Class theClass = Class.forName(Constantes.PAQUETE_INTERFAZ_ACCION + "." + nombreAccion);
                            acciones.add((Accion) theClass.getConstructors()[0].newInstance());
                    } catch (IllegalArgumentException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    } catch (SecurityException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    } catch (InstantiationException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    } catch (IllegalAccessException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    } catch (InvocationTargetException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    }
            }
            return acciones;
    }
}
