import org.junit.Test;
import org.junit.Rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.rules.ExpectedException;

/**
 * tests all specifications of {@link SimpleThermostat}s.
 */


public class SimpleThermostatTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();

  /**
   * Testing Constructor when temperature is more than fifty.
   */
  @Test
  public void testConstructorWhenTemperatureIsMoreThanFifty() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Temperature more than fifty is not supported");

    new SimpleThermostat("D3e", 51);

  }

  /**
   * Testing Constructor when ID in blank.
   */
  @Test
  public void testConstructorWhenIdIsBlank() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("It cannot have a blank ID");

    new SimpleThermostat("", 1);
  }


  /**
   * Testing Constructor when given ID and set temperature are acceptable.
   */
  @Test
  public void testConstructorForAcceptableArguments() {

    Thermostat a = new SimpleThermostat("3D", 49);

    String expectedOutput = "3D";
    assertEquals(expectedOutput, a.getID());
    double expectedOutput1 = 49 + 273.15;
    assertEquals(expectedOutput1, a.getSetTemperature(), 0.005);

  }

  /**
   * Testing getID method.
   */
  @Test
  public void testGetID() {

    Thermostat a = new SimpleThermostat("3D", 49);
    String expectedOutput = "3D";
    assertEquals(expectedOutput, a.getID());
  }

  /**
   * Testing getSetTemperature method.
   */

  @Test
  public void testGetSetTemperature() {

    Thermostat a = new SimpleThermostat("3D", 49);
    double expectedOutput = 49 + 273.15;
    assertEquals(expectedOutput, a.getSetTemperature(), 0.005);

  }

  /**
   * Testing increaseSetTemperature method.
   */
  @Test
  public void testIncreaseSetTemperature() {

    Thermostat a = new SimpleThermostat("3D", 49);
    a.increaseSetTemperature();
    double expectedOutput = 49.1 + 273.15;
    assertEquals(expectedOutput, a.getSetTemperature(), 0.005);

  }

  /**
   * Testing decreaseSetTemperature method.
   */

  @Test
  public void testDecreaseSetTemperature() {

    Thermostat a = new SimpleThermostat("3D", 49);
    a.decreaseSetTemperature();
    double expectedOutput = 48.9 + 273.15;
    assertEquals(expectedOutput, a.getSetTemperature(), 0.005);

  }

  /*
  @Test
  public void testFirstEqualsMethod() {

    Thermostat thermostatA = new SimpleThermostat("3D", 49);
    Thermostat thermostatB = new SimpleThermostat("3D", 49);

    assertEquals(thermostatA, thermostatB);
    assertNotEquals(thermostatA.hashCode(),thermostatB.hashCode());
  }
   */

  /**
   * Two thermostats are the same if they have the same ID (case-sensitive), and  if
   * their temperature values when rounded to two decimal places are the same. This equality method
   * does not have the previous problem because its hashcode also follow the rule which is
   * in equals method so whenever two thermostats are considered in equal method they would be
   * the same in hashcode too.
   */

  @Test
  public void testSecondEqualsMethod() {

    Thermostat thermostatA = new SimpleThermostat("3D", 49);
    Thermostat thermostatB = new SimpleThermostat("3D", 49);

    assertEquals(thermostatA, thermostatB);
    assertEquals(thermostatA.hashCode(), thermostatB.hashCode());
  }

  /**
   * Testing case-sensitive.
   */

  @Test
  public void testCaseSensitive() {

    Thermostat thermostatA = new SimpleThermostat("3d", 49);
    Thermostat thermostatB = new SimpleThermostat("3D", 49);

    assertNotEquals(thermostatA, thermostatB);
    assertNotEquals(thermostatA.hashCode(), thermostatB.hashCode());
  }


}