package sudoku.pelilogiikka;

public class Ruutu {

    private int arvo;
    private int rivi;
    private int sarake;

    /**
     * Luo Ruutu olion halutuilla arvoilla
     * @param arvo numero joka asetetaan
     * @param rivi ruudun rivi
     * @param sarake ruudun sarake
     */
    public Ruutu(int arvo, int rivi, int sarake) {
        this.arvo = arvo;
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

    private boolean tarkistaNumero(int numero) {
        return numero < 10 && numero > 0;
    }

    /**
     * Asettaa ruudulle arvon, jos arvo on sallittus
     * @param arvo arvo joka asetetaan
     * @return onnistuiko asetus
     */
    public boolean setArvo(int arvo) {
        if (arvo == 0) {
            this.arvo = 0;
            return true;
        }
        if (tarkistaNumero(arvo)) {
            this.arvo = arvo;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Tyhjentaa ruudun arvon eli toisin sanoen asettaa ruudun arvoksi nollan.
     */
    public void tyhjenna() {
        this.arvo = 0;
    }

    /**
     *
     * @return palauttaa true jos ruutu on tyhja, muuten false
     */
    public boolean vapaa() {
        return this.arvo == 0;
    }

}
