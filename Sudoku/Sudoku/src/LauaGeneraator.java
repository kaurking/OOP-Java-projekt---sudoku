public class LauaGeneraator {

    // mitteEemaldatudNumbrid nagu raskustase, mida rohkem jätad alles numbreid, seda lihtsam on
    // Genereerid alguses valiidse laua ja siis eemaldad numbreid, Siis peab kontrollima, kas on lahendatav?
    public static SudokuLaud generate(int mitteEemaldatudNumbrid) {
        SudokuLaud fullBoard = generateFullBoard();
        removeCells(fullBoard, mitteEemaldatudNumbrid);
        return fullBoard;
    }

    private static SudokuLaud generateFullBoard() {return null;}

    private static void removeCells(SudokuLaud laud, int mitteEemaldatudNumbrid) {}

}
