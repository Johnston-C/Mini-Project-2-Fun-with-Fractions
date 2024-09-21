package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * A simple implementation of arbitrary-precision Fractions.
 *
 * @author Cade Johnston
 */
public class BigFraction {

  // +--------+
  // | Fields |
  // +--------+

  /** The numerator. Can be any integer. */
  private BigInteger num;

  /** The denominator. Must be non-negative. */
  private BigInteger denom;

  // +--------------+
  // | Constructors |
  // +--------------+

  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * @param n The numerator of the fraction [BigInteger].
   * @param d The denominator of the fraction [BigInteger].
   */
  public BigFraction(BigInteger n, BigInteger d) {
    BigInteger gcd = n.gcd(d);
    this.num = n.divide(gcd);
    this.denom = d.divide(gcd);
    if (this.num.multiply(this.denom).abs().equals(this.num.multiply(this.denom))) {
      this.num = this.num.abs();
    } else {
      this.num = this.num.abs().negate();
    } // if / else
    this.denom = this.denom.abs();
  } // BigFraction(BigInteger, BigInteger)

  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * @param n The numerator of the fraction [int].
   * @param d The denominator of the fraction [int].
   */
  public BigFraction(int n, int d) {
    BigInteger bINum = BigInteger.valueOf(n);
    BigInteger bIDenom = BigInteger.valueOf(d);
    BigInteger gcd = bINum.gcd(bIDenom);
    this.num = bINum.divide(gcd);
    this.denom = bIDenom.divide(gcd);
    if (this.num.multiply(this.denom).abs().equals(this.num.multiply(this.denom))) {
      this.num = this.num.abs();
    } else {
      this.num = this.num.abs().negate();
    } // if / else
    this.denom = this.denom.abs();
  } // BigFraction(int, int)

  /**
   * Build a new fraction based on the input string.
   *
   * @param fracString The string to convert into a fraction.
   */
  public BigFraction(String fracString) {
    int split = fracString.indexOf("/");
    if (split == -1) {
      this.num = new BigInteger(fracString);
      this.denom = BigInteger.ONE;
    } else {
      this.num = new BigInteger(fracString.substring(0, split));
      this.denom = new BigInteger(fracString.substring(split + 1));
      BigInteger gcd = this.num.gcd(this.denom);
      if (this.num.multiply(this.denom).abs().equals(this.num.multiply(this.denom))) {
        this.num = this.num.abs().divide(gcd);
      } else {
        this.num = this.num.abs().negate().divide(gcd);
      } // if / else
      this.denom = this.denom.abs().divide(gcd);
    } // if / else
  } // BigFraction(String)

  // +---------+
  // | Methods |
  // +---------+

  /**
   * Add another faction to this fraction.
   *
   * @param addend
   *   The fraction to add.
   *
   * @return the result of the addition.
   */
  public BigFraction add(BigFraction addend) {
    return new BigFraction(this.num.multiply(addend.denom).add(addend.num.multiply(this.denom)),
        this.denom.multiply(addend.denom));
  } // add(BigFraction)

  /**
   * Subtract another faction from the current value and store the result.
   *
   * @param subtrahend
   *   The fraction to subtract with.
   *
   * @return the result of the subtraction.
   */
  public BigFraction subtract(BigFraction subtrahend) {
    return new BigFraction(
        this.num.multiply(subtrahend.denom).subtract(subtrahend.num.multiply(this.denom)),
        this.denom.multiply(subtrahend.denom));
  } // Subtract(BigFraction)

  /**
   * Multiply another faction to the current value and store the result.
   *
   * @param multiplier
   *   The fraction to mlultiply by.
   *
   * @return the result of the multiplication.
   */
  public BigFraction multiply(BigFraction multiplier) {
    return new BigFraction(this.num.multiply(multiplier.num),
        this.denom.multiply(multiplier.denom));
  } // multiply(BigFraction)

  /**
   * Divide another faction from the current value abnd store the result.
   *
   * @param divisor
   *   The fraction to divide by.
   *
   * @return the result of the division.
   */
  public BigFraction divide(BigFraction divisor) {
    return new BigFraction(this.num.multiply(divisor.denom), this.denom.multiply(divisor.num));
  } // divide(BigFraction)

  /**
   * Access the 'num' variable of a BigFraction object.
   *
   * @return the value of 'num'.
   */
  public BigInteger numerator() {
    return this.num;
  } // numerator()

  /**
   * Access the 'denom' variable of a BigFraction object.
   *
   * @return the value of 'denom'.
   */
  public BigInteger denominator() {
    return this.denom;
  } // denominator()

  /**
   * The BigFraction object's fraction represented as a String.
   *
   * @return The String equivalent of the BigFraction's fraction.
   */
  public String toString() {
    if (this.denom.equals(BigInteger.ONE)) {
      return this.num.toString();
    } // if
    return this.num + "/" + this.denom;
  } // toString()
} // class BigFraction
