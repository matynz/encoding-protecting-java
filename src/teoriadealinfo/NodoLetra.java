/**
 * Es un nodo del arbol que contiene Caracter y la cantidad acumulada
 *
 *
 * Practico de Maquina para Teoria de la Informacion
 * Alumnos: 
 *          - Guido Urquiza
 *          - Mauricio Soligo
 *          - Matias Casanova
 */

package teoriadealinfo;


public class NodoLetra {

    public Integer getcant() {
        return int_cant;
    }

    public void setcant(Integer int_cant) {
        this.int_cant = int_cant;
    }

    public String getcarac() {
        return str_carac;
    }

    public void setcarac(String str_carac) {
        this.str_carac = str_carac;
    }
    
    

    private Integer int_cant=0;
    private String str_carac;
    public NodoLetra Hizq=null;
    public NodoLetra Hder=null;
}
