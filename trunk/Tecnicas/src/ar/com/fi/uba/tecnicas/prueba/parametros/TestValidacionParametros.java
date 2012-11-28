package ar.com.fi.uba.tecnicas.prueba.parametros;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ar.com.fi.uba.tecnicas.controlador.validador.ValidadorCodigoMateria;
import ar.com.fi.uba.tecnicas.controlador.validador.ValidadorNumeroTP;
import ar.com.fi.uba.tecnicas.controlador.validador.ValidadorPadronDeInscripto;
import ar.com.fi.uba.tecnicas.controlador.validador.ValidadorPadronNuevo;
import ar.com.fi.uba.tecnicas.controlador.validador.ValidadorPrivacidad;
import ar.com.fi.uba.tecnicas.modelo.entidades.Parametro;

public class TestValidacionParametros {

	@Test
	public void validaCodigoMateria() {
        ValidadorCodigoMateria validador = new ValidadorCodigoMateria();
        
        Parametro codigoMateria = new Parametro();
        codigoMateria.setValor("7510");
        codigoMateria.setValidador(validador);
        
        assertEquals(true, codigoMateria.validar());
	}
	@Test
	public void noValidaCodigoMateria() {
        ValidadorCodigoMateria validador = new ValidadorCodigoMateria();
        
        Parametro codigoMateria = new Parametro();
        codigoMateria.setValor("75.10");
        codigoMateria.setValidador(validador);
        
        assertEquals(false, codigoMateria.validar());

	}
	@Test
	public void validaNumeroTp() {
        ValidadorNumeroTP validador = new ValidadorNumeroTP();
        
        Parametro numeroTp = new Parametro();
        numeroTp.setValor("1");
        numeroTp.setValidador(validador);
        
        assertEquals(true, numeroTp.validar());
	}
	@Test
	public void noValidaNumeroTp() {
        ValidadorNumeroTP validador = new ValidadorNumeroTP();
        
        Parametro numeroTp = new Parametro();
        numeroTp.setValor("Uno");
        numeroTp.setValidador(validador);
        
        assertEquals(false, numeroTp.validar());
	}
	@Test
	public void validaPadronDeInscripto() {
        ValidadorPadronDeInscripto validador = new ValidadorPadronDeInscripto();
        
        Parametro padron = new Parametro();
        padron.setValor("84634");
        padron.setValidador(validador);
        
        assertEquals(true, padron.validar());
	}
	@Test
	public void noValidaPadronDeInscripto() {
        ValidadorPadronDeInscripto validador = new ValidadorPadronDeInscripto();
        
        Parametro padron = new Parametro();
        padron.setValor("8A6B3");
        padron.setValidador(validador);
        
        assertEquals(false, padron.validar());
	}
	@Test
	public void validaPadronNuevo() {
        ValidadorPadronNuevo validador = new ValidadorPadronNuevo();
        
        Parametro padron = new Parametro();
        padron.setValor("84634");
        padron.setValidador(validador);
        
        assertEquals(true, padron.validar());
	}
	@Test
	public void noValidaPadronNuevo() {
        ValidadorCodigoMateria validador = new ValidadorCodigoMateria();
        
        Parametro padron = new Parametro();
        padron.setValor("-84634");
        padron.setValidador(validador);
        
        assertEquals(false, padron.validar());
	}
	@Test
	public void validaPrivacidad() {
        ValidadorPrivacidad validador = new ValidadorPrivacidad();
        
        Parametro privacidad = new Parametro();
        privacidad.setValor("Publica");
        privacidad.setValidador(validador);
        
        assertEquals(true, privacidad.validar());
	}
	@Test
	public void noValidaPrivacidad() {
        ValidadorCodigoMateria validador = new ValidadorCodigoMateria();
        
        Parametro privacidad = new Parametro();
        privacidad.setValor("ParaTodosYTodas");
        privacidad.setValidador(validador);
        
        assertEquals(false, privacidad.validar());
	}
}
