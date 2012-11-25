package ar.com.fi.uba.tecnicas.prueba.persistencia;

import java.util.ArrayList;
import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Test;

import ar.com.fi.uba.tecnicas.controlador.validador.ValidadorCodigoMateria;
import ar.com.fi.uba.tecnicas.controlador.validador.ValidadorParametro;
import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;
import ar.com.fi.uba.tecnicas.modelo.entidades.Regla;
import ar.com.fi.uba.tecnicas.modelo.excepciones.ValidacionExcepcion;
import ar.com.fi.uba.tecnicas.persistencia.Repositorio;
import ar.com.fi.uba.tecnicas.persistencia.RepositorioReglas;

public class TestPersistenciaRegla {

	@Test
	public void persistirRegla() {

		try {
			
			Repositorio<Regla> repo = RepositorioReglas.getInstance();
			repo.vaciar();
			
			Regla regla = new Regla();
			regla.setNombre("Regla1");
			regla.setAsunto("REGLA");
			regla.setAcciones(new ArrayList<String>());
			regla.getNombreAcciones().add("EnviarMail");
			regla.setParametros(new HashMap<Parametro, ValidadorParametro>());
			Parametro parametro = new Parametro();
			parametro.setNombre("PARAM1");
			regla.getParametros().put(parametro, new ValidadorCodigoMateria());
			repo.agregar(regla);
			
			Regla reglaRecup = (Regla) repo.obtener(regla.getNombre());
			
			Assert.assertEquals(regla.getNombre(), reglaRecup.getNombre());
			Assert.assertEquals(regla.getAsunto(), reglaRecup.getAsunto());
			Assert.assertNotNull(reglaRecup.getNombreAcciones());
			Assert.assertEquals(regla.getNombreAcciones().size(), reglaRecup.getNombreAcciones().size());
			Assert.assertEquals(reglaRecup.getNombreAcciones().size(), 1);
			Assert.assertTrue(regla.getNombreAcciones().get(0).equals(reglaRecup.getNombreAcciones().get(0)));
			Assert.assertNotNull(reglaRecup.getParametros());
			Assert.assertEquals(regla.getParametros().size(), reglaRecup.getParametros().size());
			Assert.assertEquals(reglaRecup.getParametros().size(), 1);
			Assert.assertTrue(reglaRecup.getParametros().containsKey(parametro));
			Assert.assertTrue(reglaRecup.getParametros().get(parametro) instanceof ValidadorCodigoMateria);
		} catch (ValidacionExcepcion e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void agregarDosReglasConElmismoNombre() throws ValidacionExcepcion {
		Repositorio<Regla> repo = RepositorioReglas.getInstance();
		repo.vaciar();
		
		Regla regla = new Regla();
		regla.setNombre("Regla1");
		repo.agregar(regla);
		
		Regla regla2 = new Regla();
		regla2.setNombre("Regla1");
		
		
		try {
			repo.agregar(regla2);
			Assert.fail("No se deberia poder agregar dos reglas con el mismo nombre.");
		} catch (ValidacionExcepcion e) {
			//No hago nada, si falla esta OK.
		}
	}

	@Test
	public void removerUnaRegla() throws ValidacionExcepcion {
		Repositorio<Regla> repo = RepositorioReglas.getInstance();
		repo.vaciar();
		
		Regla regla = new Regla();
		regla.setNombre("Regla1");
		repo.agregar(regla);
		
		Regla regla2 = new Regla();
		regla2.setNombre("Regla34");
		repo.agregar(regla2);
		
		repo.quitar("Regla1");
		
		Regla reglaNull = repo.obtener("Regla1");
		
		Assert.assertNull("Se pudo obtener una regla que fue removida.", reglaNull);
	
	}
	
}
