public class SudokuMain {

    private SudokuLaud laud;

    public void playGame() {

        ConsoleUI UI = new ConsoleUI();

        SudokuLaud laud = LauaGeneraator.generate(UI.getRaskusTase());

        while (true) {

            // prindi laud
            UI.printBoard(laud);

            // küsi kasutajalt sisendit
            int[] sisend = UI.getKasutajaLiigutus();

            // lisab lauale sisendi                               // *alguses oli x ja y koordinaat vale pidi.
            if (laud.makeMove(sisend[1], sisend[0], sisend[2])) { // 1 ja 0 on vastupidi, kuna makeMove() on
                if (laud.isComplete(false)) {       // tehtud nii, et laud[rida][veerg] on uus ruut,
                    UI.printBoard(laud);                          // mis on muidu vale pidi, haha ups.
                    System.out.println("Palju õnne! Lahendasid sudoku!");
                    break;
                }
            } else {
                System.out.println("Pole valiidne käik või seda ruutu ei saa muuta (algseid ruute (rohelisi) muuta ei saa).");
            }

        }
    }
}
