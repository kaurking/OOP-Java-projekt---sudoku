public class SudokuMain {

    private SudokuLaud laud;

    public void playGame() {

        // testlaud, kus on kõik nullid, hiljem LauaGeneraator klassis peaks laua genereerima.
        SudokuRuut[][] testlaud = new SudokuRuut[9][9];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                testlaud[r][c] = new SudokuRuut(0, false); // kõik on tühjad ja saab editida
            }
        }
        SudokuLaud laud = new SudokuLaud(testlaud);
        // end

        ConsoleUI UI = new ConsoleUI();

        while (true) {

            // prindi laud
            UI.printBoard(laud);

            // küsi kasutajalt sisendit
            int[] sisend = UI.getKasutajaLiigutus();

            // lisab lauale sisendi
            if (laud.makeMove(sisend[0], sisend[1], sisend[2])) {
                if (laud.isComplete()) {
                    UI.printBoard(laud);
                    System.out.println("Palju õnne! Lahendasid sudoku!");
                    break;
                }
            } else {
                System.out.println("Pole valiidne käik või seda ruutu ei saa muuta (algseid ruute muuta ei saa).");
            }

        }
    }
}
