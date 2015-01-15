package sudoku.pelilogiikka;

public class Ruutu {

    private int arvo;

    public Ruutu() {
        arvo = 0;
    }

    public int getArvo() {
        return arvo;
    }

    public void setArvo(int arvo) {
        if (arvo > 0 && arvo < 10) {
            this.arvo = arvo;
        }
    }

    public void tyhjenna() {
        this.arvo = 0;
    }

    public boolean vapaa() {
        return this.arvo == 0;
    }

}
