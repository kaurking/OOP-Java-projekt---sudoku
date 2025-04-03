public class SudokuLaud {

    private SudokuRuut[][] laud;

    public SudokuLaud(SudokuRuut[][] laud) {
        this.laud = laud;
    }

    // Ilma argumentideta konstruktor loob ise tühja sudokulaua
    public SudokuLaud() {
        SudokuRuut[][] lauaRuudud = new SudokuRuut[9][9];

        for (int rida = 0; rida < 9; rida++) {
            for (int veerg = 0; veerg < 9; veerg++) {
                lauaRuudud[rida][veerg] = new SudokuRuut(0, false);
            }
        }

        this.laud = lauaRuudud;
    }

    // Lauast koopia tegemine
    // Saaks lisada ka isFixed väärtuse kopeerimine, kuid hetkel pole vajalik
    public SudokuLaud copyOf() {
        SudokuLaud uusLaud = new SudokuLaud();
        for (int rida = 0; rida < 9; rida++) {
            for (int veerg = 0; veerg < 9; veerg++) {
                uusLaud.getRuut(rida, veerg).setValue(laud[rida][veerg].getValue());
            }
        }
        return uusLaud;

    }

    public SudokuRuut getRuut(int rida, int veerg) {
        return laud[rida][veerg];
    }

    public SudokuRuut[][] getRuudustik() {
        return laud;
    }

    public boolean makeMove(int rida, int veerg, int value) {
        if (isValidMove(rida, veerg, value)) {
            laud[rida][veerg] = new SudokuRuut(value, false);
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidMove(int rida, int veerg, int value) {
        if (value >= 1 && value <= 9 && rida >= 0 && rida < 9 && veerg >= 0 && veerg < 9 && !laud[rida][veerg].getIsFixed()) {
            return true;
        } else {
            return false;
        }
    }

    public int getNullideArv() {
        int nullideArv = 0;

        for (int rida = 0; rida < 9; rida++) {
            for (int veerg = 0; veerg < 9; veerg++){
                if (laud[rida][veerg].getValue() == 0) {
                    nullideArv++;
                }
            }
        }
        return nullideArv;

    }

    public boolean isComplete(boolean onTyhjadLahtrid) {

        // Kontrollib, kas kõikides ridades on numbreid ainult 1 kord.
        // Kui onTyhadLahtrid on true, siis eirab lahtreid, kus on 0.
        for (int rida = 0; rida < laud.length; rida++) {
            boolean[] olemas = new boolean[9];
            for (int veerg = 0; veerg < laud[rida].length; veerg++) {
                int value = laud[rida][veerg].getValue();

                if (onTyhjadLahtrid) {
                    if (value == 0) {
                        continue;
                    }
                }

                if (value == 0 || olemas[value - 1]) return false;
                olemas[value - 1] = true;
            }
        }

        // Kontrollib kõik veerud, kas numbreid on ainult 1 kord
        for (int veerg = 0; veerg < laud.length; veerg++) {
            boolean[] olemas = new boolean[9];
            for (int rida = 0; rida < laud[veerg].length; rida++) {
                int value = laud[rida][veerg].getValue();

                if (onTyhjadLahtrid) {
                    if (value == 0) {
                        continue;
                    }
                }

                if (value == 0 || olemas[value - 1]) return false;
                olemas[value - 1] = true;
            }
        }

        // kontrollib kõik 3x3 alamkastid, kas numbreid on ainult 1 kord
        for (int kastRida = 0; kastRida < 9; kastRida += 3) {
            for (int kastVeerg = 0; kastVeerg < 9; kastVeerg += 3) {
                boolean[] seen = new boolean[9];
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        int value = laud[kastRida + r][kastVeerg + c].getValue();

                        if (onTyhjadLahtrid) {
                            if (value == 0) {
                                continue;
                            }
                        }

                        if (value == 0 || seen[value - 1]) return false;
                        seen[value - 1] = true;
                    }
                }
            }
        }
        return true;
    }

}
