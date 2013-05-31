/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teoriadealinfo;

import java.io.File;
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
public class ArchivoTest {

    public ArchivoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of escribirArchivo method, of class Archivo.
     */
    @Test
    public void testEscribirArchivo() {
        File file = new File("/home/matias/ggg.txt");
        Archivo archivo = new Archivo(file);
        System.out.println("escribirArchivo");
        StringBuffer s = new StringBuffer("1000101000101000111010101010100101010010000");
        String archivo_salida = "/home/matias/testArchivo";
        boolean expResult = true;
        archivo.archivo_codificado_loc=archivo_salida;
        //boolean result = archivo.escribirArchivo(s);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of leerArchivo method, of class Archivo.
     */
    @Test
    public void testLeerArchivo() {
        System.out.println("leerArchivo");
        File file = new File("/home/matias/ggg.txt");
        Archivo archivo = new Archivo(file);
        StringBuffer i = null;
        StringBuffer ie = new StringBuffer("1000101000101000111010101010100101010010000");
        String archivo_entrada = "/home/matias/testArchivo";
        while (ie.length() % 8 != 0) {
            ie.append('0');
        }
        archivo.archivo_codificado_loc=archivo_entrada;
        //i = archivo.leerArchivo();
        //System.out.println("ie: " + ie.length() + " resultado: " + i.length());
        //assertEquals(ie.toString(), i.toString());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
