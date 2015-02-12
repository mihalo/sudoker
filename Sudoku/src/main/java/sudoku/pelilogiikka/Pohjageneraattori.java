package sudoku.pelilogiikka;

import java.util.*;

/**
 *  Luo sudoku peliin pohjan sekä ratkaisun.
 *  Pohja on kelvollinen vain jos siinä on yksi mahdollinen ratkaisu.
 */
public class Pohjageneraattori {

    private Pelialue p;
    private int maara;
    private ArrayList<int[]> vapaatPaikat;
    private final Tarkastaja tarkastaja = new Tarkastaja();

    /**
     * Luo sudoku pohjan, ottaen huomioon vaikeustason
     * 
     * @param vaikeustaso
     */
    public Pohjageneraattori(int vaikeustaso) {
        vaikeustaso(vaikeustaso);
    }

    private void vaikeustaso(int vaikeustaso) {
        if (vaikeustaso == 1) {
            luoPohja(32);
        } else if (vaikeustaso == 2) {
            luoPohja(25);
        } else if (vaikeustaso == 3) {
            luoPohjaVaikea();
        }
    }

    private void alustaVapaatPaikat() {
        this.vapaatPaikat = new ArrayList();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                vapaatPaikat.add(new int[]{i, j});
            }
        }
    }

    private void kopioiRatkaisu() {
        Ruutu[][] ratkaisu = new Ruutu[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                ratkaisu[i][j] = new Ruutu(p.numero(i,j),i,j);
            }
        }
        p.setRatkaistuRuudukko(ratkaisu);
    }

    private void luoPohja(int vaikeustaso) {
        while (true) {
            this.p = new Pelialue();
            alustaVapaatPaikat();
            taytaRuudukko(0, 0, 9);
            kopioiRatkaisu();
            int i = 0;
            while (i < 81 - vaikeustaso) {
                Collections.shuffle(vapaatPaikat);
                int[] paikka = vapaatPaikat.get(0);
                int paikanArvo = p.numero(paikka[0], paikka[1]);
                p.tyhjennaRuutu(paikka[0], paikka[1]);
                ratkaisija();
                if (maara > 1) {
                    p.asetaNumero(paikka[0], paikka[1], paikanArvo);
                } else {
                    i++;
                    vapaatPaikat.remove(0);
                }
            }
            ratkaisija();
            if (maara == 1) {
                break;
            }
        }
    }

    private void luoPohjaVaikea() {
        this.p = new Pelialue();
        alustaVapaatPaikat();
        for (int i = 0; i < 9; i += 2) {
            taytaRuudukko(i, i, i + 1);
        }
        taytaRuudukko(0, 0, 9);
        kopioiRatkaisu();
        boolean ok = true;
        Collections.shuffle(vapaatPaikat);
        while (ok) {
            boolean kierros = true;
            for (int[] paikka : vapaatPaikat) {
                int arvo = p.numero(paikka[0], paikka[1]);
                p.tyhjennaRuutu(paikka[0], paikka[1]);
                ratkaisija();
                if (maara == 1) {
                    kierros = false;
                    vapaatPaikat.remove(paikka);
                    break;
                } else {
                    p.asetaNumero(paikka[0], paikka[1], arvo);
                }
            }
            if (kierros) {
                ok = false;
            }
        }
    }

    /**
     * Metodi kokeilee ratkaista luotua pohjaa sekä tarkistaa onko pohjassa enemmän kuin yksi ratkaisu
     */
    public void ratkaisija() {
        maara = 0;
        ratkaise(0, 0);
    }

    public Pelialue getPelialue() {
        return this.p;
    }

    private void taytaRuudukko(int rivi, int sarake, int raja) {
        if (rivi == raja) {
            return;
        }
        if (!p.ruutu(rivi, sarake).vapaa()) {
            taytaRuudukko(sarake == 8 ? (rivi + 1) : rivi, (sarake + 1) % 9, raja);
        } else {
            ArrayList<Integer> kaytettavat = tarkastaja.kaytettavatNumerot(p, rivi, sarake);
            for (Integer kaytettavat1 : kaytettavat) {
                if (tarkastaja.tarkistaSiirto(p,rivi,sarake,kaytettavat1).isEmpty()) {
                    p.asetaNumero(rivi, sarake, kaytettavat1);
                    ratkaisija();
                    if (maara >= 1) {
                        taytaRuudukko(sarake == 8 ? (rivi + 1) : rivi, (sarake + 1) % 9, raja);
                    } else {
                        p.tyhjennaRuutu(rivi, sarake);
                    }
                }
            }
        }
    }

    private void ratkaise(int rivi, int sarake) {
        if (maara > 1) {
            return;
        }
        if (rivi == 9) {
            maara++;
            return;
        }
        if (!p.ruutu(rivi, sarake).vapaa()) {
            ratkaise(sarake == 8 ? (rivi + 1) : rivi, (sarake + 1) % 9);
        } else {
            ArrayList<Integer> kaytettavat = tarkastaja.kaytettavatNumerot(p, rivi, sarake);
            for (Integer kaytettavat1 : kaytettavat) {
                p.asetaNumero(rivi, sarake, kaytettavat1);
                ratkaise(sarake == 8 ? (rivi + 1) : rivi, (sarake + 1) % 9);
                p.tyhjennaRuutu(rivi, sarake);
            }
        }
    }

}
