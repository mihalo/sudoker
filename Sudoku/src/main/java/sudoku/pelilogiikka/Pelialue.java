package sudoku.pelilogiikka;

import java.util.*;

public class Pelialue {

    private Ruutu[][] ruudukko;
    private Ruutu[][] ratkaistuRuudukko;

    /**
     * Luo sekä alustaa pelialueen taulukot.
     */
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

    /**
     * Metodi palauttaa pelialueen ruudut halutulta riviltä.
     * 
     * @param rivi  Rivi jolta ruudut haetaan
     * @return rivin ruudut
     */
    public ArrayList<Ruutu> rivinRuudut(int rivi) {
        ArrayList<Ruutu> ruudut = new ArrayList();
        for (int sarake = 0; sarake < 9; sarake++) {
            ruudut.add(ruudukko[rivi][sarake]);
        }
        return ruudut;
    }

    /**
     * Metodi palauttaa pelialueen ruudut halutusta sarakkeesta.
     * 
     * @param sarake Sarake jolta ruudut haetaan
     * @return sarakkeen ruudut
     */
    public ArrayList<Ruutu> sarakkeenRuudut(int sarake) {
        ArrayList<Ruutu> ruudut = new ArrayList();
        for (int rivi = 0; rivi < 9; rivi++) {
            ruudut.add(ruudukko[rivi][sarake]);
        }
        return ruudut;
    }

    /**
     * Metodi palauttaa pelialueen ruudut halutusta osaruudukosta, joka on 3x3 kokoinen.
     * 
     * @param rivi
     * @param sarake
     * @return osaruudukon ruudut
     */
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

    /**
     * Metodi asettaa numerot ruudukkoon taulukosta.
     * @param numerot numerot järjestyksessä vasemmalta oikealle, ylhäältä alas.
     */
    public void asetaNumerot(int[] numerot) {
        int n = 0;
        for (Ruutu[] ruudukko1 : ruudukko) {
            for (int j = 0; j < ruudukko.length; j++) {
                ruudukko1[j].setArvo(numerot[n]);
                n++;
            }
        }
    }

    private boolean tarkistaIndeksi(int indeksi) {
        return indeksi > 8 || indeksi < 0;
    }

    private TreeSet<Integer> rivinNumerotApu(int rivi) {
        TreeSet<Integer> numerot = new TreeSet();
        for (int i = 0; i < ruudukko.length; i++) {
            if (!ruudukko[rivi][i].vapaa()) {
                numerot.add(ruudukko[rivi][i].getArvo());
            }
        }
        return numerot;
    }

    /**
     * Metodi palauttaa halutun rivin numerot TreeSetissä.
     * @param rivi haluttu rivi
     * @return rivin numerot
     */
    public TreeSet<Integer> rivinNumerot(int rivi) {
        if (tarkistaIndeksi(rivi)) {
            return new TreeSet();
        }
        return rivinNumerotApu(rivi);
    }

    private TreeSet<Integer> sarakkeenNumerotApu(int sarake) {
        TreeSet<Integer> numerot = new TreeSet();
        for (Ruutu[] ruudukko1 : ruudukko) {
            if (!ruudukko1[sarake].vapaa()) {
                numerot.add(ruudukko1[sarake].getArvo());
            }
        }
        return numerot;
    }

    /**
     * Metodi palauttaa halutun sarakkeen numerot TreeSetissä.
     * @param sarake haluttu sarake
     * @return sarakkeen numerot
     */
    public TreeSet<Integer> sarakkeenNumerot(int sarake) {
        if (tarkistaIndeksi(sarake)) {
            return new TreeSet();
        }
        return sarakkeenNumerotApu(sarake);
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

    private TreeSet<Integer> osaruudukonNumerotApu(int x, int y) {
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

    /**
     * Metodi palauttaa halutun osaruudukon numerot TreeSetissä.
     * @param rivi haluttu rivi
     * @param sarake haluttu sarake
     * @return osaruudukon numerot
     */
    public TreeSet<Integer> osaruudukonNumerot(int rivi, int sarake) {
        if (tarkistaIndeksi(rivi) || tarkistaIndeksi(sarake)) {
            return new TreeSet();
        }
        return osaruudukonNumerotApu(rivi, sarake);
    }

    /**
     * Metodi palauttaa numerot osaruudukosta ArrayDequessa
     * @param x rivi
     * @param y sarake
     * @return numerot osaruudukosta
     */
    public ArrayDeque<Integer> numerotOsaruudukosta(int x, int y) {
        ArrayDeque<Integer> numerot = new ArrayDeque();
        for (int rivi = vasenYlakulma(x); rivi < vasenYlakulma(x) + 3; rivi++) {
            for (int sarake = vasenYlakulma(y); sarake < vasenYlakulma(y) + 3; sarake++) {
                numerot.add(ruudukko[rivi][sarake].getArvo());
            }
        }
        return numerot;
    }

    /**
     * Metodi asettaa numeron pelialueeseen
     * @param rivi rivi johon numero asetetaan
     * @param sarake sarake johon numero asetetaan
     * @param numero numero joka asetetaan
     * @return onnistuiko asettaminen
     */
    public boolean asetaNumero(int rivi, int sarake, int numero) {
        if (tarkistaIndeksi(rivi) || tarkistaIndeksi(sarake)) {
            return false;
        }
        return ruudukko[rivi][sarake].setArvo(numero);
    }

    /**
     * Metodi tyhjentaa ruudun
     * @param rivi rivi
     * @param sarake sarake
     */
    public void tyhjennaRuutu(int rivi, int sarake) {
        if (!tarkistaIndeksi(rivi) && !tarkistaIndeksi(sarake)) {
            ruudukko[rivi][sarake].tyhjenna();
        }
    }

    /**
     * Metodi palauttaa numeron, joka on pelialueella halutussa kohdassa
     * @param rivi rivi jolta haetaan
     * @param sarake sarake josta haetaan
     * @return numero TAI -1 jos yritetään hakea taulukon ulkopuolelta
     */
    public int numero(int rivi, int sarake) {
        if (!tarkistaIndeksi(rivi) && !tarkistaIndeksi(sarake)) {
            return ruudukko[rivi][sarake].getArvo();
        } else {
            return -1;
        }
    }

    /**
     * Palauttaa ruutu olion halutusta kohtaa pelialuetta
     * @param rivi rivi jolta haetaan
     * @param sarake sarake josta haetaan
     * @return ruutu tai null jos haetaan taulukon ulkopuolelta
     */
    public Ruutu ruutu(int rivi, int sarake) {
        if (!tarkistaIndeksi(rivi) && !tarkistaIndeksi(sarake)) {
            return ruudukko[rivi][sarake];
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

    /**
     * Palauttaa satunnaisen tyhjän paikan sijainnin pelialueelta.
     * @return int[] jossa ensin rivi sitten sarake
     */
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

    public Ruutu[][] getRuudukko() {
        return ruudukko;
    }
    
   
}
