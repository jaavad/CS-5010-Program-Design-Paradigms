package betterburrito;

import java.util.Map;

import burrito.PortionSize;
import burrito.Protein;
import burrito.Size;
import burrito.Topping;

/**
 * This class represents a custom burrito that can have an arbitrary number
 * of proteins and toppings, both of arbitrary portion sizes.
 */
public class CustomBurrito implements ObservableBurrito {
  protected Size size;
  protected final Map<Protein, PortionSize> proteins;
  protected final Map<Topping, PortionSize> toppings;

  /**
   * Create a custom burrito.
   *
   * @param size     the size of this burrito
   * @param proteins the proteins of this burrito
   * @param toppings the toppings of this burrito
   */

  protected CustomBurrito(Size size, Map<Protein, PortionSize> proteins,
                          Map<Topping, PortionSize> toppings) {

    if (size == null) {
      throw new IllegalStateException();
    }

    this.size = size;
    this.proteins = proteins;
    this.toppings = toppings;

  }

  /**
   * this static inner class for building a CustomBurritoBuilder.
   */

  public static class CustomBurritoBuilder extends BurritoBuilder<CustomBurritoBuilder> {

    /**
     * this creates a CustomBurritoBuilder object.
     */
    public CustomBurritoBuilder() {
      super();
    }

    @Override
    protected CustomBurritoBuilder returnBuilder() {
      return this;
    }

    /**
     * this class returns a CustomBurrito object.
     * @return CustomBurrito object
     */
    public CustomBurrito build() {
      //use the currently set values to create the Customer object
      return new CustomBurrito(size1, proteins1, toppings1);
    }

  }


  @Override
  public PortionSize hasTopping(Topping name) {
    return this.toppings.getOrDefault(name, null);
  }

  @Override
  public PortionSize hasProtein(Protein name) {
    return this.proteins.getOrDefault(name, null);
  }

  @Override
  public double cost() {
    double cost = 0.0;
    for (Map.Entry<Protein, PortionSize> item : this.proteins.entrySet()) {
      cost += item.getKey().getCost() * item.getValue().getCostMultipler();
    }

    for (Map.Entry<Topping, PortionSize> item : this.toppings.entrySet()) {
      cost += item.getKey().getCost() * item.getValue().getCostMultipler();
    }
    return cost + this.size.getBaseCost();
  }


}
