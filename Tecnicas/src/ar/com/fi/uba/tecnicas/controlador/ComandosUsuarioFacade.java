package ar.com.fi.uba.tecnicas.controlador;

import ar.com.fi.uba.tecnicas.vista.InterfazUsuario;

/**
 * Facade con los metodos que puede ejecutar el usuario.
 * @author cristian
 *
 */
public class ComandosUsuarioFacade {
	

	/**
	 * 
	 * @param invocador
	 * @return
	 */
	public String crearRepositorio(InterfazUsuario invocador){
		return "";
	}
	
	/**
	 * Muestra todos los componentes del repositorio
	 * @param invocador
	 * @return
	 */
	public String mostrarRepositorio(InterfazUsuario invocador){
		return "";
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
