import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LauaGeneraator {

    // Tagastab nõutud arvu tühjade ruutudega lahendatava sudoku
    public static SudokuLaud generate(int eemaldatudNumbrid) {
        SudokuLaud taidetudLaud = genereeriTaielikLaud();
        SudokuLaud poolikLaud = kustutaRuudud(taidetudLaud, eemaldatudNumbrid);

        return poolikLaud;
    }

    private static SudokuLaud genereeriTaielikLaud() {
        SudokuLaud laud = new SudokuLaud();

        // Aluseks võetakse korrektne sudoku ruudustik, sest selle ridu ja veergusi suvaliselt vahetades on võimalik
        // genereerida täiesti teine, kuid ikkagi reeglitele vastav sudoku
        int[] ruutudeAlgVaartused = {
                3, 1, 9, 4, 2, 7, 5, 8, 6,
                6, 8, 7, 3, 1, 5, 9, 2, 4,
                2, 5, 4, 6, 8, 9, 1, 7, 3,

                4, 7, 3, 1, 9, 2, 6, 5, 8,
                1, 6, 2, 5, 3, 8, 7, 4, 9,
                5, 9, 8, 7, 6, 4, 2, 3, 1,

                9, 4, 5, 8, 7, 1, 3, 6, 2,
                7, 2, 6, 9, 4, 3, 8, 1, 5,
                8, 3, 1, 2, 5, 6, 4, 9, 7};

        for (int rida = 0; rida < 9; rida++) {
            for (int veerg = 0; veerg < 9; veerg++)
                laud.getRuut(rida, veerg).setValue(ruutudeAlgVaartused[rida * 9 + veerg]);
        }

        // Iga numbri positsioonide vahetamine mõne muu, suvalise numbri positsioonidega
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
                    laud.getRuut(plokiNumber * 3 + juhuArv, veeruNumber).setValue(laud.getRuut(plokiNumber * 3 + kordusteArv, veeruNumber).getValue());
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
                    laud.getRuut(reaNumber, plokiVeeruNumber * 3 + veeruNumber).setValue(laud.getRuut(reaNumber, juhuArv * 3 + veeruNumber).getValue());
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
    // Kui leitakse rohkem kui üks lahendus või sudokut ei ole võimalik lahendada, siis tagastatakse sudoku, mis on poolik
    private static SudokuLaud leiaLahendus(SudokuLaud laud) {

        boolean yksLahendus = false; // kas on juba leitud üks lahendus
        SudokuLaud esimeneLahendus = laud.copyOf();

        // Läbime sudokulaua ruuduhaaval, otsides tühje ruute
        for (int rida = 0; rida < 9; rida++) {
            for (int veerg = 0; veerg < 9; veerg++) {
                if (laud.getRuut(rida, veerg).getValue() == 0) {

                    // Arvude 1-9 proovimine tühjas ruudus
                    for (int arv = 1; arv < 10; arv++) {
                        laud.getRuut(rida, veerg).setValue(arv);

                        // Kontrollime, kas arv sobib ruutu
                        if (laud.isComplete(true)) {

                            // Kui arv sobib, otsitakse rekursiivselt järgmisesse ruutu sobivat arvu
                            SudokuLaud genereeritudLaud = leiaLahendus(laud).copyOf();

                            // Kui rekursioon tagastab täidetud ja korrektse laua, siis leiti lahendus
                            // Kui rekursioon tagastab pooliku laua, ei leitud järelikult lahendust ja
                            // eespool for tsüklis proovitakse ruutu panna järgmist arvu
                            if (genereeritudLaud.isComplete(false)) {

                                if (!yksLahendus) {
                                    // Lahenduse leidmisel salvestatakse see ja märgitakse, et üks lahendus on leitud
                                    yksLahendus = true;
                                    esimeneLahendus = genereeritudLaud.copyOf();
                                } else {
                                    // Teise lahenduse leidmisel tagastatakse sudoku, mis ei ole lahendatud
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
        // Kui lahendust ei leitud, tagastatakse algne laud
        return laud;

    }

    // Antud sudokust etteantud koguse ruutude kustutamine, et luua lahendatav sudoku
    private static SudokuLaud kustutaRuudud(SudokuLaud laud, int eemaldatudNumbrid) {

        SudokuLaud algneSeis = laud.copyOf();

        // Järjendi loomine kõikide ruutude koordinaatidega
        List<List<Integer>> koigiRuutudeKoordinaadid = new ArrayList<>();

        for (int rida = 0; rida < 9; rida++) {
            for (int veerg = 0; veerg < 9; veerg++) {
                List<Integer> ajutineJarjend = new ArrayList<>();
                ajutineJarjend.add(rida);
                ajutineJarjend.add(veerg);
                koigiRuutudeKoordinaadid.add(ajutineJarjend);
            }
        }

        // Koordinaatide panemine suvalisse järjekorda
        Collections.shuffle(koigiRuutudeKoordinaadid);

        // Muutuja tsüklite arvu piiramiseks
        int kordusteArv = 0;

        // Tsükkel käib, kuni leitakse sobiv laud või tsüklite arv saab täis
        while (laud.getNullideArv() < eemaldatudNumbrid && kordusteArv < 10) {

            kordusteArv++;

            // Kui while tsükkel jõuab rohkem kui üks korda siia, ei õnnestunud eelmise tsükli jooksul luua
            // korrektset sudokut, seega laud lähtestatakse ja proovitakse ruute kustutada teises järjekorras.
            laud = algneSeis.copyOf();
            Collections.shuffle(koigiRuutudeKoordinaadid);

            for (int i = 0; i < 81; i++) {

                SudokuLaud eelnevSeis = laud.copyOf();

                List<Integer> ruuduKoordinaadid = koigiRuutudeKoordinaadid.get(i);
                laud.getRuut(ruuduKoordinaadid.get(0), ruuduKoordinaadid.get(1)).setValue(0);

                if (!onYheLahendusega(laud)) {

                    // Kui ruudu kustutamisega muutus sudoku võimatuks, võetakse tagasi eelnev laud ja proovitakse uuesti
                    laud = eelnevSeis.copyOf();

                } else if (laud.getNullideArv() == eemaldatudNumbrid) {

                    // Kui nõutud arv ruute on kustutatud, lõpetatakse tsükkel
                    break;

                }
            }
        }

        // Sudoku 'vihjetele' isFixed määramine, et kasutaja ei saaks neid ruute muuta
        for (int rida = 0; rida < 9; rida++) {
            for (int veerg = 0; veerg < 9; veerg++) {
                if (laud.getRuut(rida, veerg).getValue() != 0) {
                    laud.getRuut(rida, veerg).setIsFixed(true);
                }
            }
        }

        // Kui ei suudetud 10 tsükli jooksul antud lauast teha nõutud tühjade ruutudega sudokut, siis
        // proovitakse rekursiivselt uue, suvalise lauaga
        if (laud.getNullideArv() < eemaldatudNumbrid) {
            return kustutaRuudud(genereeriTaielikLaud(), eemaldatudNumbrid);
        } else {
            return laud;
        }

    }

}
