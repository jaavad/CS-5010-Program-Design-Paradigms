
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * SimpleFraction class Implements the Fraction interface. This class
 * contains all the method definitions that are common to the concrete
 * implementations of the {@link Fraction} interface. This class can only represent
 * a non-negative fraction. Any attempt to create a negative fraction through
 * the constructor should throw an IllegalArgumentException.
 *
 */


public class SimpleFraction implements Fraction {


  private final int numerator;
  private final int denominator;

  /**
   * Constructs a {@code SimpleFraction} object. This class should have
   * a single public constructor that takes the numerator and denominator
   * as integers as its only arguments. Any attempt to create a negative fraction through
   * the constructor should throw an IllegalArgumentException. It should also throw an
   * IllegalArgumentException if the given denominator is zero. Note that these arguments
   * can be individually negative, even as the constructor imposes the above constraint.
   *
   * @param numerator1 the numerator of the fraction
   * @param denominator1  the denominator of the fraction
   */

  public SimpleFraction(int numerator1, int denominator1) {
  if (((numerator1 < 0) & (denominator1 > 0)) || ((numerator1 > 0) & (denominator1 < 0)) )
  {
    throw new IllegalArgumentException("Negative fractions are not supported");
  } else if (denominator1 ==0) {
    throw new IllegalArgumentException("The fraction would be infinite");
  }
    this.numerator = numerator1;
    this.denominator = denominator1;
  }


  /**
   * A method to add two fraction objects. The add method should not simplify the result.
   * The addition of "1/2" and "1/2" should be "2/2" and not "1/1"
   *
   * @param other
   * @return
   */

  public Fraction add(Fraction other) {

    return other.add(this.numerator,this.denominator);
  }

  /**
   * A method to add a fraction with another given as a numerator and denominator.
   * This method should throw an IllegalArgumentException exceptions if the fraction provided to
   * it is negative or the given denominator is zero. The add method should not simplify the result.
   * The addition of "1/2" and "1/2" should be "2/2" and not "1/1"
   *
   * @param numerator1
   * @param denominator1
   * @return
   */

  public Fraction add(int numerator1, int denominator1) {
    if (((numerator1 < 0) & (denominator1 > 0)) || ((numerator1 > 0) & (denominator1 < 0))) {
      throw new IllegalArgumentException("Negative fractions are not supported");
    } else if (denominator1 ==0) {
      throw new IllegalArgumentException("The fraction would be infinite");
    }

    if (denominator1 == this.denominator) {

      int numerator2 = numerator1 + this.numerator;
      int denominator2 = denominator1;

      return new SimpleFraction (numerator2, denominator2);

    } else{

      int denominator2 = denominator1 * this.denominator;
      int numerator2 =  this.denominator * numerator1 + denominator1 * this.numerator;

      return new SimpleFraction (numerator2, denominator2);
    }

  }

  /**
   * A method that returns the decimal value of a fraction, rounded to the given number of places.
   * This method should throw an IllegalArgumentException exception if the places number
   * provided to it is negative.
   *
   * @param places the given integer number of places for rounding.
   * @return the decimal value of a fraction.
   */
  public double getDecimalValue(int places) {
    if (places < 0) {
      throw new IllegalArgumentException("Negative place numbers are not acceptable");
    }
    double input = (float) this.numerator / this.denominator;
    BigDecimal result = new BigDecimal(input).setScale(places, RoundingMode.HALF_UP);

    return result.doubleValue();

  }



  /**
   *  The overridden toString method which returns a string of the form "n/d".
   *  For example, a fraction created with numerator 2 and 4 should return "2/4"
   *  through its toString method, whereas a fraction created with numerator -4 and denominator -9
   *  should return "4/9" through its toString method.
   *
   * @return returns a string of the form "n/d" from a fraction
   */


  @Override
  public String toString() {

    return this.numerator + "/" + this.denominator;

  }


}
