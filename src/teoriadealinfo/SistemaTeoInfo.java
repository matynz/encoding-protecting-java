/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teoriadealinfo;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author matias
 */
public class SistemaTeoInfo {
    ///Objetos a Utilizar

    private Arbol arbol;
    private Tabla tabla;
    private Archivo archivo;
    private Huffman huffman;
    private Hamming hamming;
    /// Variables internas
    StringBuilder bin_codificado;
    StringBuilder ascii_decodificado;
    int tamaño_bloque; /// tamaño del bloque
    // Contadores internos de integridad
    //      Codificar: 0- New
    //          1- Seleccionar archivo 2- Generar tabla 3-Devolver Tabla 4-Guardar Tabla 5-Comprimir 6- Haminizar 7-Error 8 - Escribir
    private int c_codificar = 0;
    //      Decodificar: 0- New
    //          1- Seleccionar Archivo 2- Armar tabla   3- Descomprimir
    private int c_decodificar = 0;
    //// Hamminizado
    private int hamminizado = 0;
    //// Rama de Codificar Archivo
    ////  -Leer ruta del archivo AscII a codificar

    public int setASCIIPath(File file) {
        if (c_codificar == 0) {
            archivo = new Archivo(file);
            c_codificar++;
            return 0;
        } else {
            archivo = new Archivo(file);
            c_codificar = 1;
            return 0;
        }
        //System.err.println("Error en el paso codificar: " + c_codificar);
        //return c_codificar + 1;
    }

    public String getASCIIText() {
        if (c_codificar == 1) {
            return archivo.leerArchivoASCii();
        }
        System.err.println("Error en el paso codificar: " + c_codificar);
        throw new IllegalArgumentException("No hay Archivo Cargado");
    }
    /// - Leer Archivo, Generar Frecuencias, armar Tabla y Arbol 

    private void generarFrecuencias(String texto) {
        tabla.generarFrecuencias(texto);
    }

    private void armarTabla() {
        tabla.armarTabla();
    }

    private void armarArbol() {
        arbol.codificarArbol(tabla.getLetras());
    }

    private void codificarTabla() {
        tabla.codificarSimbolo(arbol.raiz, "");///el segundo parametro vacio lo unico que hace es pasar una cadena vacia
    }

    public int codeTabla() {
        if (c_codificar == 1) {
            //arreglar esto
            tabla = new Tabla();
            String texto = archivo.leerArchivoASCii();
            generarFrecuencias(texto);
            armarTabla();
            arbol = new Arbol();
            armarArbol();
            codificarTabla();
            c_codificar++;
            return 0;
        }
        System.err.println("Error en el paso codificar: " + c_codificar);
        return c_codificar + 1;
    }
    /// Devolver Tabla

    public ArrayList<NodoCodigo> getTabla() {
        if (c_codificar == 2) {
            c_codificar++;
            return tabla.getTabla();
        }
        System.err.println("Error en el paso codificar: " + c_codificar);
        throw new IllegalArgumentException("No hay Archivo Cargado");
    }
    /// Guardar Tabla

    public int guardarTabla(String loc) {
        if (c_codificar == 3) {
            int tamaño = tabla.getTabla().size();
            if (tamaño > 255) {
                System.err.println("Error en el paso codificar: " + c_codificar + " Demasiados caracteres a codificar");
                return c_codificar++;
            }
            huffman = new Huffman();
            tamaño *= 8;
            archivo.setLocationArchivoCod(loc);
            huffman.llenarMapaCarCod(tabla.getTabla());
            bin_codificado = new StringBuilder(tamaño);
            huffman.guardarTabla(arbol.raiz, bin_codificado);
            c_codificar++;
            return 0;
        }
        System.err.println("Error en el paso codificar: " + c_codificar);
        return c_codificar + 1;
    }

    //// Comprimir Archivo
    public int comprimirArchivo() {
        if (c_codificar == 4) {
            huffman.comprimirArchivo(bin_codificado, archivo.leerArchivoASCii());
            hamminizado = 0;
            c_codificar=5;
            return 0;
        }
        System.err.println("Error en el paso codificar: " + c_codificar);
        return c_codificar + 1;
    }
    ///// Hamming
    ///// Solo hay que pasarle el tamaño del bloque?
    ///// Puede devolvernos el string protegido (con los primeros 7 bits que tamaño de compresion es)
    ///// + los datos protegidos y puede traer 0 de mas al final  /// Sacarlos

    public int hamminizar(int tamaño) {
        if (c_codificar == 5) {
            tamaño_bloque = tamaño;
            hamming = new Hamming(tamaño_bloque);
            bin_codificado = new StringBuilder(hamming.haminizar(bin_codificado.toString()));
            hamminizado = 1;
            c_codificar=6;
            return 0;
        }
        System.err.println("Error en el paso codificar: " + c_codificar);
        return c_codificar + 1;
    }

    public int insertarRuido() {
        if (c_codificar == 6) {
            bin_codificado = new StringBuilder(Ruido.insertar(bin_codificado.toString(), tamaño_bloque));
            c_codificar=7;
        }
        System.err.println("Error en el paso codificar: " + c_codificar);
        return c_codificar + 1;
    }

    ///// Escribir archivo
    public int escribirArchivo() {
        if (c_codificar == 5 || c_codificar==6 || c_codificar == 7) {
            archivo.escribirArchivo(bin_codificado);
            JOptionPane.showMessageDialog(null,"Archivo guardado con EXITO");
            return 0;
        }
        System.err.println("Error en el paso codificar: " + c_codificar);
        return c_codificar + 1;
    }

    //// Descomprimir Archivo
    ///Leer archivo 1
    public int setPathDescomprimir(String loc) {
        c_decodificar = 1;
        archivo = new Archivo(loc);
        bin_codificado = archivo.leerArchivo();
        hamminizado=1;
        return 0;

    }

    /////// Devuleve 1 en caso de que no se pueda deshamminizar en este momento, por ejemplo que no haya un 
    /////// texto cargado o que el texto cargado sea recien comprimido o recien deshamminizado.
    public int desHamminizar() {
        if (c_decodificar == 1) {
            if (hamminizado == 0) {
                return 1;
            }
            if (hamming == null) {
                hamming = new Hamming(8);
            }
            bin_codificado = new StringBuilder(hamming.desHaminizar(bin_codificado.toString()));
            hamminizado = 0;
            c_decodificar=2;
            return 0;
        }
        return c_decodificar + 1;
    }

    //Sacarle el Huffman
    public int descomprimirArchivo() {
        if (c_decodificar == 1 || c_decodificar == 2) {
            arbol = new Arbol();
            huffman = new Huffman();
            arbol.raiz = huffman.leerTabla(bin_codificado);
            tabla = new Tabla();
            tabla.codificarSimbolo(arbol.raiz, "");
            huffman.llenarMapaCodCar(tabla.getTabla());
            ascii_decodificado = huffman.descomprimirArchivo(bin_codificado);
            c_decodificar++;
            return 0;
        }

        System.err.println("Error en el paso decodificar: " + c_codificar);
        return c_decodificar + 1;

    }

    public String getTextoDescomprimido() {
        if (c_decodificar == 3) {
            return ascii_decodificado.toString();
        }
        return "";
    }
}
