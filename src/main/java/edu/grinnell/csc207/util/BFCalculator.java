package edu.grinnell.csc207.util;

/**
 * A class that acts as a four function calculator.
 * Utilizes BigFraction to store, calculate, and return values.
 *
 * @author Cade Johnston
 */
public class BFCalculator {

  /**
   * The default value and empty value for the calculator (0).
   */
  private final BigFraction clearValue = new BigFraction("0");

  // +--------+
  // | Fields |
  // +--------+

  /** The last value calculated by this calculator. */
  private BigFraction lValue;

  // +--------------+
  // | Constructors |
  // +--------------+

  /**
   * Build a new calculator with lValue 'clearValue'.
   */
  public BFCalculator() {
    lValue = clearValue;
  } // BFCalculator()

  // +---------+
  // | Methods |
  // +---------+

  /**
   * Get the output of the calculator.
   *
   * @return the last calculated value.
   */
  public BigFraction get() {
    return lValue;
  } // get()

  /**
   * Add another faction to the current value and store the result.
   *
   * @param addend
   *   The fraction to add.
   */
  public void add(BigFraction addend) {
    lValue = lValue.add(addend);
  } // add(BigFraction)

  /**
   * Subtract another faction from the current value and store the result.
   *
   * @param subtrahend
   *   The fraction to subtract with.
   */
  public void subtract(BigFraction subtrahend) {
    lValue = lValue.subtract(subtrahend);
  } // subtract(BigFraction)

  /**
   * Multiply another faction to the current value and store the result.
   *
   * @param multiplier
   *   The fraction to mlultiply by.
   */
  public void multiply(BigFraction multiplier) {
    lValue = lValue.multiply(multiplier);
  } // multiply(BigFraction)

  /**
   * Divide another faction from the current value abnd store the result.
   *
   * @param divisor
   *   The fraction to divide by.
   */
  public void divide(BigFraction divisor) {
    lValue = lValue.divide(divisor);
  } // divide(BigFraction)

  /**
   * Reset the value stored in the calculator.
   */
  public void clear() {
    lValue = clearValue;
  } // clear()
} // class BFCalculator
