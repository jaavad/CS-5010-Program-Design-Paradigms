import org.junit.Test;

import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Test class for NaiveTemperatureMonitor implementation.
 */

public class NaiveTemperatureMonitorTest {

  private TemperatureMonitor thermostatList;
  private Thermostat thermostat1;
  private Thermostat thermostat2;


  @Before
  public void setUp() {

    thermostatList = new NaiveTemperatureMonitor();
    thermostat1 = new SimpleThermostat("A30", 30);
    thermostat2 = new SimpleThermostat("A40", 40);
  }


  @Test
  public void testAdd() {
    thermostatList.add(thermostat1);
    thermostatList.add(thermostat2);
    assertEquals(2, thermostatList.getNumberOfThermostats());
  }

  @Test
  public void testRemove() {
    Thermostat thermostat3 = new SimpleThermostat("A20", 20);
    thermostatList.add(thermostat1);
    thermostatList.add(thermostat2);
    thermostatList.add(thermostat3);
    assertEquals(3, thermostatList.getNumberOfThermostats());
    thermostatList.remove(thermostat2);
    assertEquals(2, thermostatList.getNumberOfThermostats());
    thermostatList.remove(thermostat1);
    assertEquals(1, thermostatList.getNumberOfThermostats());

  }

  @Test
  public void testGetNumberOfThermostats() {
    thermostatList.add(thermostat1);
    assertEquals(1, thermostatList.getNumberOfThermostats());
  }

  @Test
  public void testTooMuchHeatingFalse() {

    Thermostat thermostatA = new SimpleThermostat("A40", 40);
    Thermostat thermostatB = new SimpleThermostat("A40", 40);
    Thermostat thermostatC = new SimpleThermostat("A20", 20);
    thermostatList.add(thermostatA);
    thermostatList.add(thermostatB);
    thermostatList.add(thermostatC);
    assertFalse(thermostatList.tooMuchHeating());
  }

  @Test
  public void testTooMuchHeatingTrue() {
    //The provided NaiveTemperatureMonitor class has a flaw:
    // sometimes it reports too much heating even if only one physical thermostat is cranked up!
    // Write one or more tests that should pass on a flawless implementation, but fails on
    // the given one.
    Thermostat thermostatA = new SimpleThermostat("A40", 40);
    Thermostat thermostatB = new SimpleThermostat("A45", 45);
    Thermostat thermostatC = new SimpleThermostat("A20", 20);
    thermostatList.add(thermostatA);
    thermostatList.add(thermostatB);
    thermostatList.add(thermostatC);
    assertTrue(thermostatList.tooMuchHeating());
  }

}







  @Override
  public boolean find(String[] words) {
    int jj =0;
    int check=0;
    int er = words.length;
    int re = this.size();
    for (int i=0; i <= words.length-1;i++) {
      check=0;
      for (int j =jj ; j<= this.size()-1;j++ ) {

        if (this.get(j).toLowerCase().equals(words[i].toLowerCase())) {
          jj=j+1;
          check=1;
          break;
        }
      }
    }
    return check == 1;
  }
