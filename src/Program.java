import java.util.Scanner;

public class Program {
//    змінні по вмісту поля
    public static final String EmptyCell = "   ", Cross = " X ", Zero = " O ";
    public static String aktyvnyjGravec;

//    змінні по ігровому полю  і грі
    public static final int Lines = 3, Column = 3;
    public static String[][] pole = new String[Lines][Column]; // створення масиву
    public static int statusGry;
    public static final int GRA_TRYVAE = 0, NICHYYA = 1, PEREMOHA_X = 3, PEREMOHA_O = 4; // зміні для стану гри

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        PochatokGry();

        do {
            ChidGravcia();
            AnalisPeremohy();
            VyvidPolia();
            if (statusGry == PEREMOHA_X) {
                System.out.println(" 'X' Переміг");
            } else if (statusGry == PEREMOHA_O) {
                System.out.println(" 'O' Переміг");
            } else if (statusGry == NICHYYA) {
                System.out.println("Нічия");
            }
            aktyvnyjGravec = (aktyvnyjGravec == Cross ? Zero : Cross);
        }
        while (statusGry == GRA_TRYVAE);
    }

// метод для початку гри
    public static void PochatokGry() {
        for (int r = 0; r < Lines; r ++ ) {
            for (int s =0; s < Column; s ++) {
                pole[r][s] = EmptyCell;
            }
        }
        aktyvnyjGravec = Cross;
        VyvidPolia();
    }
// метод дяля вибору місяця ходу
    public static void ChidGravcia() {
        boolean vvedennyaHodu = false; //25/20
        do {
            System.out.println("Гравець '"+aktyvnyjGravec+"', ведіть рядок ( 1 - 3 ) і ствчик ( 1 - 3 ) через пробіл");
            int r = sc.nextInt() - 1;
            int s = sc.nextInt() - 1;
            if (r >= 0 && r < Lines &&  s >= 0 && s < Column && pole[r][s] == EmptyCell) { // 28.03
                pole[r][s] = aktyvnyjGravec;
                vvedennyaHodu = true;
            } else {
                System.out.println("Ваш хід (" + (r + 1) + ","  + (s + 1) + ") неможливий. Спробуйте ще раз...");
            }
        }
        while (vvedennyaHodu != true);

    }
//    метод перевірка перемоги
    public static void AnalisPeremohy() {

        String winner = Winner();
        if (winner.equals(Cross)) {
            statusGry = PEREMOHA_X;
        }else if (winner.equals(Zero)) {
            statusGry = PEREMOHA_O;
        } else if (AreAllCellsFilled()) {
            statusGry = NICHYYA;
        } else {
            statusGry = GRA_TRYVAE;
        }
    }
//    Перевірка на заповненість кліток поля.
    public static boolean AreAllCellsFilled() {
        for (int r = 0; r < Lines ; r++ ) {
            for (int s = 0; s < Lines; s++) {
                if (pole[r][s] == EmptyCell) {
                    return false; // маємо порожню клітинку
                }
            }
        }
        return true;
    }
//    метод на перевірку перемоця
    public static String Winner() {

        int i; //KilkistOdnakovych;
        //в рядок
        for (int r = 0; r < Lines; r++) {
            i = 0;
            for (int s = 0; s < Column; s++) {
                if (pole[r][0] != EmptyCell && pole[r][0] == pole[r][s]) {
                    i++;
                }
            }
            if (i == 3) {
                return pole[r][0];
            }
        }
        //в стовпчик
        for (int s = 0; s < Column; s++) {
            i = 0;
            for (int r = 0; r < Lines; r++) {
                if (pole[0][s] != EmptyCell && pole[0][s] == pole[r][s]) {
                    i++;
                }
            }
            if (i == 3) {
                return pole[0][s];
            }
        }
        if (pole[0][0] != EmptyCell && pole[0][0] == pole[1][1] && pole[0][0] == pole[2][2]) {
            return pole[0][0];
        }
        if (pole[0][2] != EmptyCell && pole[2][0] == pole[1][1] && pole[2][0] == pole[0][2]) {
            return pole[0][2];
        }
        return EmptyCell;
    }



//Метод для виведення поля
    public static void VyvidPolia() {
        for (int r = 0; r < Lines; r++) {
            for (int s = 0; s < Column; s++) {
                System.out.print(pole[r][s]);
                if (s != Column - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (r!= Lines - 1) {
                System.out.println("-----------");
            }
        }
        System.out.println();

    }
}


