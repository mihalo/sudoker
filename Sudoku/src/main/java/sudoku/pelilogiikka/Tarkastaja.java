package sudoku.pelilogiikka;

import java.util.*;

/**
 * Tämä luokka hoitaa pelin tarkastamisen pelatessa sekä pohjaa luodessa.
 */
public class Tarkastaja {
    
    /**
     * Metodi tarkastaa pelialueen, että onko se täytetty sääntöjen mukaisesti.
     * 
     * @param pelialue tarkastettava pelialue
     * @return true/false
     */
    public boolean tarkasta(Pelialue pelialue) {
        return tarkistaRivit(pelialue) && tarkistaSarakkeet(pelialue) && tarkistaOsaruudukot(pelialue);
    }

    private boolean tarkistaNumerot(TreeSet<Integer> numerot) {
        return numerot.size() == 9;
    }
    
    /**
     * Metodi palauttaa ruutujen numerot TreeSetissä.
     * 
     * @param ruudut ruudut
     * @return numerot
     */
    public TreeSet<Integer> ruudutNumeroiksi(ArrayList<Ruutu> ruudut) {
        TreeSet<Integer> numerot = new TreeSet();
        for (Ruutu r : ruudut) {
            numerot.add(r.getArvo());
        }
        return numerot;
    }
    
    private boolean tarkistaRivit(Pelialue pelialue) {
        for (int i = 0; i < 9; i++) {
            if (!tarkistaNumerot(ruudutNumeroiksi(pelialue.rivinRuudut(i)))) {
                return false;
            }
        }
        return true;
    }

    private boolean tarkistaSarakkeet(Pelialue pelialue) {
        for (int i = 0; i < 9; i++) {
            if (!tarkistaNumerot(ruudutNumeroiksi(pelialue.sarakkeenRuudut(i)))) {
                return false;
            }
        }
        return true;
    }

    private boolean tarkistaOsaruudukot(Pelialue pelialue) {
        for (int rivi = 0; rivi < 9; rivi += 3) {
            for (int sarake = 0; sarake < 9; sarake += 3) {
                if (!tarkistaNumerot(ruudutNumeroiksi(pelialue.osaruudukonRuudut(rivi, sarake)))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Tarkastaa, että numero on sallittu.
     * 
     * @param numero tarkastettava numero
     * @return true/false
     */
    public boolean tarkastaNumero(int numero) {
        return !(numero < 1 || numero > 9);
    }

    /**
     * Tarkastaa, että indeksi on sallittu.
     * 
     * @param indeksi tarkastettava indeksi
     * @return true/false
     */
    public boolean tarkastaIndeksi(int indeksi) {
        return !(indeksi > 8 || indeksi < 0);
    }

    private ArrayList<Ruutu> tarkistaSamatNumerot(ArrayList<Ruutu> ruudut, int numero) {
        ArrayList<Ruutu> vaarat = new ArrayList();
        for (Ruutu r : ruudut) {
            if (r.getArvo() == numero) {
                vaarat.add(r);
            }
        }
        return vaarat;
    }    

    /**
     * Tarkastaa pelissä tapahtuvan siirron, sekä palauttaa virheelliset ruudut tämän jälkeen.
     * 
     * @param pelialue pelialue
     * @param rivi rivi johon numero asetetaan
     * @param sarake sarake johon numero asetetaan
     * @param numero numero joka asetetaan
     * @return väärät ruudut
     */
    public ArrayList<Ruutu> tarkistaSiirto(Pelialue pelialue, int rivi, int sarake, int numero) {
        ArrayList<Ruutu> vaarat = new ArrayList();
        vaarat.addAll(tarkistaSamatNumerot(pelialue.rivinRuudut(rivi), numero));
        vaarat.addAll(tarkistaSamatNumerot(pelialue.sarakkeenRuudut(sarake), numero));
        vaarat.addAll(tarkistaSamatNumerot(pelialue.osaruudukonRuudut(rivi, sarake), numero));
        return vaarat;
    }
    
    /**
     * Palauttaa kaikki numerot, jotka on mahdollista sijoittaa haluttuun kohtaan satunnaisessa järjestyksessä.
     * 
     * @param pelialue pelialue
     * @param rivi rivi joka halutaaan
     * @param sarake sarake joka halutaan
     * @return kaytettävät numerot
     */
    public ArrayList<Integer> kaytettavatNumerot(Pelialue pelialue, int rivi, int sarake) {
        ArrayList<Integer> numerot = new ArrayList();
        for (int i = 1; i <= 9; i++) {
            numerot.add(i);
        }
        TreeSet<Integer> kaytettavat = new TreeSet();
        kaytettavat.addAll(ruudutNumeroiksi(pelialue.rivinRuudut(rivi)));
        kaytettavat.addAll(ruudutNumeroiksi(pelialue.sarakkeenRuudut(sarake)));
        kaytettavat.addAll(ruudutNumeroiksi(pelialue.osaruudukonRuudut(rivi, sarake)));
        numerot.removeAll(kaytettavat);
        Collections.shuffle(numerot);
        return numerot;
    }
}
