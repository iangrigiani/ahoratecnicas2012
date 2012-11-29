/**
 * 
 */
package ar.com.fi.uba.tecnicas.modelo.entidades.accion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import ar.com.fi.uba.tecnicas.modelo.entidades.Alumno;
import ar.com.fi.uba.tecnicas.modelo.entidades.AlumnoGrupo;
import ar.com.fi.uba.tecnicas.modelo.entidades.Grupo;
import ar.com.fi.uba.tecnicas.modelo.entidades.Inscripcion;
import ar.com.fi.uba.tecnicas.modelo.entidades.Materia;
import ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje;
import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;
import ar.com.fi.uba.tecnicas.modelo.excepciones.ValidacionExcepcion;
import ar.com.fi.uba.tecnicas.persistencia.Repositorio;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioAlumno;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioAlumnoGrupo;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioGrupo;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioIncripciones;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioMateria;

/**
 * @author nacho
 * Parsea el txt de los padrones
 * Chequea que los padrones sean validos
 * Chequea que los padrones existan en la materia en el cuatrimestre
 * Chequea que los padrones NO pertenezcan ya a algun grupo
 * 
 *
 */
public class AltaGrupo implements Accion {

	/**
	 * @see ar.com.fi.uba.tecnicas.modelo.entidades.accion.Accion#ejecutar(ar.com.fi.uba.tecnicas.modelo.entidades.Mensaje, java.util.List)
	 */
	@Override
	public String ejecutar(Mensaje mensaje, Set<Parametro> parametros) {
		String retorno = "";
		
		// Aca tengo que obtener el listado de padrones
		 
		
		Repositorio<Inscripcion> repositorioInscripcion = RepositorioIncripciones.getInstance();
		Repositorio<Materia> repositorioMateria = RepositorioMateria.getInstance();
		Repositorio<Grupo> repositorioGrupo = RepositorioGrupo.getInstance();
		Repositorio<AlumnoGrupo> repositorioAlumnoGrupo = RepositorioAlumnoGrupo.getInstance();

		
    	List<Materia> obtenerTodasMaterias = repositorioMateria.obtenerTodos();
    	if (obtenerTodasMaterias == null || obtenerTodasMaterias.isEmpty()) {
    		return "No existe materia";
    	}
    	Materia materia = obtenerTodasMaterias.get(0);
    	
    	String nroGrupo = "";
    	for (Parametro parametro : parametros) {
			if (parametro.getValidador().getDescripcion().equalsIgnoreCase("NUMERO_GRUPO")) {
				nroGrupo = parametro.getValor(); 
			}
		}
		
		// Recorro la lista de padrones y verifico:
		// * que sean validos
		// * que existan en la materia en el cuatrimestre
		// * que NO pertenezcan a un grupo
		List<Inscripcion> inscripciones = repositorioInscripcion.obtenerTodos();
		Inscripcion ins;
		
		//Asumo que solo existe un ajunto
		if (mensaje.getPathAdjunto() != null && !mensaje.getPathAdjunto().isEmpty()) {
			File archivo = new File(mensaje.getPathAdjunto().get(0));
			if (!archivo.exists()) {
				return "No se encuentran los adjuntos";
			}
		            
            List<String> padrones = obtenerPadrones(archivo);
            if (validarPadrones(padrones)) {
            	for (String padron : padrones) {
	            	
	            	ins = new Inscripcion(padron, materia.getCodigo(), new Date());

	            	if (inscripciones.contains(ins)) {
	            		//Crear el grupo y crear la vinculacion del alumno con el grupo
	            		try {
							agregarAlGrupo(padron, nroGrupo, ins.getPrimerCuatrimestre(), repositorioGrupo, repositorioAlumnoGrupo);
							System.out.println("Alta Grupo: Se dio de alta correctamente los alumno en el grupo " + nroGrupo);
						} catch (ValidacionExcepcion e) {
							System.out.println("Alta Grupo: Alguno de los algunos ya existe en otro grupo en este cuatrimestre");
						}
	            	}
	            }
            } else {
            	retorno = "No exiten uno o mas padrones.";
            }
	     
		} 
      
		return retorno;
	}

	private void agregarAlGrupo(String padron, String nroGrupo, Boolean primerCuatrimestre, Repositorio<Grupo> repositorioGrupo, Repositorio<AlumnoGrupo> repositorioAlumnoGrupo) throws ValidacionExcepcion {
		if (repositorioGrupo.obtener(nroGrupo) != null) {
			AlumnoGrupo alGrupo = new AlumnoGrupo();
			alGrupo.setGrupo(nroGrupo);
			alGrupo.setPadron(padron);
			alGrupo.setPrimerCuatrimestre(primerCuatrimestre);
			repositorioAlumnoGrupo.agregar(alGrupo);
		}
	}

	private Boolean validarPadrones(List<String> padrones) {
		Repositorio<Alumno> repositorioAlumno = RepositorioAlumno.getInstance();
		for (String string : padrones) {
			if (repositorioAlumno.obtener(string) == null) {
	    		return false;
	    	}	
		}
		return true;
	}

	private List<String> obtenerPadrones(File archivoName) {
		List<String> padrones = new ArrayList<String>();
		RandomAccessFile archivo;
		try {
			archivo = new RandomAccessFile(archivoName, "r");
			 String line = archivo.readLine();
		        int i = 0;
		        while (line != null && !line.isEmpty()) {
					padrones.add(line);
		        	line = archivo.readLine();
		        	i++;
		        }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        return padrones;
	}

	@Override
	public String puedeEjecutar(Mensaje mesg, Set<Parametro> parametrosParaAccion) {
		String retorno = "";
		List<String> pathAdjuntos = mesg.getPathAdjunto();
		if (pathAdjuntos.isEmpty()){
			retorno = "Alta de Grupo: El mensaje no tiene un archivo adjunto";
		}
		
		return retorno;
	}

}

