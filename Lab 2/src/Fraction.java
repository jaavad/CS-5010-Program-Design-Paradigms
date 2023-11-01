/**
 * It adds two fraction objects and returns the decimal value of a fraction.
 * This interface represents simple fractions and implement some functionality for them.
 */

public interface Fraction {

  /**
   * A method to add two fraction objects: Fraction add(Fraction other).</li>
   *
   * @param other
   * @return
   */

  Fraction add(Fraction other);

  /**
   * A method to add a fraction with another given as a numerator and denominator.
   * This method should throw an IllegalArgumentException exception if the fraction provided to it
   * is negative.
   *
   * @param numerator
   * @param denominator
   * @return
   */
  Fraction add(int numerator, int denominator);

  /**
   * A method that returns the decimal value of a fraction, rounded to the given number of places.
   *
   * @param places
   * @return
   */
  double getDecimalValue(int places);


}
