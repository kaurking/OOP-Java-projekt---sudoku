public class SudokuMain {

    private SudokuLaud laud;

    public void playGame() {

        // Vaja lisada veel raskustaseme valik, hetkel genereerib iga sudoku 20 kustutatud lahtriga
        SudokuLaud laud = LauaGeneraator.generate(30);

        ConsoleUI UI = new ConsoleUI();

        while (true) {

            // prindi laud
            UI.printBoard(laud);

            // küsi kasutajalt sisendit
            int[] sisend = UI.getKasutajaLiigutus();

            // lisab lauale sisendi
            if (laud.makeMove(sisend[0], sisend[1], sisend[2])) {
                if (laud.isComplete(false)) {
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
