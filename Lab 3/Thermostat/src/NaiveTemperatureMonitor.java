import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * This class represents a temperature monitor. It tracks several thermostats, and specifically
 * monitors how many of them have been set to too hot.
 */

public class NaiveTemperatureMonitor implements TemperatureMonitor {
  private final List<Thermostat> thermostatList;

  /**
   * Temperature Monitor constructor that initializes the thermostatList.
   */

  public NaiveTemperatureMonitor() {

    this.thermostatList = new ArrayList<Thermostat>();
  }

  /**
   * Adds thermostat to thermostatList.
   *
   * @param t thermostat to add
   */

  public void add(Thermostat t) {

    thermostatList.add(t);
  }


  /**
   * Removes thermostat from thermostatList.
   *
   * @param t thermostat to remove
   */
  public void remove(Thermostat t) {

    thermostatList.remove(t);
  }

  /**
   * function gets number of thermostats.
   *
   * @return size of thermostat list
   */

  public int getNumberOfThermostats() {

    return thermostatList.size();
  }


  /**
   * Function returns too much heating based on number of thermostats that have high temperature.
   *
   * @return true if count > 1
   */

  public boolean tooMuchHeating() {
    int count = 0;
    HashSet<Thermostat> hs = new HashSet<>();
    for (Thermostat t : thermostatList) {
      if (!hs.contains(t) && t.getSetTemperature() > 23 + 273.15) {
        count += 1;
        hs.add(t);
      }

    }
    return count > 1;
  }
}
