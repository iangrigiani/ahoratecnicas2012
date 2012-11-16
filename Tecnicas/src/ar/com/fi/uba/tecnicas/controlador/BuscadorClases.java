/**
 * 
 */
package ar.com.fi.uba.tecnicas.controlador;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import ar.com.fi.uba.tecnicas.controlador.comun.Constantes;
import ar.com.fi.uba.tecnicas.modelo.entidades.accion.Accion;

/**
 * @author ramiro
 *
 */
public class BuscadorClases {

	public static List<Object> buscarClasesImplementanInterfaz(String interfas, String directorio, String paquete) {
		List<String> nombreClases = BuscadorClases.buscarNombreClasesImplementanInterfaz(interfas, directorio, paquete);
		List<Object> clases = new ArrayList<Object>(nombreClases.size());
		for (String nombreClase : nombreClases) {
			Object instancia = getInstancia(paquete + "." +nombreClase);
			if (instancia != null) {
				clases.add(instancia);
			}
		}
		return clases;
	}
	
	@SuppressWarnings("rawtypes")
	public static List<String> buscarNombreClasesImplementanInterfaz(String interfas, String directorio, String paquete) {
		File currentDirectory = new File(directorio);

        File[] children = currentDirectory.listFiles();

        if (children == null || children.length == 0) {
            return null;
        }

        List<String> nombresClases = new ArrayList<String>();
        // check for classfiles
        for (int i = 0; i < children.length; i++) {
            File child = children[i];
            if (!child.isDirectory()) {
               if (child.getName().endsWith(".class")) {
                    String className = child.getName().replaceAll(".class", "");
                    Class theClass;
                	try {
						theClass = Class.forName(paquete + "."+className);
						  if (!theClass.isInterface()) {
		                        Class[] interfaces = theClass.getInterfaces();
		                        for (Class class1 : interfaces) {
									if (class1.getName().contains(interfas)) {
										nombresClases.add(className);
									}
								}
	                        }
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
               }
            }
        }
		return nombresClases;
	}
	
	  
    public static List<Accion> getAcciones(List<String> nombreAcciones) {
            List<Accion> acciones = new ArrayList<Accion>(nombreAcciones.size());
            for (String nombreAccion : nombreAcciones) {
                    Object instancia = getInstancia(Constantes.PAQUETE_INTERFAZ_ACCION + "." + nombreAccion);
                    if (instancia != null) {
                    	acciones.add((Accion) instancia);
                    }
            }
            return acciones;
    }

	@SuppressWarnings("rawtypes")
	private static Object getInstancia(String nombreClase) {
		try {
		       Class theClass = Class.forName(nombreClase);
		       return theClass.getConstructors()[0].newInstance();
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
		return null;
	}
}
