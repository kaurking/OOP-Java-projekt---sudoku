import java.io.Console;

public class LauaGeneraator {

    // mitteEemaldatudNumbrid nagu raskustase, mida rohkem jätad alles numbreid, seda lihtsam on
    // Genereerid alguses valiidse laua ja siis eemaldad numbreid, Siis peab kontrollima, kas on lahendatav?
    public static SudokuLaud generate(int eemaldatudNumbrid) {
        SudokuLaud taidetudLaud = generateFullBoard();
        SudokuLaud poolikLaud = removeCells(taidetudLaud, eemaldatudNumbrid);

        return poolikLaud;
    }

    private static SudokuLaud generateFullBoard() {
        SudokuLaud laud = new SudokuLaud();

        // Aluseks võetakse korrektne sudoku ruudustik, sest selle ridu, veergusi ja elemente vahetades on võimalik
        // genereerida täiesti teine, kuid ikkagi reeglitele vastav sudoku
        int[] ruutudeAlgVaartused = {
                3, 1, 9,   4, 2, 7,   5, 8, 6,
                6, 8, 7,   3, 1, 5,   9, 2, 4,
                2, 5, 4,   6, 8, 9,   1, 7, 3,

                4, 7, 3,   1, 9, 2,   6, 5, 8,
                1, 6, 2,   5, 3, 8,   7, 4, 9,
                5, 9, 8,   7, 6, 4,   2, 3, 1,

                9, 4, 5,   8, 7, 1,   3, 6, 2,
                7, 2, 6,   9, 4, 3,   8, 1, 5,
                8, 3, 1,   2, 5, 6,   4, 9, 7};

        for (int rida = 0; rida < 9; rida++) {
            for (int veerg = 0; veerg < 9; veerg++ )
                laud.getRuut(rida, veerg).setValue(ruutudeAlgVaartused[rida * 9 + veerg]);
        }

        // Vahetame iga numbri (1-9) positsioonid sudokus mõne muu, suvalise numbri positsioonidega
        for (int arv = 1; arv < 10; arv++) {
            int juhuArv = (int) (Math.random() * 9 + 1);
            for (int rida = 0; rida < 9; rida++) {
                for (int veerg = 0; veerg < 9; veerg++) {
                    if (laud.getRuut(rida, veerg).getValue() == juhuArv) {
                        laud.getRuut(rida, veerg).setValue(arv);
                    } else if (laud.getRuut(rida, veerg).getValue() == arv) {
                        laud.getRuut(rida, veerg).setValue(juhuArv);
                    }
                }
            }
        }

        int ajutine;

        // vahetame suvaliselt ridade järjekorda kolme rea kaupa (alguses read 1-3, siis 4-6...)
        for (int plokiNumber = 0; plokiNumber < 3; plokiNumber++) {
            for (int kordusteArv = 0; kordusteArv < 3; kordusteArv++) {
                int juhuArv = (int) (Math.random() * 3);
                for (int veeruNumber = 0; veeruNumber < 9; veeruNumber++) {
                    ajutine = laud.getRuut(plokiNumber * 3 + juhuArv, veeruNumber).getValue();
                    laud.getRuut(plokiNumber * 3 + juhuArv, veeruNumber).setValue(laud.getRuut(plokiNumber * 3 + kordusteArv, veeruNumber).getValue()); ;
                    laud.getRuut(plokiNumber * 3 + kordusteArv, veeruNumber).setValue(ajutine);
                }
            }
        }

        // vahetame suvaliselt veergude järjekorda kolme veeru kaupa (alguses veerud 1-3, siis 4-6...)
        for (int plokiNumber = 0; plokiNumber < 3; plokiNumber++) {
            for (int kordusteArv = 0; kordusteArv < 3; kordusteArv++) {
                int juhuArv = (int) (Math.random() * 3);
                for (int reaNumber = 0; reaNumber < 9; reaNumber++) {
                    ajutine = laud.getRuut(reaNumber, plokiNumber * 3 + juhuArv).getValue();
                    laud.getRuut(reaNumber, plokiNumber * 3 + juhuArv).setValue(laud.getRuut(reaNumber, plokiNumber * 3 + kordusteArv).getValue());
                    laud.getRuut(reaNumber, plokiNumber * 3 + kordusteArv).setValue(ajutine);
                }
            }
        }

        // vahetame suvaliselt plokkide ridade (3 rida korraga) järjekorda
        for (int plokiReaNumber = 0; plokiReaNumber < 3; plokiReaNumber++) {
            int juhuArv = (int) (Math.random() * 3);
            for (int reaNumber = 0; reaNumber < 3; reaNumber++) {
                for (int veeruNumber = 0; veeruNumber < 9; veeruNumber++) {
                    ajutine = laud.getRuut(plokiReaNumber * 3 + reaNumber, veeruNumber).getValue();
                    laud.getRuut(plokiReaNumber * 3 + reaNumber, veeruNumber).setValue(laud.getRuut(juhuArv * 3 + reaNumber, veeruNumber).getValue());
                    laud.getRuut(juhuArv * 3 + reaNumber, veeruNumber).setValue(ajutine);
                }
            }
        }

        // vahetame suvaliselt plokkide veergude (3 rida korraga) järjekorda
        for (int plokiVeeruNumber = 0; plokiVeeruNumber < 3; plokiVeeruNumber++) {
            int juhuArv = (int) (Math.random() * 3);
            for (int veeruNumber = 0; veeruNumber < 3; veeruNumber++) {
                for (int reaNumber = 0; reaNumber < 9; reaNumber++) {
                    ajutine = laud.getRuut(reaNumber, plokiVeeruNumber * 3 + veeruNumber).getValue();
                    laud.getRuut(reaNumber, plokiVeeruNumber * 3 + veeruNumber).setValue(laud.getRuut(reaNumber, juhuArv * 3 + veeruNumber).getValue());;
                    laud.getRuut(reaNumber, juhuArv * 3 + veeruNumber).setValue(ajutine);
                }
            }
        }

        return laud;
    }

    // Tagastab, kas antud sudoku on täpselt ühe lahendusega
    private static boolean onYheLahendusega(SudokuLaud laud) {
        return leiaLahendus(laud.copyOf()).isComplete(false);
    }

    // Rekursiivselt sudoku lahendamine
    // Kui leitakse rohkem kui üks lahendus või sudokut ei ole võimalik lahendada, siis tagastatakse sudoku, mis on reeglitevastane
    private static SudokuLaud leiaLahendus(SudokuLaud laud) {

        boolean yksLahendus = false; // kas on juba leitud üks lahendus
        SudokuLaud esimeneLahendus = laud.copyOf();

        // Sudoku lahendamine
        for (int rida = 0; rida < 9; rida ++) {
            for (int veerg = 0; veerg < 9; veerg++) {
                if (laud.getRuut(rida, veerg).getValue() == 0) {
                    for (int arv = 1; arv < 10; arv++){

                        // Arvude 1-9 proovimine tühjades lahtrites
                        laud.getRuut(rida, veerg).setValue(arv);

                        // Kontrollime, kas arv sobib lahtrisse
                        if (laud.isComplete(true)) {

                            // Kui arv sobib, otsitakse rekursiivselt järgmisesse lahtrisse sobivat arvu
                            SudokuLaud genereeritudLaud = leiaLahendus(laud).copyOf();

                            if(genereeritudLaud.isComplete(true)) {

                                if (!yksLahendus) {
                                    // Lahenduse leidmisel salvestatakse see ja märgitakse, et üks lahendus on leitud
                                    yksLahendus = true;
                                    esimeneLahendus = genereeritudLaud.copyOf();
                                } else {
                                    // Teise lahenduse leidmisel muudetakse esimene lahter 0-ks, sest
                                    // siis on genereeritudLaud.isComplete() eelmises rekursioonis alati väär
                                    laud.getRuut(0, 0).setValue(0);
                                    return laud;
                                }

                            }
                        }
                        // Kui leiti ainult üks lahendus ja kõik ülejäänud arvud on kontrollitud, tagastatakse lahendus
                        if (esimeneLahendus.isComplete(false) && arv == 9) {
                            return esimeneLahendus;
                        }
                    }
                }
            }
        }
        // Kui lahendust ei leitud, tagastatakse laud, mis ei saa olla reeglitele vastav
        return esimeneLahendus;

    }

    // Antud sudokust etteantud koguse lahtrite kustutamine, et luua lahendatav sudoku
    private static SudokuLaud removeCells(SudokuLaud laud, int eemaldatudNumbrid) {

        // Võib tekkida probleem, kui pärast mingi hulga lahtrite kustutamist ei saa enam
        // ühtegi lahtrit rohkem kustutada, kuid numbritega lahtreid on ikka liiga palju.
        // Kas genereerida kuidagi uus sudoku ja proovida sellega või proovida sama sudo-
        // kuga edasi, alustades täiesti algusest?

            while (laud.getNullideArv() < eemaldatudNumbrid) {

                SudokuLaud eelnevSeis = laud.copyOf();

                // Valime suvalise lahtri, mida kustutada
                int suvalineRida = (int) (Math.random() * 9);
                int suvalineVeerg = (int) (Math.random() * 9);
                if (laud.getRuut(suvalineRida, suvalineVeerg).getValue() != 0) {
                    laud.getRuut(suvalineRida, suvalineVeerg).setValue(0);
                }

                // Kui lahtri kustutamine tegi sudoku võimatuks, proovitakse mõnda teist lahtrit kustutada
                while (!onYheLahendusega(laud)) {

                    // Laua lahtrite taastamine samasse seisu, mis oli enne lahtri kustutamist
                    laud = eelnevSeis.copyOf();

                    suvalineRida = (int) (Math.random() * 8);
                    suvalineVeerg = (int) (Math.random() * 8);
                    if (laud.getRuut(suvalineRida, suvalineVeerg).getValue() != 0) {
                        laud.getRuut(suvalineRida, suvalineVeerg).setValue(0);
                    }

                }
            }

        // Määrame sudoku 'vihjetele' isFixed, et kasutaja ei saaks neid muuta
        for (int rida = 0; rida < 9; rida++) {
            for (int veerg = 0; veerg < 9; veerg++) {
                if (laud.getRuut(rida, veerg).getValue() != 0) {
                    laud.getRuut(rida, veerg).setIsFixed(true);
                }
            }
        }

        return laud;

    }

}
