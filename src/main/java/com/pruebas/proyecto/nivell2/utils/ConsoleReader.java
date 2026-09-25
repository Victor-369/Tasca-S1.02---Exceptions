package com.pruebas.proyecto.nivell2.utils;

import com.pruebas.proyecto.nivell2.exceptions.StringTooLongException;
import com.pruebas.proyecto.nivell2.exceptions.UniqueCharacterException;
import com.pruebas.proyecto.nivell2.exceptions.YesNoAnswerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleReader {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LogManager.getLogger(ConsoleReader.class);

    public static byte readByte(String message) {
        boolean valid = false;
        byte answer = 0;

        while (!valid) {
            try {
                System.out.print(message);
                answer = scanner.nextByte();
                
                valid = true;
            } catch (InputMismatchException e) {
                logger.error("Must be a byte: " + e.getMessage());
            } finally {
                scanner.nextLine();
            }
        }
        
        return answer;
    }

    public static int readInt(String message) {
        boolean valid = false;
        int answer = 0;

        while (!valid) {
            try {
                System.out.print(message);
                answer = scanner.nextInt();

                valid = true;
            } catch (InputMismatchException e) {
                logger.error("Must be an int: " + e.getMessage());
            } finally {
                scanner.nextLine();
            }
        }

        return answer;
    }

    public static float readFloat(String message) {
        boolean valid = false;
        float answer = 0f;

        while (!valid) {
            try {
                System.out.print(message);
                answer = scanner.nextFloat();

                valid = true;
            } catch (InputMismatchException e) {
                logger.error("Must be a float: " + e.getMessage());
            } finally {
                scanner.nextLine();
            }
        }

        return answer;
    }

    public static double readDouble(String message) {
        boolean valid = false;
        double answer = 0d;

        while (!valid) {
            try {
                System.out.print(message);
                answer = scanner.nextDouble();

                valid = true;
            } catch (InputMismatchException e) {
                logger.error("Must be a double: " + e.getMessage());
            } finally {
                scanner.nextLine();
            }
        }

        return answer;
    }

    public static char readChar(String message) {
        boolean valid = false;
        char answer = '\u0000';

        while (!valid) {
            try {
                System.out.print(message);
                String tmp = scanner.nextLine();
                if (tmp.length() != 1) throw new UniqueCharacterException("Char too long");
                answer = tmp.charAt(0);

                valid = true;
            } catch (UniqueCharacterException e) {
                logger.error("Must be a char: " + e.getMessage());
            }
        }

        return answer;
    }

    public static String readString(String message) {
        boolean valid = false;
        String answer = null;

        while (!valid) {
            try {
                System.out.print(message);
                answer = scanner.nextLine();
                if (answer.length() > 3) throw new StringTooLongException("String too long");

                valid = true;
            } catch (StringTooLongException e) {
                logger.error("String is too long: " + e.getMessage());
            }
        }

        return answer;
    }

    public static boolean readYesNo(String message) {
        boolean valid = false;
        boolean answer = false;

        while (!valid) {
            try {
                System.out.print(message);
                String tmp = scanner.nextLine();

                if (tmp.equals("y")) { answer = true; valid = true; }
                else if (tmp.equals("n")) { answer = false; valid = true; }
                else throw new YesNoAnswerException("Wrong answer");
            } catch (YesNoAnswerException e) {
                logger.error("Answer must be 'y' or 'n': " + e.getMessage());
            }
        }

        return answer;
    }
}
