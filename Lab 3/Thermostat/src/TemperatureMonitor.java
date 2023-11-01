
/**
 * TemperatureMonitor interface with public methods.
 */
public interface TemperatureMonitor {

  /**
   * Adds thermostat to thermostatList.
   *
   * @param t thermostat to add
   */

  void add(Thermostat t);

  /**
   * Removes thermostat from thermostatList.
   *
   * @param t thermostat to remove
   */

  void remove(Thermostat t);

  /**
   * function gets number of thermostats.
   *
   * @return size of thermostat list
   */

  int getNumberOfThermostats();

  /**
   * Function returns whether there is too much heating based on number of thermostats
   * that have high temperature.
   *
   * @return true if count > 1
   */
  boolean tooMuchHeating();



}
