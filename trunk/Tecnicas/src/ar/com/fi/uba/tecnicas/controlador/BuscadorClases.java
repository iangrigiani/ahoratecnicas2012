/**
 * 
 */
package ar.com.fi.uba.tecnicas.controlador;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ramiro
 *
 */
public class BuscadorClases {

	public static List<Object> buscarClasesImplementanInterfaz(String interfas, String directorio, String paquete) {
		List<String> nombreClases = BuscadorClases.buscarNombreClasesImplementanInterfaz(interfas, directorio, paquete);
		List<Object> clases = new ArrayList<Object>(nombreClases.size());
		for (String nombreClase : nombreClases) {
			try {
				Class theClass = Class.forName(paquete + "." +nombreClase);
				clases.add(theClass.getConstructors()[0].newInstance());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			}
		}
		return clases;
	}
	
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
}
