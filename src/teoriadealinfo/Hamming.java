/**
 * Maneja el hamminizado y Deshamminizado
 * Practico de Maquina para Teoria de la Informacion
 * Alumnos: 
 *          - Guido Urquiza
 *          - Mauricio Soligo
 *          - Matias Casanova
 */
package teoriadealinfo;

import java.lang.Math.*;
import java.util.ArrayList;

public class Hamming {

    Byte g[][];
    Byte h[][];
    int block_size;

    /// Constructor  Init
    public Hamming(int block_size) {
        this.block_size = block_size;
        armarG();
        armarH(); //Hacer que copie la primera parte de G


    }

    public String desHaminizar(String s) {
        StringBuilder ent = new StringBuilder(s);
        int b_size = (int) Math.pow(2, Integer.parseInt(recuperar74(ent.substring(0, 7)), 2));
        ent.delete(0, 7);
        if (b_size != block_size) {
            this.block_size = b_size;
            armarG();
            armarH(); //Hacer que copie la primera parte de G
        }

        Integer n = block_size - 1;
        Integer c = logEnt(n + 1);
        Integer m = n - c;
        Integer iteraciones = s.length() / n;
        StringBuilder res = new StringBuilder(m * iteraciones);
        for (int i = 0; i < iteraciones; i++) {
            try {
                res.append(recuperar(ent.substring(0, n)));
                ent.delete(0, n);
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
        return res.toString();
    }

    public String haminizar(String s) {
        Integer n = block_size - 1;
        Integer c = logEnt(n + 1);
        Integer m = n - c;
        Integer iteraciones = s.length() / m;
        StringBuilder res = new StringBuilder(s.length() + 4 + c * iteraciones);
        StringBuilder ent = new StringBuilder(s);
        String size = Integer.toBinaryString(c);
        while (size.length() < 4) {
            size = "0" + size;
        }
        size = hamming74(size);
        res.append(size);
        for (int i = 0; i < iteraciones; i++) {
            res.append(proteger(ent.substring(0, m)));
            ent.delete(0, m);
        }
        if (ent.length() != 0) {
            while (ent.length() < m) {
                ent.append("0");
            }
            res.append(proteger(ent.substring(0, m)));
        }
        return res.toString();
    }

    public String recuperar(String s) throws Exception {
        int n = block_size - 1;
        int c = logEnt(n + 1);
        int m = n - c;
        int error;
        if (s.length() != n) {
            throw new Exception("Fallo en el tamaño del String a recuperar");
        }
        StringBuilder res = new StringBuilder(m);
        res.append(s.substring(0, m));
        if ((error = chequear(s)) != 0) {
            return arreglar(s, error).substring(0, m);
        }
        return res.substring(0, m).toString();
    }

    public String proteger(String s) {
        int n = block_size - 1;
        int c = logEnt(n + 1);
        int m = n - c;
        StringBuilder res = new StringBuilder(n);
        if (s.length() == m) {
            res.append(s);
            for (int j = m; j < n; j++) {
                Integer cod = 0;
                for (int i = 0; i < m; i++) {
                    cod += (s.charAt(i) - '0') * Integer.parseInt(g[i][j].toString());
                }
                res.append(Integer.toBinaryString(cod).charAt(Integer.toBinaryString(cod).length() - 1));
            }
        }
        return res.toString();
    }

    private int chequear(String s) throws Exception {
        int error;
        int n = block_size - 1;
        int c = logEnt(n + 1);
        int m = n - c;
        StringBuilder res = new StringBuilder(c);
        if (s.length() == n) {
            for (int j = 0; j < c; j++) {
                Integer cod = 0;
                for (int i = 0; i < n; i++) {
                    cod += (s.charAt(i) - '0') * Integer.parseInt(h[i][j].toString());
                }
                res.append(Integer.toBinaryString(cod).charAt(Integer.toBinaryString(cod).length() - 1));
            }
        } else {
            throw new Exception("String largo para descomponer!");
        }
        res.reverse();
        error = Integer.parseInt(res.toString(), 2);

        return error;
    }

    private String arreglar(String s, int error) {
        int l = logEnt(error);
        int n = block_size - 1;
        int c = logEnt(n + 1);
        int m = n - c;
        if (l != -1) {
            s = swap(s, m + l);
        } else {
            l = log(error);
            s = swap(s, error - l - 1);
        }
        return s;
    }

    private String swap(String s, int lugar) {
        StringBuilder res = new StringBuilder(s);
        String i = res.charAt(lugar) == '0' ? "1" : "0";
        res.replace(lugar, lugar + 1, i);
        return res.toString();
    }

    private void armarH() {
        int n = block_size - 1;
        int c = logEnt(n + 1);
        int m = n - c;
        h = new Byte[n][c];

        int cont = 0;
        int control = 0;
        int p = 1;
        int exp = 1;
        String pos_bin;
        for (int j = 0; j < c; j++) {
            for (int i = 0; i < n; i++) {
                if (i + 1 == p) {
                    p = ((Double) Math.pow(2, exp++)).intValue();
                } else {
                    pos_bin = Integer.toBinaryString(i + 1);
                    if (pos_bin.length() <= control) {
                        h[cont++][j] = new Byte("0");
                    } else {
                        h[cont++][j] = new Byte(pos_bin.substring(pos_bin.length() - control - 1, pos_bin.length() - control));
                    }
                }
            }
            p = 1;
            exp = 1;
            cont = 0;
            control++;
        }
        for (int i = m; i < n; i++) {
            for (int j = 0; j < c; j++) {
                h[i][j] = i - m != j ? new Byte("0") : new Byte("1");
            }
        }


    }

    private void armarG() {
        int n = block_size - 1;
        int c = logEnt(n + 1);
        int m = n - c;
        g = new Byte[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                g[i][j] = i != j ? new Byte("0") : new Byte("1");
            }
        }
        int cont = 0;
        int control = 0;
        int p = 1;
        int exp = 1;
        String pos_bin;
        for (int j = m; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (i + 1 == p) {
                    p = ((Double) Math.pow(2, exp++)).intValue();
                } else {
                    pos_bin = Integer.toBinaryString(i + 1);
                    if (pos_bin.length() <= control) {
                        g[cont++][j] = new Byte("0");
                    } else {
                        g[cont++][j] = new Byte(pos_bin.substring(pos_bin.length() - control - 1, pos_bin.length() - control));
                    }
                }
            }
            p = 1;
            exp = 1;
            cont = 0;
            control++;
        }
    }

    private int logEnt(int num) {
        if (num == 1024) {
            return 10;
        }
        if (num == 512) {
            return 9;
        }
        if (num == 256) {
            return 8;
        }
        if (num == 128) {
            return 7;
        }
        if (num == 64) {
            return 6;
        }
        if (num == 32) {
            return 5;
        }
        if (num == 16) {
            return 4;
        }
        if (num == 8) {
            return 3;
        }
        if (num == 4) {
            return 2;
        }
        if (num == 2) {
            return 1;
        }
        if (num == 1) {
            return 0;
        }

        return -1;
    }

    private int log(int num) {
        if (num <= 1024 && num >= 512) {
            return 10;
        }
        if (num >= 256) {
            return 9;
        }
        if (num >= 128) {
            return 8;
        }
        if (num >= 64) {
            return 7;
        }
        if (num >= 32) {
            return 6;
        }
        if (num >= 16) {
            return 5;
        }
        if (num >= 8) {
            return 4;
        }
        if (num >= 4) {
            return 3;
        }
        if (num >= 2) {
            return 2;
        }
        return -1;
    }

    private String hamming74(String s) {
        StringBuilder res = new StringBuilder(7);
        int[] parbit = new int[3];
        if (s.length() != 4) {
            return "";//IllegalArgumentException
        }
        parbit[0] = Integer.valueOf(s.substring(0, 1)) + Integer.valueOf(s.substring(1, 2)) + Integer.valueOf(s.substring(3, 4));
        parbit[1] = Integer.valueOf(s.substring(0, 1)) + Integer.valueOf(s.substring(2, 3)) + Integer.valueOf(s.substring(3, 4));
        parbit[2] = Integer.valueOf(s.substring(1, 2)) + Integer.valueOf(s.substring(2, 3)) + Integer.valueOf(s.substring(3, 4));
        String bs = Integer.toBinaryString(parbit[0]);
        res.append(bs.charAt(bs.length() - 1));
        bs = Integer.toBinaryString(parbit[1]);
        res.append(bs.charAt(bs.length() - 1));
        res.append(s.charAt(0));
        bs = Integer.toBinaryString(parbit[2]);
        res.append(bs.charAt(bs.length() - 1));
        res.append(s.subSequence(1, 4));
        return res.toString();
    }

    private String recuperar74(String s) {
        StringBuilder res = new StringBuilder(4);
        if (s.length() != 7) {
            return ""; //IllegalArgumentException
        }
        int parbit[] = new int[3];
        parbit[0] = Integer.valueOf(s.substring(0, 1)) + Integer.valueOf(s.substring(2, 3))
                + Integer.valueOf(s.substring(4, 5)) + Integer.valueOf(s.substring(6, 7));
        parbit[1] = Integer.valueOf(s.substring(1, 2)) + Integer.valueOf(s.substring(2, 3))
                + Integer.valueOf(s.substring(5, 6)) + Integer.valueOf(s.substring(6, 7));
        parbit[2] = Integer.valueOf(s.substring(3, 4)) + Integer.valueOf(s.substring(4, 5))
                + Integer.valueOf(s.substring(5, 6)) + Integer.valueOf(s.substring(6, 7));
        String sb = Integer.toBinaryString(parbit[0]);
        int sindrome = Integer.valueOf(sb.substring(sb.length() - 1, sb.length()));
        sb = Integer.toBinaryString(parbit[1]);
        sindrome += Integer.valueOf(sb.substring(sb.length() - 1, sb.length())) * 2;
        sb = Integer.toBinaryString(parbit[2]);
        sindrome += Integer.valueOf(sb.substring(sb.length() - 1, sb.length())) * 4;
        if (sindrome != 0) {
            s=swap(s,sindrome-1);
        }
        res.append(s.charAt(2));
        res.append(s.substring(4, 7));
        return res.toString();
    }

    public ArrayList<String> mostrarH() {
        ArrayList<String> res = new ArrayList<String>();
        int n = block_size - 1;
        int c = logEnt(n + 1);
        int m = n - c;

        for (int i = 0; i < n; i++) {
            StringBuilder sbi = new StringBuilder(n);
            for (int j = 0; j < c; j++) {
                sbi.append(h[i][j]);
            }
            res.add(sbi.toString());
        }
        return res;
    }

    public ArrayList<String> mostrarG() {
        ArrayList<String> res = new ArrayList<String>();
        int n = block_size - 1;
        int c = logEnt(n + 1);
        int m = n - c;

        for (int i = 0; i < m; i++) {
            StringBuilder sbi = new StringBuilder(n);
            for (int j = 0; j < n; j++) {
                sbi.append(g[i][j]);
            }
            res.add(sbi.toString());
        }
        return res;
    }
}
