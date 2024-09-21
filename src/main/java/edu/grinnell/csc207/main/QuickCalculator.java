package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BigFraction;
import java.io.PrintWriter;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;

/**
 * A program that acts like a four function calculator with a register. Accepts command line
 * arguments only.
 *
 * @author Cade Johnston
 */
public class QuickCalculator {
  /**
   * The expected string for the store command.
   */
  private static final String STORE_STRING = "STORE";

  /**
   * The regex expected for integer input.
   */
  private static final String INT_REGEX = "\\-?[0-9]*";

  /**
   * The regex expected for fraction input.
   */
  private static final String FRAC_REGEX = "\\-?[0-9]*/\\-?[1-9][0-9]*?";

  /**
   * The regex expected for registers.
   */
  private static final String REGISTER_REGEX = "[a-z]";

  /**
   * Initialize and run a four function calculator with command line arguements.
   *
   * @param args
   *   Command-line arguments are an array of strings.
   *   Each string should be made up of three things:
   *      integer numbers and fractional numbers (See regex above for matches)
   *      register chars (See regex above for matches)
   *      function chars (+, -, /, *)
   *   The arguments will be run in order as seperate equations / commands for the calculator
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    BFCalculator calculator = new BFCalculator();
    BFRegisterSet register = new BFRegisterSet();
    BigFraction secondParam;
    String lastInput;
    boolean allowingOutput = false;
    String[] parsingInput;
    for (String arg : args) {
      lastInput = arg;
      parsingInput = lastInput.split(" ", 0);
      if (parsingInput.length == 0) {
        System.err.println("Error: Invlaid expression.");
      } else if (parsingInput.length == 2) {
        if (parsingInput[0].equals(STORE_STRING)) {
          if ((parsingInput[1].matches(REGISTER_REGEX))) {
            register.store(parsingInput[1].charAt(0), calculator.get());
            pen.printf("%s: STORED\n", lastInput);
          } else {
            System.err.println("Error: STORE command received invalid register.");
          } // if / else
        } else {
          System.err.println("FAILED [Invalid expression]");
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
            allowingOutput = false;
          } // if / else
        } else {
          allowingOutput = false;
        } // if / else if / else
        for (int i = 0; i < parsingInput.length - 1; i += 2) {
          if ((parsingInput[i + 2].matches(INT_REGEX)) || (parsingInput[i + 2].matches(FRAC_REGEX))
              || (parsingInput[i + 2].matches(REGISTER_REGEX))) {
            if (parsingInput[i + 2].matches(REGISTER_REGEX)) {
              if (register.get(parsingInput[i + 2].charAt(0)) != null) {
                secondParam = register.get(parsingInput[i + 2].charAt(0));
              } else {
                System.err.println("Error: Invlaid expression.");
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
            } else if (parsingInput[i + 1].equals("/")) {
              calculator.divide(secondParam);
            } else {
              allowingOutput = false;
              i = parsingInput.length;
            } // if / else if / else if / else if / else
          } else {
            allowingOutput = false;
            i = parsingInput.length;
          } // if / else
        } // for [i]
        if (allowingOutput) {
          pen.printf("%s -> %s\n", lastInput, calculator.get());
        } else {
          pen.printf("%s Failed [Invalid expression]", lastInput);
        } // if / else
      } else {
        System.err.println("FAILED [Invalid expression]");
      } // if / else if / else if / else
    } // for [input]
    pen.close();
  } // main(String[])
} // class QuickCalculator
