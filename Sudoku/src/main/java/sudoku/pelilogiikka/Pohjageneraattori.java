package sudoku.pelilogiikka;

import java.util.*;

public class Pohjageneraattori {

    private Pelialue p;
    private int maara;
    private ArrayList<int[]> vapaatPaikat;

    public Pohjageneraattori(int vaikeustaso) {
        while (true) {
            this.p = new Pelialue();
            alustaVapaatPaikat();
            luoPohja(vaikeustaso(vaikeustaso));
            ratkaisija();
            System.out.println(maara);
            if (maara == 1) {
                break;
            }
        }
    }
    
    public int vaikeustaso(int vaikeustaso) {
        if (vaikeustaso < 1) {
            return 32;
        } else if (vaikeustaso < 2) {
            return 30;
        } else if (vaikeustaso < 3) {
            return 28;
        } else {
            return 0;
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
                Ruutu r = new Ruutu();
                r.setArvo(p.numero(i,j));
                ratkaisu[i][j] = r;
            }
        }
        p.setRatkaistuRuudukko(ratkaisu);
    }

    private void luoPohja(int vaikeustaso) {
        taytaRuudukko(0, 0);
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
    }

    private ArrayList<Integer> kaytettavatNumerot(int rivi, int sarake) {
        ArrayList<Integer> kaytettavat = new ArrayList();
        for (int j = 1; j < 10; j++) {
            kaytettavat.add(j);
        }
        TreeSet<Integer> numerot = new TreeSet();
        numerot.addAll(p.rivinNumerot(rivi));
        numerot.addAll(p.sarakkeenNumerot(sarake));
        numerot.addAll(p.osaruudukonNumerot(rivi, sarake));
        kaytettavat.removeAll(numerot);

        Collections.shuffle(kaytettavat);

        return kaytettavat;
    }

    private void ratkaisija() {
        maara = 0;
        ratkaise(0, 0);
    }

    public Pelialue getPelialue() {
        return this.p;
    }

    private void taytaRuudukko(int rivi, int sarake) {
        if (rivi == 9) {
            return;
        }
        if (!p.ruutu(rivi, sarake).vapaa()) {
            taytaRuudukko(sarake == 8 ? (rivi + 1) : rivi, (sarake + 1) % 9);
        } else {
            ArrayList<Integer> kaytettavat = kaytettavatNumerot(rivi, sarake);
            for (int i = 0; i < kaytettavat.size(); i++) {
                if (p.tarkistaSiirto(rivi, sarake, kaytettavat.get(i)).isEmpty()) {
                    p.asetaNumero(rivi, sarake, kaytettavat.get(i));
                    taytaRuudukko(sarake == 8 ? (rivi + 1) : rivi, (sarake + 1) % 9);
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
            ArrayList<Integer> kaytettavat = kaytettavatNumerot(rivi, sarake);
            for (int i = 0; i < kaytettavat.size(); i++) {
                p.asetaNumero(rivi, sarake, kaytettavat.get(i));
                ratkaise(sarake == 8 ? (rivi + 1) : rivi, (sarake + 1) % 9);
                p.tyhjennaRuutu(rivi, sarake);
            }
        }
    }

}
