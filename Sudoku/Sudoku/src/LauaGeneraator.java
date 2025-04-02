public class LauaGeneraator {

    public static SudokuLaud generate(int mitteEemaldatudNumbrid) {
        SudokuLaud fullBoard = generateFullBoard();
        removeCells(fullBoard, mitteEemaldatudNumbrid);
        return fullBoard;
    }

    private static SudokuLaud generateFullBoard() {}

    private static void removeCells(SudokuLaud laud, int mitteEemaldatudNumbrid) {}

}
