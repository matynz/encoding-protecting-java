/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teoriadealinfo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author matias
 */
public class Tabla {

    public Archivo archivo;
    public Arbol arbol;
    ////Variables de clase
    public ArrayList<NodoCodigo> tabla_codigo = new ArrayList<NodoCodigo>();
    private HashMap<Character, Integer> tabla_freq = new HashMap<Character, Integer>(255);
    private ArrayList<NodoLetra> letras = new ArrayList<NodoLetra>();
    ///CONSTANTES
    final byte[] BNL = {10};
    final String NL = new String(BNL);
    final byte[] BEOF = {4};
    final String EOF = new String(BEOF);

    public Tabla(Archivo a, Arbol ab) {
        archivo = a;
        arbol = ab;
    }
    public Tabla() {
        ///Blanquear variables
    }

    public ArrayList<NodoCodigo> getTabla() {
        return tabla_codigo;
    }

    public void generarFrecuencias(String texto) {

        for (char c : texto.toCharArray()) {
            if (tabla_freq.containsKey(c)) {
                tabla_freq.put(c, tabla_freq.get(c) + 1);
            } else {
                tabla_freq.put(c, 1);
            }
        }
    }

    public void armarTabla() {
        NodoLetra letra;
        //Armo el Arreglo de Letras
        for (Character c : tabla_freq.keySet()) {
            letra = new NodoLetra();
            letra.setcarac(c.toString());
            letra.setcant(tabla_freq.get(c));
            letras.add(letra);
        }

        //caracter especial de fin de archivo
        letra = new NodoLetra();
        letra.setcarac(EOF);
        letra.setcant(1);
        letras.add(letra);

    }
    public ArrayList<NodoLetra> getLetras(){
        return letras;
    }
    

    public void cargarFrecuencias() {
        NodoLetra new_letra;
        ArrayList<NodoLetra> letra_s = new ArrayList<NodoLetra>();

        HashMap<Character, Integer> tabla = new HashMap<Character, Integer>(255);
        ArrayList<String> renglones = archivo.leerArchivoASCII();
        String texto = "";
        for (String r : renglones) {
            texto += r + "\n";
        }
        //Saco frecuencias
        for (char c : texto.toCharArray()) {
            if (tabla.containsKey(c)) {
                tabla.put(c, tabla.get(c) + 1);
            } else {
                tabla.put(c, 1);
            }
        }
        //Armo el Arreglo de Letras
        for (Character c : tabla.keySet()) {
            new_letra = new NodoLetra();
            new_letra.setcarac(c.toString());
            new_letra.setcant(tabla.get(c));
            letra_s.add(new_letra);
        }

        //caracter especial de fin de archivo
        new_letra = new NodoLetra();
        new_letra.setcarac(EOF);
        new_letra.setcant(1);
        letra_s.add(new_letra);
        letra_s = arbol.codificarArbol(letra_s);
        arbol.raiz = letra_s.get(0);
        String cadena = "";
        codificarSimbolo(arbol.raiz, cadena);

    }

    public void codificarSimbolo(NodoLetra arr_aux, String cadena) {
        NodoCodigo new_codigo;
        if (arr_aux != null) {
            if (arr_aux.getcarac().length() == 1) {
                new_codigo = new NodoCodigo();
                new_codigo.str_carc = arr_aux.getcarac();
                new_codigo.str_codigo = cadena;
                new_codigo.int_frecuencia = arr_aux.getcant();
                tabla_codigo.add(new_codigo);
            }
            codificarSimbolo(arr_aux.Hizq, cadena + "0");
            codificarSimbolo(arr_aux.Hder, cadena + "1");
        }
    }
}
