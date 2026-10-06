import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean czyDalejGra = true;

        while (czyDalejGra) {
            int numer = 0;
            while (numer < 3 || numer > 10) {
                System.out.print("Ile kostek chcesz rzucic? (3-10): ");
                numer = scanner.nextInt();
            }

            int[] wynikLiczby = rzucKostkami(numer);

            System.out.println("Wyniki:");
            for (int i = 0; i < wynikLiczby.length; i++) {
                System.out.println("Kostka " + (i + 1) + ": " + wynikLiczby[i]);
            }

            int punkty = liczPunkty(wynikLiczby);
            System.out.println("Punkty: " + punkty);

            System.out.print("Jeszcze raz? (t/n): ");
            czyDalejGra = scanner.next().equals("t");
        }
    }
       /* ************************************************
        nazwa: <rzutKostkami>
        opis: <losuje ilosc wyrzuconych oczek na kostce (1-6)>
        parametry:  numer - okresla liczbe kostek , typu int
        zwracany typ i opis:  tablica liczb calkowitych przechowujacych z wylosowanymi wartosciami
        autor: <000000000>
        ************************************************ */

    public static int[] rzucKostkami(int numer)
    {
        Random random = new Random();
        int[] wynikLiczby = new int[numer];
        for (int i = 0; i < numer; i++) {
            wynikLiczby[i] = random.nextInt(6) + 1;
        }
        return wynikLiczby;

    }

    public static int liczPunkty(int[] wynikLiczby) {
        int[] liczbaPunktowana = new int[7];
        for (int x : wynikLiczby) {
            liczbaPunktowana[x]++;
        }

        int punkty = 0;
        for (int i = 1; i <= 6; i++) {
            if (liczbaPunktowana[i] >= 2) {
                punkty += i * liczbaPunktowana[i];
            }
        }
        return punkty;
    }


}