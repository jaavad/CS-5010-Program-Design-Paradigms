import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import org.junit.rules.ExpectedException;

/**
 * tests all specifications of {@link Fraction interface} and {@link SimpleFraction}.
 */
public class SimpleFractionTest {

  private Fraction first;
  private Fraction second;
  private Fraction third;



  /**
   * In this part we define some fraction objects used for testing methods.
   */
  @Before
  public void setup() {
    first = new SimpleFraction(2, 3);

    second = new SimpleFraction(0, 3);

    third = new SimpleFraction(0, 2);

  }

  /**
   * Tests overridden toString method.
   */
  @Test
  public void testToString() {
    String expectedOutput = "2/3";
    assertEquals(expectedOutput, first.toString());
  }

  /**
   *  Constructs an instance of the class under test representing a fraction by given numerator and
   *  denominator. Here we test three conditions: 1- testing throwing an IllegalArgumentException
   *  if the fraction wanted to creat is negative, 2- testing throwing an IllegalArgumentException
   *  if the given denominator is zero, 3- testing returning a fraction object using the given
   *  numerator and denominator.
   *
   */

    @Rule
    public ExpectedException exception = ExpectedException.none();


    /**
     * Testing Constructor when the fraction wanted to creat is negative.
     */
    @Test
    public void testConstructorWhenFractionIsNegative() {
      exception.expect(IllegalArgumentException.class);
      exception.expectMessage("Negative fractions are not supported");

      new SimpleFraction(-1, 1);
    }

    /**
     * Testing Constructor when the given denominator is zero.
     */
    @Test
    public void testConstructorWhenDenominatorIsZero() {
      exception.expect(IllegalArgumentException.class);
      exception.expectMessage("The fraction would be infinite");
      new SimpleFraction(1, 0);
    }

    /**
     * Testing Constructor when the given numerator and denominator are acceptable.
     */
    @Test
    public void testConstructorForAcceptableFraction() {

      Fraction acceptableSample = new SimpleFraction(1, 1);
      String expectedOutput = "1/1";
      assertEquals(expectedOutput, acceptableSample.toString());
    }



  /**
   * Testing add(Fraction other) method. Here are two conditions needed to test.
   * First when the denominators of both fractions are equal. Second when denominators of them
   * are not equal. In both conditions,the method should return a fraction object resulted from
   * summation of two given fractions.The add method should not simplify the result.
   */

  @Test
  public void testAdd() {
    String expectedOutput = "2/3";
    Fraction result = first.add(second);
    assertEquals(expectedOutput, result.toString());

    expectedOutput = "4/6";
    result = first.add(third);
    assertEquals(expectedOutput, result.toString());

  }


  /**
   * Testing add(int numerator,int denominator) method. Here are three conditions needed to test.
   * First testing throwing an IllegalArgumentException if the fraction wanted to creat is negative.
   * Second testing returning a fraction object using the given numerator and denominator.
   * Third testing returning a fraction object using the given numerator and denominator
   */

    @Test
    public void testAddWhenFractionIsNegative() {
      exception.expect(IllegalArgumentException.class);
      exception.expectMessage("Negative fractions are not supported");

      first.add(-1, 1);
    }

  /**
   * Testing Add method when the denominator of the fraction wanted to add is zero.
   */
    @Test
    public void testAddWhenDenominatorIsZero() {
      exception.expect(IllegalArgumentException.class);
      exception.expectMessage("The fraction would be infinite");
      first.add(1, 0);
    }

  /**
   * Testing Add method when the numerator and denominator of the fraction wanted to add are
   * acceptable.There are conditions needed to test, when the denominators of both fractions are
   * equal and when they are not equal.
   */
    @Test
    public void testAcceptableFractionSummation() {

      String expectedOutput = "2/3";
      Fraction result = first.add(0,3);
      assertEquals(expectedOutput, result.toString());

      expectedOutput = "4/6";
      result = first.add(0,2);
      assertEquals(expectedOutput, result.toString());

    }

  /**
   * Testing getDecimalValue method. Here are two conditions needed to test.
   * First, testing throwing an IllegalArgumentException exception if the places number
   * provided to the method is negative.
   * Second, when the places number provided to the method is positive.
   * So the output of getDecimalValue method needs to be tested.
   *
   */

  @Test
    public void testGetDecimalValueWhenPlaceNumberIsNegative() {
      exception.expect(IllegalArgumentException.class);
      exception.expectMessage("Negative place numbers are not acceptable");

      first.getDecimalValue(-1);
    }

    @Test
    public void testGetDecimalValue() {
      double expectedOutput = 0.6667;
      assertEquals(expectedOutput, first.getDecimalValue(4),0.1);
    }


}