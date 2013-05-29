/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package teoriadealinfo;

/**
 *
 * @author Mauricio
 */
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
