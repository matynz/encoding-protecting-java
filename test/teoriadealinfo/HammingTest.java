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
public class HammingTest {

    Hamming h;
    String test1out;
    String test1in = "010";
    String test2out;
    String test2in = "010011110111010101010001010101000111110011101010001011101001110010101101010101010100";
    String test3in = "010011110111010101010001010101000111110011101010001011101001110010101101010101010100"
            + "0100111101110101010100010101010001111100111010100010111010011100"
            + "0111010101010001010101000111110011011101010"
            + "011101010101000010100010101010001111100"
            + "111010101010100010001111100111010011101010001010101000111110011101011110011"
            + "010100011111001101010101"
            + "000100011111001110100111010111010011101010";
    String test3out;

    public HammingTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        h = new Hamming(8);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void Mostrar() {
//        // TODO review the generated test code and remove the default call to fail.
//        ArrayList<String> res_g = h.mostrarG();
//        ArrayList<String> res_h = h.mostrarH();
//        System.out.println("G: ");
//        for (String s : res_g) {
//            System.out.println(s);
//        }
//        System.out.println("H: ");
//        for (String s : res_h) {
//            System.out.println(s);
//        }
    }

    @Test
    public void Proteger() {
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(h.proteger("1011"), "1011010");
    }

    @Test
    public void Recuperar() {
        // TODO review the generated test code and remove the default call to fail.
        try {
            assertEquals(h.recuperar("1011110"), "1011");
        } catch (Exception e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void Recuperar2() {
        // TODO review the generated test code and remove the default call to fail.
        try {
            assertEquals(h.recuperar("1001010"), "1011");
        } catch (Exception e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void Integral() {
        h = new Hamming(16);
        String s = h.proteger("01001110110");
        try {
            assertEquals(h.recuperar(s), "01001110110");
        } catch (Exception e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void IntegralConError() {
        h = new Hamming(16);
        String s = h.proteger("01001110110");
        StringBuilder s1 = new StringBuilder(s);
        s1.replace(2, 3, "1");
        try {
            assertEquals(h.recuperar(s1.toString()), "01001110110");
        } catch (Exception e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void HaminizarMini() {
        h = new Hamming(8);
        test1out = h.haminizar(test1in);
        System.out.println("Haminizar mini: " + test1out);
        DesHaminizarMini();
    }

    public void DesHaminizarMini() {
        String s = h.desHaminizar(test1out);
        System.out.println("Deshaminiar Mini: " + s);
        assertEquals(s.substring(0, test1in.length()), test1in);
    }

    @Test
    public void Haminizar() {
        h = new Hamming(16);
        test2out = h.haminizar(test2in);
        System.out.println("Haminizar: " + test2out);
        DesHaminizar();
    }

    public void DesHaminizar() {
        String s = h.desHaminizar(test2out);
        System.out.println("Deshaminizar: " + s);
        assertEquals(s.substring(0, test2in.length()), test2in);
    }

    @Test
    public void HaminizarBig() {
        h = new Hamming(1024);
        test3out = h.haminizar(test3in);
        System.out.println("HaminizarBig: " + test3out);
        DesHaminizarBig();
    }

    public void DesHaminizarBig() {
        String s = h.desHaminizar(test3out);
        System.out.println("DeshaminizarBig: " + s);
        assertEquals(s.substring(0, test3in.length()), test3in);
    }

    @Test
    public void HaminizarMiniError() {
        h = new Hamming(8);
        test1out = h.haminizar(test1in);
        String temp;
        temp= String.copyValueOf(test1out.toCharArray());
        System.out.println("Haminizar mini Error: " + test1out);
        test1out=Ruido.insertar(test1out, 8);
        System.out.print("Haminizar mini c/Error: ");
        for (int i = 0; i < temp.length(); i++) {
            if (temp.substring(i, i + 1).equals(test1out.substring(i, i + 1))) {
                System.out.print(temp.substring(i, i + 1));
            } else {
                System.out.print("/" + test1out.substring(i, i + 1) + "/");
            }
        }
        System.out.println("");
        DesHaminizarMini();
    }

    public void DesHaminizarMiniError() {
        String s = h.desHaminizar(test1out);
        System.out.println("Deshaminizar Mini Error: " + s);
        assertEquals(s.substring(0, test1in.length()), test1in);
    }

    @Test
    public void HaminizarError() {
        h = new Hamming(16);
        test2out = h.haminizar(test2in);
        System.out.println("HaminizarError: " + test2out);
        test2out=Ruido.insertar(test2out, 16);
        System.out.println("HaminizarError: " + test2out);
        DesHaminizar();
    }

    public void DesHaminizarError() {
        String s = h.desHaminizar(test2out);
        System.out.println("DeshaminiarError: " + s);
        assertEquals(s.substring(0, test2in.length()), test2in);
    }

    @Test
    public void HaminizarBigError() {
        h = new Hamming(1024);
        test3out = h.haminizar(test3in);
        System.out.println("HaminizarBigError: " + test3out);
        test3out=Ruido.insertar(test3out, 1024);
        System.out.println("HaminizarBigError: " + test3out);
        DesHaminizarBig();
    }

    public void DesHaminizarBigError() {
        String s = h.desHaminizar(test3out);
        System.out.println("DeshaminizarError: " + s);
        assertEquals(s.substring(0, test3in.length()), test3in);
    }
}