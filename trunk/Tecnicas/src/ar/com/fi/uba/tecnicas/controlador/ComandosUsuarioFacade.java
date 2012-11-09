package ar.com.fi.uba.tecnicas.controlador;

import java.util.Iterator;
import ar.com.fi.uba.tecnicas.modelo.FabricaComponente;
import ar.com.fi.uba.tecnicas.modelo.entidades.Cable;
import ar.com.fi.uba.tecnicas.modelo.entidades.Circuito;
import ar.com.fi.uba.tecnicas.modelo.entidades.Componente;
import ar.com.fi.uba.tecnicas.modelo.entidades.ComponenteBasico;
import ar.com.fi.uba.tecnicas.modelo.entidades.Demultiplexor;
import ar.com.fi.uba.tecnicas.persistencia.Repositorio;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioComponentes;
import ar.com.fi.uba.tecnicas.vista.InterfazUsuario;

/**
 * Facade con los metodos que puede ejecutar el usuario.
 * @author cristian
 *
 */
public class ComandosUsuarioFacade {
	
	private Circuito circuitoEnEdicion = null;
	private Repositorio<Componente> repo=new RepositorioComponentes();
	
	/**
	 * 
	 * @param invocador
	 * @return
	 */
	public String crearRepositorio(InterfazUsuario invocador){
		
		try {
			if (this.repo==null) {
				this.repo = new RepositorioComponentes();
				return "Repositorio creado correctamente";
			}
			else return "El repositorio ya esta creado";
			
		} catch (Exception e) {
			return "No se pudo crear el repositorio "+e.getMessage()+" "+e.getCause()+" "+e.getClass();
		}
	}
	
	/**
	 * Muestra todos los componentes del repositorio
	 * @param invocador
	 * @return
	 */
	public String mostrarRepositorio(InterfazUsuario invocador){
		try {
			String mensaje = "COMPONENTES\n";
			Iterator<Componente> it=repo.obtenerTodos(ComponenteBasico.class).iterator();
			while (it.hasNext()) {
				Componente componente1= (Componente) it.next();
				mensaje += "         -" + componente1.toString() + "\n";
			}
			
			mensaje +="\n\nDEMULTIPLEXORES\n";
			Iterator<Componente> it2=repo.obtenerTodos(Demultiplexor.class).iterator();
			while (it2.hasNext()) {
				Componente componente1= (Componente) it2.next();
				mensaje += "         -" + componente1.toString() + "\n";
			}
			
			mensaje +="\n\nCIRCUITOS\n";
			Iterator<Componente> it3=repo.obtenerTodos(Circuito.class).iterator();
			while (it3.hasNext()) {
				Componente componente1= (Componente) it3.next();
				mensaje += "         -" + componente1.toString() + "\n";
			}
			
			return mensaje;
		} catch (Exception e) {
			return "Error: "+e.getClass()+" "+e.getMessage()+" "+e.getStackTrace();
		}
	}
	
	
	public String limpiar(InterfazUsuario invocador){
		try {
			if (circuitoEnEdicion == null) {
				repo.vaciar();
				return "Se limpio el repositorio correctamente.";
			} else {
				return "No se puede limpiar el repositorio porque hay un circuito en edición.";
			}
		} catch (Exception e) {
			return "error: "+e.getMessage()+" "+e.getCause()+" "+e.getClass();
		}
	}
	
	
	/**
	 * Comando para crear un circuito y ponerlo en edicion automaticamente.
	 * @param invocador
	 * @param cantEntradas
	 * @param cantSalidas
	 * @param nombre
	 * @return
	 */
	public String crearCircuito(InterfazUsuario invocador){
		try {
			String cantEntradas=invocador.obtenerDatos("Ingrese la cantidad de entradas: ");
			String cantSalidas=invocador.obtenerDatos("Ingrese la cantidad de salidas: ");
			String nombre=invocador.obtenerDatos("Ingrese el nombre: ");
			circuitoEnEdicion=FabricaComponente.crearCircuito(Integer.parseInt(cantEntradas), Integer.parseInt(cantSalidas), nombre);
			
			return "Circuito creado y listo para edicion,\nCircuito ID: "+Integer.toString(circuitoEnEdicion.getId());
			
		} catch (EValidacion e) {
			return "Error: "+e.getMessage();
		}catch (NumberFormatException e) {
			return "La cantidad de entradas o salidas ingresada no es correcta";
		}
	}
	
	/**
	 * Comando que permite agregar un componente al circuito
	 * @param invocador
	 * @param nombre
	 * @return
	 */
	public String agregarComponente(InterfazUsuario invocador,String nombre){
		try {
			if (circuitoEnEdicion == null) return "No hay un circuito en edicion";
			if (this.circuitoEnEdicion.getNombre().equalsIgnoreCase(nombre)) return "No se puede agregar el mismo circuito que se esta editando";
			
			Componente unComponente = repo.obtener(nombre).clone();
			if (unComponente != null) {
				circuitoEnEdicion.addComponente(unComponente);
				return "Componente " + nombre + " agregado correctamente.(ID: "+ unComponente.getId() + ")";
			} else
				return "No se pudo entontrar un componente con ese nombre.";
		} catch (Exception e) {
			return "Error: "+e.getMessage()+" "+e.getCause()+" "+e.getClass();
		}
		
	}
	
	/**
	 * Comando para crear componente y agregarlo al repositorio
	 * @param invocador
	 * @param tipo
	 * @param nombre
	 * @param cantEntradas
	 * @param cantSalidas
	 * @return
	 */
	public String crearComponente(InterfazUsuario invocador){
		try {
			String nombre =invocador.obtenerDatos("Ingresar nombre: ");
			String tipo=invocador.obtenerDatos("Ingresar tipo (generico/demux): ");
			String cantEntradas=invocador.obtenerDatos("Cantidad de entradas: ");
			String cantSalidas=invocador.obtenerDatos("Cantidad de salidas: ");
			
			int cantEntradasInt=Integer.parseInt(cantEntradas);
			int cantSalidasInt=Integer.parseInt(cantSalidas);
			
			if (tipo.equalsIgnoreCase("GENERICO")) {
				ComponenteBasico unComponenteBasico = FabricaComponente.crearComponenteBasico(cantEntradasInt,cantSalidasInt, nombre);
				int i=0;
				
				while (i<Integer.parseInt(cantSalidas)) {
					try {
						unComponenteBasico.agregarFuncion(invocador.obtenerDatos("Ingrese Funcion para la SALIDA NRO " + i + ": "));
						i +=1;
					} catch (EValidacion e) {
						invocador.mensaje("Error: "+e.getMessage());			
					}
				}
				unComponenteBasico.validarFunciones();
				
				repo.agregar(unComponenteBasico);
				return "Se creo exitosamente el componente, con el nombre: "+unComponenteBasico.getNombre();
				
			}else if (tipo.equalsIgnoreCase("DEMUX")){
				Demultiplexor unDemux = FabricaComponente.crearDemultiplexor(cantEntradasInt,cantSalidasInt, nombre);
				int i=0;
				while (i<Integer.parseInt(cantSalidas)) {
					try {
						unDemux.agregarFuncion(invocador.obtenerDatos("Ingrese Funcion para la SALIDA NRO " + i + ": "));
						i +=1;
					} catch (EValidacion e) {
						invocador.mensaje("Error: "+e.getMessage());			
					}
				}
				unDemux.validarFunciones();
				
				repo.agregar(unDemux);
				return "Se creo exitosamente el Demultiplexor, con el nombre: "+unDemux.getNombre();
				
			}else return "Tipo de componente incorrecto.";
			
		} catch (NumberFormatException e) {
			return "La cantidad de entradas o salidas ingresada no es correcta";
		} catch (EValidacion e) {
			return "Error: "+e.getMessage();
		}catch (Exception e) {
			return "Error: "+e.getMessage()+e.getStackTrace();
		}
		
	}

	public String conectarComponentes(InterfazUsuario invocador){
		
		try {
			if (this.circuitoEnEdicion==null) return "No puede conectar componentes si no tiene un circuito creado";
			
			int iDComponenteSalidaInt =Integer.parseInt(invocador.obtenerDatos("Ingresar ID del componente salida: "));
			int nroSalidaInt=Integer.parseInt(invocador.obtenerDatos("Ingresar Nro. Salida: "));
			
			int iDComponenteEntradaInt =Integer.parseInt(invocador.obtenerDatos("Ingresar ID del componente entrada: "));
			int nroEntradaInt=Integer.parseInt(invocador.obtenerDatos("Ingresar Nro entrada: "));
			
			Componente componenteEntrada=circuitoEnEdicion.obtenerComponente(iDComponenteEntradaInt);
			Componente componenteSalida=circuitoEnEdicion.obtenerComponente(iDComponenteSalidaInt);
			
			componenteEntrada.isExisteEntrada(nroEntradaInt);
			componenteSalida.isExisteSalida(nroSalidaInt);
			
			componenteEntrada.conectar(nroEntradaInt, componenteSalida, nroSalidaInt);
			
			return "Conexion: Comp: "+componenteSalida.getId()+" Salida: "+nroSalidaInt+" ----> "+componenteEntrada.getId()+" Entrada: "+nroEntradaInt; 
			
		} catch (NumberFormatException e) {
			return "Formato numerico incorrecto";
		} catch (EValidacion e) {
			return "Error: "+e.getMessage();
		} catch (Exception e) {
			return "Error: "+e.getCause();
		}
	}
	
	public String conectarSalidaCircuito(InterfazUsuario invocador){
		
		try {
			if (this.circuitoEnEdicion==null) return "No puede conectar componentes si no tiene un circuito creado";
			
			int iDComponenteSalidaInt =Integer.parseInt(invocador.obtenerDatos("Ingresar ID del componente salida: "));
			int nroSalidaInt=Integer.parseInt(invocador.obtenerDatos("Ingresar Nro. salida componente: "));
			
			Componente componenteSalida=circuitoEnEdicion.obtenerComponente(iDComponenteSalidaInt);
			
			int nroSalidaCircuitoInt=Integer.parseInt(invocador.obtenerDatos("Ingresar Nro. salida circuito: "));
			
			componenteSalida.isExisteSalida(nroSalidaInt);
			
			this.circuitoEnEdicion.conectarSalida(nroSalidaCircuitoInt, componenteSalida, nroSalidaInt);
			
			return "Conexion exitosa, Componente: "+componenteSalida.getId()+" Salida: "+nroSalidaInt+" ----> Circuito salida: "+nroSalidaCircuitoInt+"\n"; 
			
		} catch (NumberFormatException e) {
			return "Formato numerico incorrecto";
		} catch (EValidacion e) {
			return "Error: "+e.getMessage();
		}catch (Exception e) {
			return "Error: "+e.getMessage()+" "+e.getCause()+" "+e.getClass();
		}
	}
	
	public String conectarEntradaCircuito(InterfazUsuario invocador){
		
		try {
			if (this.circuitoEnEdicion==null) return "No puede conectar componentes si no tiene un circuito creado";
			
			int nroEntradaCircuitoInt=Integer.parseInt(invocador.obtenerDatos("Ingresar Nro. entrada circuito: "));
			
			int iDComponenteEntradaInt =Integer.parseInt(invocador.obtenerDatos("Ingresar ID componente entrada: "));
			int nroEntradaInt=Integer.parseInt(invocador.obtenerDatos("Ingresar Nro. entrada componente: "));
			
			Componente componenteEntrada=circuitoEnEdicion.obtenerComponente(iDComponenteEntradaInt);
			
			componenteEntrada.isExisteEntrada(nroEntradaInt);
			
			this.circuitoEnEdicion.conectar(nroEntradaCircuitoInt, componenteEntrada, nroEntradaInt);
			
			
			return "Conexion exitosa, circuito entrada: "+nroEntradaCircuitoInt+" ----> Componente: "+componenteEntrada.getId()+" Entrada: "+nroEntradaInt; 
			
		} catch (NumberFormatException e) {
			return "Formato numerico incorrecto";
		} catch (EValidacion e) {
			return "Error: "+e.getMessage();
		}catch (Exception e) {
			return "Error: "+e.getMessage()+" "+e.getCause()+" "+e.getClass();
		}
	}

	/**
	 * Muestra los componentes del circuito en edicion;
	 * @param invocador
	 * @return
	 */
	public String mostrar(InterfazUsuario invocador){
		
		try {
			if (circuitoEnEdicion == null)return "No hay un circuito en edicion actualmente\n";
			
			String mensaje = "DETALLES DE CIRCUITO "+this.circuitoEnEdicion.getNombre()+"\n";
			if (this.circuitoEnEdicion.getListaEntradas().isEmpty()) {
				mensaje += "- No hay entradas conectadas\n";
			} else {
				mensaje += "\nEntradas conectadas con: \n";
				for (int i = 0; i < this.circuitoEnEdicion.getCantEntradas(); i++) {
					Cable unCable = (Cable) this.circuitoEnEdicion.getListaEntradas().get(i);
					if (unCable!=null)	mensaje += "       - E" +i+": "+ unCable.toString() + "\n";
					else mensaje += "       - E" +i+": Sin Conexión\n";
				}
			}
			
			if (this.circuitoEnEdicion.getListaSalidas().isEmpty()) {
				mensaje += "- No hay salidas conectadas\n";
			} else {
				mensaje += "\nSalidas conectadas con: \n";
				for (int i = 0; i < this.circuitoEnEdicion.getCantSalidas(); i++) {
					Cable unCable = (Cable) this.circuitoEnEdicion.getListaSalidas().get(i);
					if (unCable!=null)	mensaje += "       - S" +i+": "+ unCable.toString() + "\n";
					else mensaje += "       - S" +i+": Sin Conexión\n";
				}
			}
			mensaje += "\n- Lista de componentes\n";
			java.util.Iterator<Componente> it = circuitoEnEdicion.getListaComponentes().iterator();
			if (!it.hasNext()) return "Este Circuito no tiene componentes";
			while (it.hasNext()) {
				Componente componente = (Componente) it.next();
				mensaje += "  * ID: " + componente.getId() + " - "	+ componente.toString() + "\n";
				mensaje += "    Entradas conectadas con: \n";
				for (int i = 0; i < componente.getCantEntradas(); i++) {
					Cable unCable = (Cable) componente.getListaEntradas().get(i);
					if (unCable!=null)	mensaje += "       - E" +i+": "+ unCable.toString() + "\n";
					else mensaje += "       - E" +i+": Sin Conexión\n";
				}
			}
			return mensaje;
		} catch (Exception e) {
			return "Error: "+e.getCause();
		}
	}
	
	public String mostrarComponente(InterfazUsuario invocador,String nombre){
		
		Componente componente = repo.obtener(nombre);
		
		if (componente!=null) return componente.toString();
		else return "No se encontro un componente con ese nombre";
		
	}
	/**
	 * Cerrar el circuito en edicion
	 * @param invocador
	 * @return
	 */
	public String cerrar(InterfazUsuario invocador){
		
		this.circuitoEnEdicion=null;
		return "Circuito cerrado";
	}
	/**
	 * Guarda el circuito que se encuentra en modo edicion
	 * @param invocador
	 * @return
	 */
	public String guardar(InterfazUsuario invocador){
		try {
		if (circuitoEnEdicion==null) return "No tiene ningun circuito en edición, para guardar";
		else{
			try {
				repo.quitar(circuitoEnEdicion.getNombre());
			} catch (Exception e) {
				return "Error, no se pudo borrar el circuito";
			}
			repo.agregar(circuitoEnEdicion);
			return "Circuito guardado correctamente";
		}
			
		} catch (EValidacion e) {
			return "Error: "+e.getMessage();
		}catch (Exception e) {
			return "Nos se pudo guardar"+e.getMessage();
		}
	}
	/**
	 * Comando que permite abrir un circuito que estaba almacenado y lo pone en edicion. si ya habia uno en edicion da la opcion de guardarlo.
	 * 
	 * @param invocador
	 * @param nombre
	 * @return
	 */
	public String abrir(InterfazUsuario invocador,String nombre){
		
		try {
		Circuito circuito=(Circuito)repo.obtener(nombre, Circuito.class);
		
		if (circuito==null) return "No se pudo encontrar un circuito con ese nombre.";
		else {
			if (this.circuitoEnEdicion==null) {
				this.circuitoEnEdicion=circuito;
				return "El circuito "+this.circuitoEnEdicion.getNombre()+" se abrio correctamente";
			}
			else{
				String resp=invocador.obtenerDatos("Ya posee un circuito en edicion, desea guardarlo (S/N");
				if (resp.equalsIgnoreCase("s")){
					repo.agregar(this.circuitoEnEdicion);
					this.circuitoEnEdicion=circuito;
					return "El circuito "+this.circuitoEnEdicion.getNombre()+" se abrio correctamente";
				}else return "El circuito no se abrio";
			}
		}
			
		} catch (EValidacion e) {
			return "Error: "+e.getMessage();
		}catch (ClassCastException e){
			return "Error: No puede abrir un ComponenteBasico y un Demultiplexor";	
		} catch (Exception e) {
			return "Error: "+e.getMessage()+" "+e.getCause()+" "+e.getClass();
		} 
		
	}
	
	public String borrarComponente(InterfazUsuario invocador,String nombre){
		
		try {
			repo.quitar(nombre);
			return "Componente borrado correctamente";
		} catch (Exception e) {
			return "Error: No se puedo borrar el componente";
		}
	}
	
	
	public String borrarCircuito(InterfazUsuario invocador,String nombre){
		try {
			repo.quitar(nombre);
			return "Circuito borrado correctamente";
		} catch (Exception e) {
			return "Error: No se puedo borrar el circuito";
		}
		
	}
	
	public String sacarComponente(InterfazUsuario invocador,String iDComponente){
		try {
			
			if (circuitoEnEdicion==null) return "No tiene ningun circuito en edición, operacion incorrecta";
			
			int id=Integer.parseInt(iDComponente);
			
			Iterator<Componente> it =this.circuitoEnEdicion.getListaComponentes().iterator();
			while (it.hasNext()) {
				Componente componente = (Componente) it.next();
				if (componente.getId()==id) {
					this.circuitoEnEdicion.getListaComponentes().remove(componente);
					return "El componente se quito del circuito correctamente, tenga en cuenta restablecer las conexiones.";
				}
			}
			return "No se encontro el ID, en la lista de componentes del circuito";
		} catch (Exception e) {
			return "Error: No se puedo borrar el circuito";
		}
	}
	
	
	public String simular(InterfazUsuario invocador){
		try {
			if (circuitoEnEdicion==null) return "No tiene ningun circuito en edición, para guardar";
			if (circuitoEnEdicion.getListaComponentes().isEmpty()) return "Este circuito no tiene componentes";
			
			String respuesta1=invocador.obtenerDatos("Ingrese la cantidad de tiempos a simular: ");
			int cantTiempos=Integer.parseInt(respuesta1);
			String resultado="Resultado\n";
			for (int nroTiempo = 0; nroTiempo < cantTiempos; nroTiempo++) {
					for (int nroEntrada = 0; nroEntrada < this.circuitoEnEdicion.getCantEntradas(); nroEntrada++) {
						Cable unCable=(Cable)this.circuitoEnEdicion.getListaEntradas().get(nroEntrada);
						
						if (unCable==null) break;
						String respuesta=invocador.obtenerDatos("\n-T"+nroTiempo+": Ingrese el valor para la entrada "+nroEntrada+" (0/1): ");
						int valorEntrada=Integer.parseInt(respuesta);
						switch (valorEntrada) {
						case 0:
							unCable.setValor(false);
							break;
						case 1:
							unCable.setValor(true);
							break;
						default:
							return "Valor de la entrada incorrecta";
						}
					}
					this.circuitoEnEdicion.simular();
					
					for (int nroSalida = 0; nroSalida < this.circuitoEnEdicion.getListaSalidas().size(); nroSalida++) {
						if (this.circuitoEnEdicion.getListaValoresSalida().get(nroSalida)) resultado += "\n-T"+nroTiempo+" -> S"+nroSalida+":1\n";
						else resultado += "\n-T"+nroTiempo+" -> S"+nroSalida+": 0";
						
					}
					resultado += "\n";
			}
			return resultado;
		
		} catch (NumberFormatException e) {
			return "Formato numerico incorrecto";
		} catch (EValidacion e){
			return "Error: "+e.getMessage();
		} catch (Exception e) {
			return "Error: "+e.getMessage()+" "+e.getCause()+" "+e.getClass();
		}
	}
	
	/**
	 * help! I need somebody!
	 * @param invocador
	 * @return
	 */
	public String ayuda(InterfazUsuario invocador){
		String mensaje="LISTA DE COMANDOS\n" +
				"* crearComponente \n" +
				"	-Crea un componente con los datos solicitados, el tipo toma dos valores generico/demux y la cantidad de entradas numeros enteros\n\n" +
				"* crearCircuito\n" +
				"	-Crea un circuito con los datos solicitados y lo deja listo para su edicion.\n\n"+
				"* agregarComponente <NOMBRE>\n" +
				"	-Agrega un componente ya creado en el circuito que esta actulmente en edicion.\n\n"+
				"* conectarComponentes .\n" +
				"	-Conecta la salida del componente indicado con la entrada indicada.\n\n"+
				"* conectarEntradaCircuito .\n" +
				"	-Conecta la entrada del circuito en edicion con la entrada del conponente indicado.\n\n"+
				"* conectarSalidaCircuito  .\n" +
				"	-Conecta la salida indicada del circuito en edicion con la salida del componente\n\n"+
				"* mostrar \n" +
				"	-Muestra los componentes que contiene el circuito actualente en edicion\n\n"+
				"* limpiar \n" +
				"	- vacia el repositorio\n\n" +
				"* guardar \n " +
				"	- Guarda el circuito en edicion.\n\n"+
				"* mostrarRepositorio \n" +
				"   - Muestra los componentes existentes\n\n" +
				"* mostrarComponente <NOMBRE COMP>\n" +
				"	- Muestra un detalle del componente\n\n"+
				"* abrir <NOMBRE CIRCUITO>\n" +
				"	- Abre el circuito nombrado y lo deja listo para edición.\n\n" +
				"* cerrar\n" +
				"	- cierra el circuito actualmente en edicion\n\n" +
				"* simular\n " +
				"	- Ejecuta la simulacion del circuito actualmente en edicion\n\n"+ 
				"* guardar\n" +
				"	- Guarda el circuito actualmente en edicion y lo saca de edicion\n\n"+
				"* limpiar\n" +
				"	- Vacía el repositorio\n\n"+
				"* borrarComponente <NOMBRE>\n" +
				"	- Elimina del repositorio, el componente indicado por el nombre\n\n" +
				"* borrarCircuito <NOMBRE>\n" +
				"	- Elimina del repositorio, el circuito indicado por el nombre\n\n" +
				"* sacarComponente <ID COMP>\n" +
				"	- Remueve el componente del circuito actualmente en edición\n\n"+
				"\nFIN\n";
		return mensaje;
			
	}
	
}
