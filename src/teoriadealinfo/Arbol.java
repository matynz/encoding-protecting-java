/**
 * 
 * Mantiene y organiza el arbol para codificar los caracteres
 *
 * Practico de Maquina para Teoria de la Informacion
 * Alumnos: 
 *          - Guido Urquiza
 *          - Mauricio Soligo
 *          - Matias Casanova
 */

package teoriadealinfo;

import java.util.ArrayList;


public class Arbol {

    public NodoLetra raiz;

    /** Ordena el array con nodoLetras(contiene la letra y la cantidad)
     *  de menor a Mayor
     */
    private ArrayList<NodoLetra> ordenarArray(ArrayList<NodoLetra> arr_aux) {

        NodoLetra aux_letra;
        for (int aux = 0; aux < arr_aux.size() - 1; aux++) {
            for (int aux_2 = aux + 1; aux_2 < arr_aux.size(); aux_2++) {

                if (arr_aux.get(aux).getcant() > arr_aux.get(aux_2).getcant()) {
                    aux_letra = arr_aux.get(aux_2);
                    arr_aux.set(aux_2, arr_aux.get(aux));
                    arr_aux.set(aux, aux_letra);
                } else if (arr_aux.get(aux).getcant() == arr_aux.get(aux_2).getcant()
                        && arr_aux.get(aux).getcarac().compareTo(arr_aux.get(aux_2).getcarac()) < 0) {
                    aux_letra = arr_aux.get(aux_2);
                    arr_aux.set(aux_2, arr_aux.get(aux));
                    arr_aux.set(aux, aux_letra);
                }
            }
        }
        return arr_aux;

    }

    /** 
     *  Ordena el ultimo elemento en el ultimo lugar donde corresponde Aqui
     *  nodoLetra  en el caracter puede tener una cadena que represenat a sus hijos
     */  
    private ArrayList<NodoLetra> insertarEnArray(ArrayList<NodoLetra> arr_aux) {
        NodoLetra aux_letra ;         
        for (int aux = arr_aux.size() - 1; aux > 1;aux--) {

            if (arr_aux.get(aux - 1).getcant() > arr_aux.get(aux).getcant()) {
                aux_letra = arr_aux.get(aux);
                arr_aux.set(aux, arr_aux.get(aux - 1));
                arr_aux.set(aux - 1, aux_letra);
            } else if (arr_aux.get(aux - 1).getcant() == arr_aux.get(aux).getcant()
                    && arr_aux.get(aux - 1).getcarac().length() < arr_aux.get(aux).getcarac().length()) {
                aux_letra = arr_aux.get(aux);
                arr_aux.set(aux, arr_aux.get(aux - 1));
                arr_aux.set(aux - 1, aux_letra);
            } else if (arr_aux.get(aux - 1).getcant() == arr_aux.get(aux).getcant()
                    && arr_aux.get(aux - 1).getcarac().length() == arr_aux.get(aux).getcarac().length()
                    && arr_aux.get(aux - 1).getcarac().compareTo(arr_aux.get(aux).getcarac()) < 0) {
                aux_letra = arr_aux.get(aux);
                arr_aux.set(aux, arr_aux.get(aux - 1));
                arr_aux.set(aux - 1, aux_letra);
            }
        }
        return arr_aux;

    }

    /** El proceso que agrupa a los 2 nodos con menor cantidad */
    public ArrayList<NodoLetra> codificarArbol(ArrayList<NodoLetra> arr_aux) {
        NodoLetra new_nodo;
        int aux = 0;
        arr_aux = ordenarArray(arr_aux);
        new_nodo = new NodoLetra();
        while (arr_aux.size() > 1) {
            new_nodo.setcant(arr_aux.get(0).getcant() + arr_aux.get(1).getcant());
            if (arr_aux.get(0).getcant() > arr_aux.get(1).getcant()) {
                new_nodo.Hizq = arr_aux.get(0);
                new_nodo.Hder = arr_aux.get(1);
                new_nodo.setcarac(arr_aux.get(0).getcarac() + arr_aux.get(1).getcarac());

            } else {
                new_nodo.Hizq = arr_aux.get(1);
                new_nodo.Hder = arr_aux.get(0);
                new_nodo.setcarac(arr_aux.get(1).getcarac() + arr_aux.get(0).getcarac());
            }
            arr_aux.add(new_nodo);

            if (arr_aux.size() >= 3) {
                arr_aux.remove(aux);
                arr_aux.remove(aux);
            }

            arr_aux = insertarEnArray(arr_aux);

            new_nodo = new NodoLetra();

        }
        raiz=arr_aux.get(0);

        return arr_aux;
    }
}
