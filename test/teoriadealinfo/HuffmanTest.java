/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teoriadealinfo;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matias
 */
public class HuffmanTest {
    
    public HuffmanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of descomprimirArchivo method, of class Huffman.
     */
    @Test
    public void testDescomprimirArchivo() {
        System.out.println("descomprimirArchivo");
        StringBuffer in = null;
        Tabla t = null;
        Arbol a = null;
        Huffman instance = null;
//        StringBuffer expResult = null;
//        StringBuffer result = instance.descomprimirArchivo(in, t, a);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of guardarTabla method, of class Huffman.
     */
    @Test
    public void testGuardarTabla() {
        System.out.println("guardarTabla");
        Tabla t = null;
        Huffman instance = null;
        int expResult = 0;
//        int result = instance.guardarTabla(t);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leerTabla method, of class Huffman.
     */
    @Test
    public void testLeerTabla() {
        System.out.println("leerTabla");
        StringBuffer in = null;
        Tabla t = null;
        Arbol a = null;
//        Huffman instance = null;
//        instance.leerTabla(in, t, a);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of llenarMapaCodCar method, of class Huffman.
     */
    @Test
    public void testLlenarMapaCodCar() {
        System.out.println("llenarMapaCodCar");
        ArrayList<NodoCodigo> tabla = null;
        Huffman instance = null;
        instance.llenarMapaCodCar(tabla);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of llenarMapaCarCod method, of class Huffman.
     */
    @Test
    public void testLlenarMapaCarCod() {
        System.out.println("llenarMapaCarCod");
        ArrayList<NodoCodigo> tabla = null;
        Huffman instance = null;
        instance.llenarMapaCarCod(tabla);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of comprimirArchivo method, of class Huffman.
     */
    @Test
    public void testComprimirArchivo() {
        System.out.println("comprimirArchivo");
//        Huffman instance = null;
//        instance.comprimirArchivo();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}