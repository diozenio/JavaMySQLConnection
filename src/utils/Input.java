package utils;

import java.util.Scanner;

public class Input {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static int getInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número inteiro válido!");
            }
        }
    }

    public static double getDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número decimal válido!");
            }
        }
    }
}
