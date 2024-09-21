package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.util.Scanner;
import edu.grinnell.csc207.util.BigFraction;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;

/**
 * A program that acts like a four function calculator with a register.
 * Accepts runtime arguments only.
 *
 * @author Cade Johnston
 */
public class InteractiveCalculator {
  /**
   * The expected string for the quit command.
   */
  private static final String QUIT_STRING = "QUIT";

  /**
   * The expected string for the store command.
   */
  private static final String STORE_STRING = "STORE";

  /**
   * The regex expected for integer input.
   */
  private static final String INT_REGEX = "\\-?[0-9]*";

  /**
   * The regex expected for fractional input.
   */
  private static final String FRAC_REGEX = "\\-?[0-9]*/\\-?[1-9][0-9]*?";

  /**
   * The regex expected for registers.
   */
  private static final String REGISTER_REGEX = "[a-z]*";

  /**
   * Initialize and run a four function calculator accepting runtime argument.
   *
   * @param args
   *   To be ignored
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner input = new Scanner(System.in);
    BFCalculator calculator = new BFCalculator();
    BFRegisterSet register = new BFRegisterSet();
    BigFraction secondParam;
    String lastInput;
    boolean acceptingInput = true;
    boolean allowingOutput = false;
    String[] parsingInput;
    while (acceptingInput) {
      lastInput = input.nextLine();
      parsingInput = lastInput.split(" ", 0);
      if (parsingInput.length == 0) {
        System.err.println("Error: Invlaid expression.");
      } else if ((parsingInput.length == 1) && (parsingInput[0].equals(QUIT_STRING))) {
        acceptingInput = false;
      } else if (parsingInput.length == 2) {
        if (parsingInput[0].equals(STORE_STRING)) {
          if ((parsingInput[1].matches(REGISTER_REGEX))) {
            register.store(parsingInput[1].charAt(0), calculator.get());
            pen.println("STORED");
          } else {
            System.err.println("Error: STORE command received invalid register.");
          } // if / else
        } else {
          System.err.println("Error: Invlaid expression.");
        } // if / else
      } else if ((parsingInput.length % 2) == 1) {
        calculator.clear();
        secondParam = new BigFraction("0");
        allowingOutput = true;
        if ((parsingInput[0].matches(INT_REGEX)) || (parsingInput[0].matches(FRAC_REGEX))) {
          calculator.add(new BigFraction(parsingInput[0]));
        } else if (parsingInput[0].matches(REGISTER_REGEX)) {
          if (register.get(parsingInput[0].charAt(0)) != null) {
            calculator.add(register.get(parsingInput[0].charAt(0)));
          } else {
            System.err.println("Error: Invlaid expression.\n");
            allowingOutput = false;
          } // if / else
        } else {
          System.err.println("Error: Invlaid expression.\n");
          allowingOutput = false;
        } // if / else if / else
        for (int i = 0; i < parsingInput.length - 1; i += 2) {
          if ((parsingInput[i + 2].matches(INT_REGEX)) || (parsingInput[i + 2].matches(FRAC_REGEX))
              || (parsingInput[i + 2].matches(REGISTER_REGEX))) {
            if (parsingInput[i + 2].matches(REGISTER_REGEX)) {
              if (register.get(parsingInput[i + 2].charAt(0)) != null) {
                secondParam = register.get(parsingInput[i + 2].charAt(0));
              } else {
                System.err.println("Error: Invlaid expression.\n");
              } // if / else
            } else {
              secondParam = new BigFraction(parsingInput[i + 2]);
            } // if / else
            if (parsingInput[i + 1].equals("+")) {
              calculator.add(secondParam);
            } else if (parsingInput[i + 1].equals("-")) {
              calculator.subtract(secondParam);
            } else if (parsingInput[i + 1].equals("*")) {
              calculator.multiply(secondParam);
            } else if ((parsingInput[i + 1].equals("/"))
                       && (!(secondParam.toString().equals("0")))) {
              calculator.divide(secondParam);
            } else {
              System.err.println("Error: Invalid expression.\n");
              allowingOutput = false;
              i = parsingInput.length;
            } // if / else if / else if / else if / else
          } else {
            System.err.println("Error: Invalid expression.\n");
            allowingOutput = false;
            i = parsingInput.length;
          } // if / else
        } // for [i]
        if (allowingOutput) {
          pen.println(calculator.get());
        } // if
      } else {
        System.err.println("Error: Invalid expression.\n");
      } // if / else if / else if / else
    } // while
    input.close();
    pen.close();
  } // main(String[])
} // class InteractiveCalculator
