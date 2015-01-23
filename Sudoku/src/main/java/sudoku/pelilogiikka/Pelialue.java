package sudoku.pelilogiikka;

import java.util.*;

public class Pelialue {

    public Ruutu[][] ruudukko;
    public Ruutu[][] ratkaistuRuudukko;

    public Pelialue() {
        ruudukko = new Ruutu[9][9];
        ratkaistuRuudukko = new Ruutu[9][9];
        alustaTaulukot();
    }

    private void alustaTaulukot() {
        for (int rivi = 0; rivi < ruudukko.length; rivi++) {
            for (int sarake = 0; sarake < ruudukko.length; sarake++) {
                ruudukko[rivi][sarake] = new Ruutu(0, rivi, sarake);
                ratkaistuRuudukko[rivi][sarake] = new Ruutu(0, rivi, sarake);
            }
        }
    }

    public ArrayList<Ruutu> rivinRuudut(int rivi) {
        ArrayList<Ruutu> ruudut = new ArrayList();
        for (int sarake = 0; sarake < 9; sarake++) {
            ruudut.add(ruudukko[rivi][sarake]);
        }
        return ruudut;
    }

    public ArrayList<Ruutu> sarakkeenRuudut(int sarake) {
        ArrayList<Ruutu> ruudut = new ArrayList();
        for (int rivi = 0; rivi < 9; rivi++) {
            ruudut.add(ruudukko[rivi][sarake]);
        }
        return ruudut;
    }

    public ArrayList<Ruutu> osaruudukonRuudut(int rivi, int sarake) {
        ArrayList<Ruutu> numerot = new ArrayList();
        for (int i = vasenYlakulma(rivi); i < vasenYlakulma(rivi) + 3; i++) {
            for (int j = vasenYlakulma(sarake); j < vasenYlakulma(sarake) + 3; j++) {
                if (!ruudukko[i][j].vapaa()) {
                    numerot.add(ruudukko[i][j]);
                }
            }
        }
        return numerot;
    }

    public void asetaNumerot(int[] numerot) {
        int n = 0;
        for (Ruutu[] ruudukko1 : ruudukko) {
            for (int j = 0; j < ruudukko.length; j++) {
                ruudukko1[j].setArvo(numerot[n]);
                n++;
            }
        }
    }

    public boolean tarkistaIndeksi(int indeksi) {
        return indeksi > 8 || indeksi < 0;
    }

    public boolean tarkistaNumero(int numero) {
        return numero > 9 || numero < 0;
    }

    public TreeSet<Integer> rivinNumerotApu(int rivi) {
        TreeSet<Integer> numerot = new TreeSet();
        for (int i = 0; i < ruudukko.length; i++) {
            if (!ruudukko[rivi][i].vapaa()) {
                numerot.add(ruudukko[rivi][i].getArvo());
            }
        }
        return numerot;
    }

    public TreeSet<Integer> rivinNumerot(int rivi) {
        if (tarkistaIndeksi(rivi)) {
            return new TreeSet();
        }
        return rivinNumerotApu(rivi);
    }

    public boolean tarkistaRivit() {
        for (int rivi = 0; rivi < 9; rivi++) {
            if (rivinNumerot(rivi).size() != 9) {
                return false;
            }
        }

        return true;
    }

    public TreeSet<Integer> sarakkeenNumerotApu(int sarake) {
        TreeSet<Integer> numerot = new TreeSet();
        for (Ruutu[] ruudukko1 : ruudukko) {
            if (!ruudukko1[sarake].vapaa()) {
                numerot.add(ruudukko1[sarake].getArvo());
            }
        }
        return numerot;
    }

    public TreeSet<Integer> sarakkeenNumerot(int sarake) {
        if (tarkistaIndeksi(sarake)) {
            return new TreeSet();
        }
        return sarakkeenNumerotApu(sarake);
    }

    public boolean tarkistaSarakkeet() {
        for (int sarake = 0; sarake < 9; sarake++) {
            if (sarakkeenNumerot(sarake).size() != 9) {
                return false;
            }
        }
        return true;
    }

    public int vasenYlakulma(int kohta) {
        if (kohta < 3) {
            return 0;
        } else if (kohta < 6) {
            return 3;
        } else {
            return 6;
        }
    }

    public TreeSet<Integer> osaruudukonNumerotApu(int x, int y) {
        TreeSet<Integer> numerot = new TreeSet();
        for (int rivi = vasenYlakulma(x); rivi < vasenYlakulma(x) + 3; rivi++) {
            for (int sarake = vasenYlakulma(y); sarake < vasenYlakulma(y) + 3; sarake++) {
                if (!ruudukko[rivi][sarake].vapaa()) {
                    numerot.add(ruudukko[rivi][sarake].getArvo());
                }
            }
        }
        return numerot;
    }

    public TreeSet<Integer> osaruudukonNumerot(int rivi, int sarake) {
        if (tarkistaIndeksi(rivi) || tarkistaIndeksi(sarake)) {
            return new TreeSet();
        }
        return osaruudukonNumerotApu(rivi, sarake);
    }

    public ArrayDeque<Integer> numerotOsaruudukosta(int x, int y) {
        ArrayDeque<Integer> numerot = new ArrayDeque();
        for (int rivi = vasenYlakulma(x); rivi < vasenYlakulma(x) + 3; rivi++) {
            for (int sarake = vasenYlakulma(y); sarake < vasenYlakulma(y) + 3; sarake++) {
                numerot.add(ruudukko[rivi][sarake].getArvo());
            }
        }
        return numerot;
    }

    public boolean tarkistaOsaruudukot() {
        for (int rivi = 0; rivi < 9; rivi += 3) {
            for (int sarake = 0; sarake < 9; sarake += 3) {
                if (osaruudukonNumerot(rivi, sarake).size() != 9) {
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
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(ruudukko[i][j].getArvo());
            }
        }
    }

    public boolean asetaNumero(int rivi, int sarake, int numero) {
        if (tarkistaIndeksi(rivi) || tarkistaIndeksi(sarake)) {
            return false;
        }
        return ruudukko[rivi][sarake].setArvo(numero);
    }

    public void tyhjennaRuutu(int rivi, int sarake) {
        if (!tarkistaIndeksi(rivi) && !tarkistaIndeksi(sarake)) {
            ruudukko[rivi][sarake].tyhjenna();
        }
    }

    public ArrayDeque<Integer> tarkistaSiirto(int rivi, int sarake, int numero) {
        if (numero == 0) {
            return new ArrayDeque();
        }
        if (tarkistaNumero(numero) || tarkistaIndeksi(rivi) || tarkistaIndeksi(sarake)) {
            return new ArrayDeque();
        }
        ArrayDeque<Integer> kohdat = new ArrayDeque();
        kohdat.addAll(tarkistaSiirronRivi(rivi, sarake, numero));
        kohdat.addAll(tarkistaSiirronSarake(rivi, sarake, numero));
        kohdat.addAll(tarkistaSiirronOsaruudukko(rivi, sarake, numero));
        return kohdat;
    }

    public ArrayDeque<Integer> tarkistaSiirronOsaruudukkoApu(int x, int y, int numero) {
        ArrayDeque<Integer> kohdat = new ArrayDeque();
        for (int i = vasenYlakulma(x); i < vasenYlakulma(x) + 3; i++) {
            for (int j = vasenYlakulma(y); j < vasenYlakulma(y) + 3; j++) {
                if (i == x && j == y) {
                    continue;
                }
                if (ruudukko[i][j].getArvo() == numero) {
                    kohdat.add(i);
                    kohdat.add(j);
                }
            }
        }
        return kohdat;
    }

    public ArrayDeque<Integer> tarkistaSiirronOsaruudukko(int x, int y, int numero) {
        if (tarkistaIndeksi(x) || tarkistaIndeksi(y)) {
            return new ArrayDeque();
        }
        return tarkistaSiirronOsaruudukkoApu(x, y, numero);
    }

    public ArrayDeque<Integer> tarkistaSiirronSarakeApu(int x, int y, int numero) {
        ArrayDeque<Integer> kohdat = new ArrayDeque();
        for (int i = 0; i < 9; i++) {
            if (i == x) {
                continue;
            }
            if (ruudukko[i][y].getArvo() == numero) {
                kohdat.add(i);
                kohdat.add(y);
            }
        }
        return kohdat;
    }

    public ArrayDeque<Integer> tarkistaSiirronSarake(int x, int y, int numero) {
        if (tarkistaIndeksi(x) || tarkistaIndeksi(y)) {
            return new ArrayDeque();
        }
        return tarkistaSiirronSarakeApu(x, y, numero);
    }

    public ArrayDeque<Integer> tarkistaSiirronRiviApu(int x, int y, int numero) {
        ArrayDeque<Integer> kohdat = new ArrayDeque();
        for (int i = 0; i < 9; i++) {
            if (i == y) {
                continue;
            }
            if (ruudukko[x][i].getArvo() == numero) {
                kohdat.add(x);
                kohdat.add(i);
            }
        }
        return kohdat;
    }

    public ArrayDeque<Integer> tarkistaSiirronRivi(int x, int y, int numero) {
        if (tarkistaIndeksi(x) || tarkistaIndeksi(y)) {
            return new ArrayDeque();
        }
        return tarkistaSiirronRiviApu(x, y, numero);
    }

    public int numero(int rivi, int sarake) {
        if (!tarkistaIndeksi(rivi) && !tarkistaIndeksi(sarake)) {
            return ruudukko[rivi][sarake].getArvo();
        } else {
            return -1;
        }
    }

    public Ruutu ruutu(int x, int y) {
        if (!tarkistaIndeksi(x) && !tarkistaIndeksi(y)) {
            return ruudukko[x][y];
        } else {
            return null;
        }
    }

    public void setRuudukko(Ruutu[][] r) {
        this.ruudukko = r;
    }

    public void setRatkaistuRuudukko(Ruutu[][] r) {
        this.ratkaistuRuudukko = r;
    }

    public int getRatkaisu(int rivi, int sarake) {
        return ratkaistuRuudukko[rivi][sarake].getArvo();
    }

    public int[] vihje() {
        ArrayList<int[]> tyhjatPaikat = new ArrayList();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (ruudukko[i][j].vapaa()) {
                    tyhjatPaikat.add(new int[]{i, j});
                }
            }
        }
        Collections.shuffle(tyhjatPaikat);
        return tyhjatPaikat.get(0);
    }
}
