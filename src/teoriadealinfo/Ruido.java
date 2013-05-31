/**
 *  Inserta Ruido en un string de 0s y 1s
 *
 * Practico de Maquina para Teoria de la Informacion
 * Alumnos:
 *          - Guido Urquiza
 *          - Mauricio Soligo
 *          - Matias Casanova
 */
package teoriadealinfo;

import java.util.Random;

public class Ruido {

    public Ruido() {
    }

    /**
     *
     *  Detecta los bloques como de tamaño de Bloque  y se compone por 7 bits al comienzo que definen
     *  tamaño del bloque y luego n bloques de tamaño de bloque
     *
     *
     */
    public static String insertar(String s, int bloque) {
        StringBuilder ent = new StringBuilder(s);
        bloque = bloque - 1;
        StringBuilder res = new StringBuilder(s.length());
        int tamaño = ent.length() - 7;
        Random r = new Random();
        if (r.nextBoolean()) {
            res.append(alterar(ent.substring(0, 7)));
            ent.delete(0, 7);
        }else
        {
            res.append(ent.substring(0, 7));
            ent.delete(0, 7);
        }
        for (int i = 0; i < tamaño / bloque; i++) {
            if (r.nextBoolean()) {
                res.append(alterar(ent.substring(0, bloque)));
                ent.delete(0, bloque);
            }else{
                res.append(ent.substring(0, bloque));
                ent.delete(0, bloque);
            }
        }
        return res.toString();
    }

    private static String alterar(String s) {
        StringBuilder res = new StringBuilder(s);
        int tamaño = res.length();
        Random r = new Random();
        int lugar = r.nextInt(tamaño);
        res.replace(lugar, lugar + 1, res.charAt(lugar) == '0' ? "1" : "0");
        return res.toString();
    }
}
