package sudoku.pelilogiikka;

import java.util.*;

public class Pelialue {

    private Ruutu[][] ruudukko;

    public Pelialue() {
        ruudukko = new Ruutu[9][9];
        for (int i = 0; i < ruudukko.length; i++) {
            for (int j = 0; j < ruudukko.length; j++) {
                ruudukko[i][j] = new Ruutu();
            }
        }
    }

    public void asetaNumerot(int[] numerot) {
        int n = 0;
        for (int i = 0; i < ruudukko.length; i++) {
            for (int j = 0; j < ruudukko.length; j++) {
                ruudukko[i][j].setArvo(numerot[n]);
                n++;
            }
        }
    }

    public TreeSet<Integer> rivinNumerot(int rivi) {
        TreeSet<Integer> numerot = new TreeSet();

        if (rivi > 8 || rivi < 0) {
            return numerot;
        }

        for (int i = 0; i < ruudukko.length; i++) {
            if (!ruudukko[rivi][i].vapaa()) {
                numerot.add(ruudukko[rivi][i].getArvo());
            }
        }

        return numerot;
    }

    public boolean tarkistaRivit() {
        for (int i = 0; i < 9; i++) {
            if (rivinNumerot(i).size() != 9) {
                return false;
            }
        }
        return true;
    }

    public TreeSet<Integer> sarakkeenNumerot(int sarake) {
        TreeSet<Integer> numerot = new TreeSet();

        if (sarake > 8 || sarake < 0) {
            return numerot;
        }

        for (int i = 0; i < ruudukko.length; i++) {
            if (!ruudukko[i][sarake].vapaa()) {
                numerot.add(ruudukko[i][sarake].getArvo());
            }
        }

        return numerot;
    }

    public boolean tarkistaSarakkeet() {
        for (int i = 0; i < 9; i++) {
            if (sarakkeenNumerot(i).size() != 9) {
                return false;
            }
        }
        return true;
    }

    private int vasenYlakulma(int kohta) {
        if (kohta < 3) {
            return 0;
        } else if (kohta < 6) {
            return 3;
        } else {
            return 6;
        }
    }

    public TreeSet<Integer> osaruudukonNumerot(int x, int y) {
        TreeSet<Integer> numerot = new TreeSet();
        for (int i = vasenYlakulma(x); i < vasenYlakulma(x) + 3; i++) {
            for (int j = vasenYlakulma(y); j < vasenYlakulma(y) + 3; j++) {
                if (!ruudukko[i][j].vapaa()) {
                    numerot.add(ruudukko[i][j].getArvo());
                }
            }
        }
        return numerot;
    }

    public boolean tarkistaOsaruudukot() {
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (osaruudukonNumerot(i, j).size() != 9) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean tarkista() {
        return tarkistaRivit() && tarkistaSarakkeet() && tarkistaOsaruudukot();
    }

    public void tulostaPelialue() {
        for (int i = 0; i < ruudukko.length; i++) {
            for (int j = 0; j < ruudukko.length; j++) {
                if (j == 2 || j == 5) {
                    System.out.print(ruudukko[i][j].getArvo() + "|");
                } else {
                    System.out.print(ruudukko[i][j].getArvo() + " ");
                }
            }

            if (i == 2 || i == 5) {
                System.out.println("\n–––––––––––––––––");
            } else {
                System.out.println();
            }

        }
    }
    
    public Ruutu[][] kaikkiNumerot() {
        return ruudukko;
    }
    
    public boolean asetaNumero(int x, int y, int numero) {
        if (x < 0 || y < 0 || x > 9 || y > 9 || numero < 0 || numero > 9) {
            return false;
        }
        ruudukko[x][y].setArvo(numero);
        return true;
    }
}
