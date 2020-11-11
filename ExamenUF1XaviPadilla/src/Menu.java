import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public static Integer menuPrincipal(Scanner sc) {
        Integer opcio = -1;

        System.out.println();
        System.out.println("                Menu               ");
        System.out.println("-----------------------------------");
        System.out.println("1 - Crear fitxers buits");
        System.out.println("2 - Fitxer de propietats");
        System.out.println("3 - Llegir propietats");
        System.out.println("4 - Crear noves propietats");
        System.out.println("5 - Escriure fitxers XML");
        System.out.println("6 - Llegeix fitxer XML");
        System.out.println("7 - Incrementa Alumne confinat");
        System.out.println("8 - Llegeix fitxer instituts.dat");
        System.out.println("9 - Generar fitxer .dat");
        System.out.println("10 - Llegeix fitxer registrecovid.dat");
        System.out.println("0 - Sortir");
        System.out.println();

        try {
            opcio = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("\nNetejant el buffer del escaner... Pressiona alguna tecla...");
            sc.nextLine();
        }

        return opcio;
    }

}
