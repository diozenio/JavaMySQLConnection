package utils;

import exceptions.ValidationException;
import java.util.Scanner;

public class Input {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getString(String message) {
        System.out.print(message);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            throw new ValidationException("O campo não pode estar vazio");
        }
        return input;
    }

    public static int getInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();
                int value = Integer.parseInt(input);
                if (value < 0) {
                    throw new ValidationException("O valor não pode ser negativo");
                }
                return value;
            } catch (NumberFormatException e) {
                throw new ValidationException("Digite um número inteiro válido!");
            }
        }
    }

    public static double getDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();
                double value = Double.parseDouble(input);
                if (value < 0) {
                    throw new ValidationException("O valor não pode ser negativo");
                }
                return value;
            } catch (NumberFormatException e) {
                throw new ValidationException("Digite um número decimal válido!");
            }
        }
    }
}
