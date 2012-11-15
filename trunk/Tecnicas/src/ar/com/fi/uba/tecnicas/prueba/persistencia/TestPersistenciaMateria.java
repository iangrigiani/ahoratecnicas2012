package ar.com.fi.uba.tecnicas.prueba.persistencia;

import junit.framework.Assert;

import org.junit.Test;

import ar.com.fi.uba.tecnicas.modelo.entidades.Materia;
import ar.com.fi.uba.tecnicas.modelo.excepciones.ValidacionExcepcion;
import ar.com.fi.uba.tecnicas.persistencia.Repositorio;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioMateria;

public class TestPersistenciaMateria {

	@Test
	public void persistirMateria() {

		try {
			
			Repositorio<Materia> repo = new RepositorioMateria();
			repo.vaciar();
			
			Materia materia = new Materia();
			materia.setCodigo("Codigo1");
			repo.agregar(materia);

			Materia materiaRecup = (Materia) repo.obtener(materia.getCodigo());
			
			Assert.assertEquals(materia.getCodigo(), materiaRecup.getCodigo());
		} catch (ValidacionExcepcion e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void agregarDosMateriasConElmismoCodigo() throws ValidacionExcepcion {
		Repositorio<Materia> repo = new RepositorioMateria();
		repo.vaciar();
		
		Materia materia = new Materia();
		materia.setCodigo("Codigo1");
		repo.agregar(materia);

		Materia materia2 = new Materia();
		materia2.setCodigo("Codigo1");
		
		
		try {
			repo.agregar(materia2);
			Assert.fail("No se deberia poder agregar dos materias con el mismo codigo.");
		} catch (ValidacionExcepcion e) {
			//No hago nada, si falla esta OK.
		}
	}

	@Test
	public void agregarDosMaterias() throws ValidacionExcepcion {
		Repositorio<Materia> repo = new RepositorioMateria();
		repo.vaciar();
		
		Materia materia = new Materia();
		materia.setCodigo("Codigo1");
		repo.agregar(materia);

		Materia materia2 = new Materia();
		materia2.setCodigo("Codigo46");
		
		try {
			repo.agregar(materia2);
			Assert.fail("No se deberia poder agregar dos materias.");
		} catch (ValidacionExcepcion e) {
			//No hago nada, si falla esta OK.
		}
	}

	@Test
	public void removerUnaMateria() throws ValidacionExcepcion {
		Repositorio<Materia> repo = new RepositorioMateria();
		repo.vaciar();
		
		Materia materia = new Materia();
		materia.setCodigo("Codigo1");
		repo.agregar(materia);

		repo.quitar("Codigo1");
		
		Materia materiaNull = repo.obtener("Codigo1");
		
		Assert.assertNull("Se pudo obtener una materia que fue removida.", materiaNull);
	
	}
	
}
