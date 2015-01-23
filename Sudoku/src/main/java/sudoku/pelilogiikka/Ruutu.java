package sudoku.pelilogiikka;

public class Ruutu {

    private int arvo;
    private int rivi;
    private int sarake;

    public Ruutu(int arvo, int rivi, int sarake) {
        arvo = 0;
        this.rivi = rivi;
        this.sarake = sarake;
    }

    public int getRivi() {
        return rivi;
    }

    public int getSarake() {
        return sarake;
    }
    

    public int getArvo() {
        return arvo;
    }

    public boolean tarkistaNumero(int numero) {
        return numero < 10 && numero > 0;
    }

    public boolean setArvo(int arvo) {
        if (tarkistaNumero(arvo)) {
            this.arvo = arvo;
            return true;
        } else {
            return false;
        }
    }

    public void tyhjenna() {
        this.arvo = 0;
    }

    public boolean vapaa() {
        return this.arvo == 0;
    }

}
