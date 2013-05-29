/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package teoriadealinfo;

/**
 *
 * @author Mauricio
 */
public class nodo_letra {

    public Integer getcant() {
        return int_cant;
    }

    public void setcant(Integer int_Value) {
        int_cant = int_Value;
    }

    public String getcarac() {
        return str_carac;
    }

    public void setcarac(String str_Value) {
        str_carac = str_Value;
    }

    public int geth() {
        return altura;
    }

    public void seth(int h) {
        altura = h;
    }

    public int gethm() {
        return alturamin;
    }

    public void sethm(int h) {
        alturamin = h;
    }

    private Integer int_cant=0;
    private String str_carac;
    private int altura=0;
    private int alturamin=0;
    public nodo_letra Hizq=null;
    public nodo_letra Hder=null;
}
