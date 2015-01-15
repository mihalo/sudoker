package sudoku;

import sudoku.pelilogiikka.Pelialue;

public class Main {

    public static void main(String[] args) {
        int[] testiSudoku = {
            0, 0, 0, 0, 0, 0, 0, 0, 4,
            0, 7, 0, 0, 0, 1, 2, 8, 0,
            2, 0, 0, 0, 5, 9, 0, 7, 0,
            0, 0, 0, 5, 9, 7, 8, 0, 3,
            0, 0, 2, 0, 0, 0, 1, 0, 0,
            5, 0, 3, 1, 2, 4, 0, 0, 0,
            0, 4, 0, 7, 3, 0, 0, 0, 2,
            0, 5, 8, 9, 0, 0, 0, 6, 0,
            1, 0, 0, 0, 0, 0, 0, 0, 0};

        int[] testiSudokuRatkaistu = {
            8, 1, 9, 2, 7, 6, 3, 5, 4,
            6, 7, 5, 3, 4, 1, 2, 8, 9,
            2, 3, 4, 8, 5, 9, 6, 7, 1,
            4, 6, 1, 5, 9, 7, 8, 2, 3,
            7, 9, 2, 6, 8, 3, 1, 4, 5,
            5, 8, 3, 1, 2, 4, 7, 9, 6,
            9, 4, 6, 7, 3, 8, 5, 1, 2,
            3, 5, 8, 9, 1, 2, 4, 6, 7,
            1, 2, 7, 4, 6, 5, 9, 3, 8};

        Pelialue peli = new Pelialue();

        peli.asetaNumero(17, 5, 5);
        peli.asetaNumerot(testiSudoku);       
        System.out.println(peli.tarkista());

        peli.tulostaPelialue();
    }

}
