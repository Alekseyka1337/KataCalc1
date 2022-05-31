package Kata;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int number1, number2;
    static char operation;
    static int result;

    public static void main(String[] args) {
        System.out.println("Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя целыми числами: a + b, a - b, a * b, a / b.");
        System.out.println("Введите выражение c арабскими [5+5] или с римскими [X/V] числами от 1 до 10");
        String userInput = scanner.nextLine();
        try {
            String userOutput = calc(userInput);
            System.out.println(userOutput);
        }
        catch (ArithmeticException e) {
            System.err.println(e + " Нельзя делить на ноль");
        }
        catch (InputMismatchException e) {
            System.err.println(e);
        }
        catch (NumberFormatException e) {
            System.err.println(e + " Неверный формат чисел");
        }
    }
    public static String calc (String input) throws ArithmeticException, NumberFormatException {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+')
                operation = '+';
            if (input.charAt(i) == '-')
                operation = '-';
            if (input.charAt(i) == '*')
                operation = '*';
            if (input.charAt(i) == '/')
                operation = '/';
        }
        input = input.toUpperCase();
        String[] blacks = input.split("[+-/*]");
        if (blacks.length == 1) {
            throw new InputMismatchException("Строка не является математической операцией");
        }
        if (blacks.length > 2) {
            throw new InputMismatchException("Выражение не удовлетворяет заданию - два целочисленных операнда и один оператор (+, -, /, *)");
        }
        String string1 = blacks[0].trim();
        String string2 = blacks[1].trim();
        number1 = romanToNumber(string1);
        number2 = romanToNumber(string2);
        if (number1 < 0 && number2 < 0) {
                number1 = Integer.parseInt(string1);
                number2 = Integer.parseInt(string2);
            if (number1 <= 10 && number2 <= 10) {
                result = calculated(number1, number2, operation);
                return "" + result;
            }
            else {
                throw new InputMismatchException("Калькулятор может принимать на вход числа только от 1 до 10 включительно");
            }
        }
        else if(number1 < 0 || number2 < 0) {
            throw new InputMismatchException("Используются одновременно разные системы счисления");
        }
        else {
            result = calculated(number1, number2, operation);
            return  convertNumToRoman(result);
        }
    }

    static String convertNumToRoman (int numArabian) {
        if (numArabian < 1){
            throw new InputMismatchException("Результатом работы с римскими числами могут быть только положительные числа");
        }
        String[] roman = {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
        return roman[numArabian];
    }

    static int romanToNumber (String roman) {
        return switch (roman) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> -1;
        };
    }

    static int calculated (int num1, int num2, char op) throws ArithmeticException {
        return switch (op) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> num1 / num2;
            default -> throw new InputMismatchException("Выражение не удовлетворяет заданию - два целочисленных операнда и один оператор (+, -, /, *)");
        };
    }
}
