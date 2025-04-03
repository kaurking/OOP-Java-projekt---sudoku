import java.util.Arrays;
import java.util.Scanner;

public class ConsoleUI {

    public void printBoard(SudokuLaud laud) {
        System.out.println("    1 2 3   4 5 6   7 8 9");
        System.out.println("  +-------+-------+-------+");

        for (int rida = 0; rida < 9; rida++) {
            System.out.print((rida + 1) + " | "); // rea number

            for (int veerg = 0; veerg < 9; veerg++) {
                int value = laud.getRuut(rida, veerg).getValue();
                if (value == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print(value + " ");
                }

                if ((veerg + 1) % 3 == 0) {
                    System.out.print("| ");
                }
            }

            System.out.println(); // rea lõpp

            if ((rida + 1) % 3 == 0) {
                System.out.println("  +-------+-------+-------+");
            }
        }
    }

    public int getRaskusTase() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Sisesta, mitme täidetud ruuduga sudokut soovid:");

        String input = myObj.nextLine();

        int ruutudeArv = Integer.parseInt(input);

        if (ruutudeArv > 80) {
            System.out.println("Viga! Täidetud ruute ei saa olla rohkem kui 80.");
            return getRaskusTase();
        } else if (ruutudeArv < 17) {
            System.out.println("Viga! Alla 17 täidetud ruuduga sudoku ei ole lahendatav.");
            return getRaskusTase();
        }

        return 81 - ruutudeArv;

    }

    // võtab kasutajalt terminalis uude ruutu väärtuse. x kordinaat, y kordinaat ja sisestatud number ehk nt: 2 3 5
    public int[] getKasutajaLiigutus() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Sisesta ruudu koordinaadid ja väärtus (x y väärtus) või 'q' lõpetamiseks:");

        String input = myObj.nextLine();

        if (input.equalsIgnoreCase("q")) {
            System.out.println("Mäng lõpetatud.");
            System.exit(0);
        }

        String[] tokens = input.trim().split("\\s+");
        if (tokens.length != 3) {
            System.out.println("Vale arv väärtusi. Proovi uuesti.");
            return getKasutajaLiigutus();
        }

        try {
            int x = Integer.parseInt(tokens[0]) - 1;
            int y = Integer.parseInt(tokens[1]) - 1;
            int value = Integer.parseInt(tokens[2]);

            if (x < 0 || x > 8 || y < 0 || y > 8 || value < 1 || value > 9) {
                System.out.println("Väärtused peavad jääma vahemikku 1–9 (koordinaadid) ja 1–9 (väärtus).");
                return getKasutajaLiigutus();
            }

            return new int[]{x, y, value};
        } catch (NumberFormatException e) {
            System.out.println("Sisend peab olema arvuline või 'q'.");
            return getKasutajaLiigutus();
        }
    }

    // selleks et sõnumit väljastada kui midagi õnnestub või pekki läheb
    public void showMessage(String message) {
        System.out.println(message);
    }

}
