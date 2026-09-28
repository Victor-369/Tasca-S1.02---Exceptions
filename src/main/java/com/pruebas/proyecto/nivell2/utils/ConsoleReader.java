package com.pruebas.proyecto.nivell2.utils;

import com.pruebas.proyecto.nivell2.exceptions.StringTooLongException;
import com.pruebas.proyecto.nivell2.exceptions.UniqueCharacterException;
import com.pruebas.proyecto.nivell2.exceptions.YesNoAnswerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleReader {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger(ConsoleReader.class);
    private static final int MAX_LENGHT_STRING = 3;


    public static byte readByte(String message) {
        boolean valid = false;
        byte answer = 0;

        while (!valid) {
            try {
                System.out.print(message);
                answer = SCANNER.nextByte();

                valid = true;
            } catch (InputMismatchException e) {
                LOGGER.error("Must be a byte");
            } finally {
                SCANNER.nextLine();
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
                answer = SCANNER.nextInt();

                valid = true;
            } catch (InputMismatchException e) {
                LOGGER.error("Must be an int");
            } finally {
                SCANNER.nextLine();
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
                answer = SCANNER.nextFloat();

                valid = true;
            } catch (InputMismatchException e) {
                LOGGER.error("Must be a float");
            } finally {
                SCANNER.nextLine();
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
                answer = SCANNER.nextDouble();

                valid = true;
            } catch (InputMismatchException e) {
                LOGGER.error("Must be a double");
            } finally {
                SCANNER.nextLine();
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
                String tmp = SCANNER.nextLine();
                if (tmp.length() != 1) throw new UniqueCharacterException("Char too long");
                answer = tmp.charAt(0);

                valid = true;
            } catch (UniqueCharacterException e) {
                LOGGER.error("Must be a char");
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
                answer = SCANNER.nextLine();
                if (answer.length() > MAX_LENGHT_STRING) throw new StringTooLongException("String too long");

                valid = true;
            } catch (StringTooLongException e) {
                LOGGER.error("String is too long");
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
                String tmp = SCANNER.nextLine();

                if (tmp.equals("s")) {
                    answer = true;
                    valid = true;
                } else if (tmp.equals("n")) {
                    answer = false;
                    valid = true;
                } else throw new YesNoAnswerException("Wrong answer");
            } catch (YesNoAnswerException e) {
                LOGGER.error("Answer must be 's' or 'n'");
            }
        }

        return answer;
    }
}
