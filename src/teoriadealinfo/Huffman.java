/**
 * Practico de Maquina para Teoria de la Informacion
 * Alumnos: 
 *          - Guido Urquiza
 *          - Mauricio Soligo
 *          - Matias Casanova
 */
package teoriadealinfo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;


public class Huffman {

    public HashMap<String, String> map_codigo = new HashMap<String, String>();
    public HashMap<String, String> map_car = new HashMap<String, String>();
    Archivo archivo;
    Arbol arbol;
    StringBuilder dest;
    ///CONSTANTES
    final byte[] BNL = {10};
    final String NL = new String(BNL);
    final byte[] BEOF = {4};
    final String EOF = new String(BEOF);

    public Huffman(Archivo a, Arbol arb) {
        archivo = a;
        arbol = arb;
    }
    
    public Huffman() {
    }

    public StringBuilder descomprimirArchivo(StringBuilder in) {
        String cod = "";
        StringBuilder texto = new StringBuilder("");
        boolean finArchivo = false;
        while (in.length() > 0 && !finArchivo) {
            cod = cod + in.substring(0, 1);
            in.deleteCharAt(0);
            if (map_car.containsKey(cod)) {
                String s = map_car.get(cod);
                if (s.equals(NL)) {
                    texto.append("\r\n");
                    cod = "";
                } else if (s.equals(EOF)) {
                    finArchivo = true;
                } else {
                    texto.append(s);
                    cod = "";
                }
            }
        }
        return texto;
    }

    public int guardarTabla(NodoLetra raiz, StringBuilder dest) {

        ArrayDeque<NodoLetra> stack = new ArrayDeque<NodoLetra>();
        stack.push(raiz);
        NodoLetra nactual;
        while (!stack.isEmpty()) {
            nactual = stack.pop();
            if (nactual.getcarac().length() > 1) {
                ///   0 nodo no terminal
                dest.append("0");
                stack.push(nactual.Hder);
                stack.push(nactual.Hizq);
            } else if (nactual.getcarac().length() == 1) {
                ///   1 nodo terminal
                dest.append("1");


                String cod = Integer.toBinaryString(nactual.getcarac().charAt(0));
                for (int i = cod.length(); i < 8; i++) {
                    cod = "0" + cod;
                }
                dest.append(cod);
            }


        }
        return 0;
    }
    
    public void comprimirArchivo(StringBuilder dest, String texto) {
//        ArrayList<String> renglones = archivo.renglones;
//        int cant_renglones = renglones.size();
            for (char c : texto.toCharArray()) {
                String cod = map_codigo.get(Character.toString(c));
                if (cod.equals(EOF) || cod.equals(NL)) {
                    System.out.println("Codificando caracter indebido");
                    return;
                }
                dest.append(cod);
            }
//            if (cant_renglones > 1) {
//                String cod = map_codigo.get(NL);
//                dest.append(cod);
//                cant_renglones--;
//            }
        
        /// Codigo para insertar fin de archivo
        String cod = map_codigo.get(EOF);
        dest.append(cod);
    }

    public NodoLetra leerTabla(StringBuilder in) {
        NodoLetra nactual = new NodoLetra();
        int bit;
        String bincarac = "";
        ArrayDeque<NodoLetra> stack = new ArrayDeque<NodoLetra>();
        int intcarac;
        char ccarac;
        String cadena = "";
        NodoLetra raiz = nactual;
        stack.push(nactual);
        String codigo;
        while (!stack.isEmpty()) {
            nactual = stack.pop();
            codigo = in.substring(0, 1);
            in.delete(0, 1);
            bit = Integer.parseInt(codigo);
            if (bit == 1) {
                bincarac = in.substring(0, 8);
                in.delete(0, 8);
                intcarac = Integer.parseInt(bincarac, 2);

                ccarac = (char) (intcarac);

                nactual.setcarac(String.valueOf(ccarac));
                bincarac = "";


            } else {
                nactual.setcarac("*****");
                nactual.Hizq = new NodoLetra();
                nactual.Hder = new NodoLetra();
                stack.push(nactual.Hder);
                stack.push(nactual.Hizq);

            }
        }
//        t.getTabla().clear();
//        t.codificarSimbolo(a.raiz, cadena);
//        llenarMapaCodCar(t.getTabla());
        return raiz;


    }

    public void llenarMapaCodCar(ArrayList<NodoCodigo> tabla) {
        for (NodoCodigo x : tabla) {
            map_car.put(x.str_codigo, x.str_carc);
        }
    }

    public void llenarMapaCarCod(ArrayList<NodoCodigo> tabla) {
        for (NodoCodigo x : tabla) {
            map_codigo.put(x.str_carc, x.str_codigo);
        }
    }

}
