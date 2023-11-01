import java.util.Objects;

/**
 * SimpleFraction class Implements the Thermostat interface. This class
 * contains all the method definitions that are common to the concrete
 * implementations of the {@link Thermostat} interface.
 */


public class SimpleThermostat implements Thermostat {

  private final String ID;
  private double temperature;

  /**
   * Constructs a {@code SimpleThermostat} object. This class represents the set temperature
   * in celsius. It cannot have a blank ID, or be set at any time to a temperature greater
   * than 50 degrees celsius.
   *
   * @param ID          Uniq ID of a thermostat
   * @param temperature set temperature of a thermostat
   */
  public SimpleThermostat(String ID, double temperature) {
    if (temperature > 50) {
      throw new IllegalArgumentException(" Temperature more than fifty is not supported");
    } else if (ID == null || ID.equals("")) {
      throw new IllegalArgumentException("It cannot have a blank ID");
    }
    this.ID = ID;
    this.temperature = temperature;
  }

  /**
   * A method to retrieve the thermostat's unique ID.
   *
   * @return the ID of the thermostat
   */

  @Override
  public String getID() {

    return this.ID;
  }

  /**
   * A method to get the temperature in degrees Kelvin.
   *
   * @return the set temperature of the thermostat in  degrees Kelvin
   */
  @Override
  public double getSetTemperature() {
    double temperatureKelvin = 273.15 + this.temperature;
    return temperatureKelvin;
  }

  /**
   * A method that increases the set temperature 0.1 degrees celsius for the thermostat
   */
  @Override
  public void increaseSetTemperature() {
    this.temperature = this.temperature + 0.1;
  }

  /**
   * A method that decreases the set temperature 0.1 degrees celsius for the thermostat
   */
  @Override
  public void decreaseSetTemperature() {
    this.temperature = this.temperature - 0.1;
  }

  /**
   * Two thermostats are the same if they have the same ID (case-sensitive), and their temperatures
   * are less than 0.005 degrees celsius apart.The issue of using less than 0.005 logic for
   * equality is that although in override equality we will consider two thermostats same with
   * less than 0.005 difference in temperature but in hash code they will not be the same because
   * they will have different temperatures.
   */


  /*
  @Override
  public boolean equals(Object o) {
    // Fast path for pointer equality:
    if (this == o) { //backward compatibility with default equals
      return true;
    }

    // If o isn't the Rectangle class then it can't be equal:
    if (!(o instanceof Thermostat)) {
      return false;
    }

    // The successful instanceof check means our cast will succeed:
    Thermostat that = (Thermostat) o;
    return (this.getID().equals(that.getID())) && (Math.abs(this.getSetTemperature() - that.getSetTemperature()) < 0.005);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ID,this.getSetTemperature());
  }

   */
  private double roundDecimal(double temperature) {

    double rfp = Math.pow(10, 2);
    return Math.round(temperature * (int) rfp) / rfp;

  }

  /**
   * Two thermostats are the same if they have the same ID (case-sensitive), and  if
   * their temperature values when rounded to two decimal places are the same. This equality method
   * does not have the previous problem because its hashcode also follow the rule which is
   * in equals method so whenever two thermostats are considered in equal method they would be
   * the same in hashcode too.
   */

  @Override
  public boolean equals(Object o) {
    // Fast path for pointer equality:
    if (this == o) { //backward compatibility with default equals
      return true;
    }

    // If o isn't the Rectangle class then it can't be equal:
    if (!(o instanceof Thermostat)) {
      return false;
    }

    // The successful instanceof check means our cast will succeed:
    Thermostat that = (Thermostat) o;
    return (this.getID().equals(that.getID())) &&
            (roundDecimal(that.getSetTemperature()) == roundDecimal(this.getSetTemperature()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(ID, roundDecimal(temperature));
  }

}
