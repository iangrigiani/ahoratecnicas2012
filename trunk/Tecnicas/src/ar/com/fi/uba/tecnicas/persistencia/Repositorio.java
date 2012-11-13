package ar.com.fi.uba.tecnicas.persistencia;

import java.util.List;

import ar.com.fi.uba.tecnicas.modelo.excepciones.ValidacionExcepcion;

public interface Repositorio<T> {

	/**
	 * Obtiene un elemento sin importar de que tipo sea.
	 * 
	 * @param nombre
	 *            identificador del elemento.
	 * @return
	 */
	T obtener(String clave);
	
	/**
	 * Devuelve todos los objetos de un determinados tipo.
	 * @param clazz
	 * @return
	 */
	List<T> obtenerTodos();
 
	/**
	 * Agrega un elemento, en caso de que no exista. Caso contrario no lo
	 * agrega.
	 * 
	 * @param elemento
	 *            elemento que se quiere agregar
	 * @throws en caso de que el compomente exista.
	 */
	void agregar(T elemento) throws ValidacionExcepcion;
	
	/**
	 * Remueve un elemento.
	 * 
	 * @param elemento
	 *            elemento a remover.
	 */
	void quitar(String clave);

	/**
	 * Vacia el repositorio.
	 */
	void vaciar();
}
