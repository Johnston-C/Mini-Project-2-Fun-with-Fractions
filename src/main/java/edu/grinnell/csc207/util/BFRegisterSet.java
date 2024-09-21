package edu.grinnell.csc207.util;

/**
 * A class that acts as register.
 * Utilizes BigFraction to store and return values.
 *
 * @author Cade Johnston
 */
public class BFRegisterSet {

  /**
   * The minimum index of the register.
   */
  private static final int REGISTER_MIN = (int) 'a';

  /**
   * The maximum index of the register.
   */
  private static final int REGISTER_MAX = (int) 'z';

  /**
   * The count of indecies.
   */
  private static final int REGISTER_COUNT = 26;

  // +--------+
  // | Fields |
  // +--------+

  /** The values stored in the register. */
  private BigFraction[] register;

  // +--------------+
  // | Constructors |
  // +--------------+

  /**
   * Build a new register with 'REGISTER_COUNT' registers with default values null.
   */
  public BFRegisterSet() {
    register = new BigFraction[REGISTER_COUNT];
  } // BFRegisterSet()

  // +---------+
  // | Methods |
  // +---------+

  /**
   * Store a value in a specified register.
   *
   * @param r
   *   The register to set a value to.
   * @param val
   *   The fraction to set the specified register to.
   */
  public void store(char r, BigFraction val) {
    if ((REGISTER_MIN > (int) r) || ((int) r > REGISTER_MAX)) {
      System.err.printf("Error: Register '%c' does not exist.", r);
    } // if
    this.register[(int) r - REGISTER_MIN] = val;
  } // store(char, BigFracion)

  /**
   * Get a value from a specified register.
   *
   * @param r
   *   The register to get a value from.
   *
   * @return the fraction stored in that register.
   */
  public BigFraction get(char r) {
    if ((REGISTER_MIN > (int) r) || ((int) r > REGISTER_MAX)) {
      System.err.printf("Error: Register '%c' does not exist.", r);
    } // if
    return this.register[(int) r - REGISTER_MIN];
  } // get(char)
} // BFRegisterSet
