/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.fi.uba.tecnicas.prueba.regla;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import ar.com.fi.uba.tecnicas.modelo.entidades.Regla;

/**
 *
 * @author jonathan
 */
public class ReglaTest {
    
    public ReglaTest() {
    }

    /**
     * Test of obtenerParametrosDelAsunto method, of class Regla.
     */
    @Test
    public void testObtenerParametrosDelAsunto() {
        System.out.println("obtenerParametrosDelAsunto");
        Regla instance = new Regla();
        String[] expResult = {"7510","87555","John","Bonachon"};
        instance.setAsunto("ALTA-MATERIA");
        String[] result = instance.obtenerParametrosDelAsunto("[ALTA-MATERIA] 7510-  87555- John - Bonachon");
        assertArrayEquals(expResult, result);
    }
}
