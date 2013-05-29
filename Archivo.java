/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teoriadealinfo;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author matias
 */
public class Archivo {

    public String archivo_codificado_loc;
    public ArrayList<String> renglones;
    public File file;

    public Archivo(File f) {
        file = f;
    }

    public Archivo(String location) {
        archivo_codificado_loc = location;
    }

    public void setLocationArchivoCod(String s) {
        archivo_codificado_loc = s;
    }

    public boolean escribirArchivo(StringBuilder string_salida) {
        try {
            BitOutputStream out = new BitOutputStream(new BufferedOutputStream(new FileOutputStream(archivo_codificado_loc)));
            for (int i = 0; i < string_salida.length(); i++) {
                out.write(string_salida.charAt(i) - '0');
            }
            out.close();

        } catch (Exception ex) {
            System.out.println("Error:" + ex);
        }
        return true;
    }

    public StringBuilder leerArchivo() {
        int s;
        StringBuilder i = new StringBuilder("");
        try {
            BitInputStream in = new BitInputStream(new BufferedInputStream(new FileInputStream(archivo_codificado_loc)));
            while ((s = in.readNoEof()) != -1) {
                i.append(Integer.toString(s));
            }

            in.close();

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        return i;
    }

    public ArrayList<String> leerArchivoASCII() {
        BufferedReader entrada = null;
        ArrayList<String> salida = new ArrayList<String>();
        try {
            entrada = new BufferedReader(new FileReader(file));
            String renglon;
            //Paso todos los renglones a un arreglo
            while ((renglon = entrada.readLine()) != null) {
                salida.add(renglon);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
        renglones = salida;
        return salida;
    }

    public String leerArchivoASCii() {
        BufferedReader entrada = null;
        StringBuilder salida = new StringBuilder();
        try {
            entrada = new BufferedReader(new FileReader(file));
            String renglon;
            //Paso todos los renglones a un arreglo
            while ((renglon = entrada.readLine()) != null) {
                salida.append(renglon);
                salida.append("\n");
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
        //renglones = salida.;
        return salida.toString();
    }
}
