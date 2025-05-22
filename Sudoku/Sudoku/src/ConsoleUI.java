import java.util.Scanner;

public class ConsoleUI {

    // et teha terminalis värve
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public void printBoard(SudokuLaud laud) {
        System.out.println(CYAN + "    1 2 3   4 5 6   7 8 9" + RESET);
        System.out.println(BLUE + "  +-------+-------+-------+" + RESET);

        for (int rida = 0; rida < 9; rida++) {
            System.out.print(CYAN + (rida + 1) + RESET + BLUE + " | " + RESET); // rea number

            for (int veerg = 0; veerg < 9; veerg++) {
                int value = laud.getRuut(rida, veerg).getValue();
                boolean fixed = laud.getRuut(rida, veerg).getIsFixed();
                if (value == 0) {
                    System.out.print(RED + ". " + RESET);
                } else if (fixed) {
                    System.out.print(GREEN + value + RESET + " ");
                } else {
                    System.out.print(RED + value + RESET + " ");
                }

                if ((veerg + 1) % 3 == 0) {
                    System.out.print(BLUE + "| " + RESET);
                }
            }

            System.out.println(); // rea lõpp

            if ((rida + 1) % 3 == 0) {
                System.out.println(BLUE + "  +-------+-------+-------+" + RESET);
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
        System.out.println("Sisesta ruudu koordinaadid ja väärtus (x *space* y *space* väärtus) (algseid, rohelisi ei saa muuta) või 'q' lõpetamiseks:");

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

    // selleks et sõnumit väljastada kui midagi õnnestub või pekki läheb - pole kasutuses hetkel
    public void showMessage(String message) {
        System.out.println(message);
    }

}
