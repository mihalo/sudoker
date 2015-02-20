package sudoku.pelilogiikka;

import org.junit.Test;
import static org.junit.Assert.*;

public class PohjageneraattoriTest {

    Pohjageneraattori helppoPohja = new Pohjageneraattori(1);

    @Test
    public void helpossaPohjassaVainYksiRatkaisu() {
        assertEquals(1, helppoPohja.getMaara());
    }

}
