package betterburrito;


import java.util.Map;

import burrito.PortionSize;
import burrito.Protein;
import burrito.Size;
import burrito.Topping;


/**
 * This class represents a veggie burrito. A veggie burrito has black beans,
 * medium salsa, cheese, lettuce, and guacamole, all in the regular portions.
 */
public class VeggieBurrito extends CustomBurrito {


  /**
   * Create a veggie burrito using the specified size.
   *
   * @param size the size of this burrito
   */
  private VeggieBurrito(Size size, Map<Protein, PortionSize> proteins,
                        Map<Topping, PortionSize> toppings) {
    super(size, proteins, toppings);
  }

  /**
   * this static inner class for building a VeggieBurritoBuilder.
   */

  public static class VeggieBurritoBuilder extends BurritoBuilder<VeggieBurritoBuilder> {

    /**
     * this creates a VeggieBurritoBuilder object.
     */
    public VeggieBurritoBuilder() {
      super();
      proteins1.put(Protein.BlackBeans, PortionSize.Normal);
      toppings1.put(Topping.MediumSalsa, PortionSize.Normal);
      toppings1.put(Topping.Cheese, PortionSize.Normal);
      toppings1.put(Topping.Lettuce, PortionSize.Normal);
      toppings1.put(Topping.Guacamole, PortionSize.Normal);
    }


    /**
     * this class returns a VeggieBurritoBuilder object.
     *
     * @return VeggieBurritoBuilder object
     */

    @Override
    protected VeggieBurritoBuilder returnBuilder() {
      return this;
    }

    /**
     * it removes Cheese from veggie burrito and return a VeggieBurritoBuilder object.
     *
     * @return VeggieBurritoBuilder
     */
    public VeggieBurritoBuilder noCheese() {
      toppings1.remove(Topping.Cheese);
      return returnBuilder();
    }

    /**
     * it removes BlackBeans from veggie burrito and return a VeggieBurritoBuilder object.
     *
     * @return VeggieBurritoBuilder
     */
    public VeggieBurritoBuilder noBlackBeans() {
      proteins1.remove(Protein.BlackBeans);
      return returnBuilder();
    }

    /**
     * it removes MildSalsa from veggie burrito and return a VeggieBurritoBuilder object.
     *
     * @return VeggieBurritoBuilder
     */
    public VeggieBurritoBuilder noMediumSalsa() {
      this.toppings1.remove(Topping.MildSalsa);
      return returnBuilder();
    }

    /**
     * it removes Lettuce from veggie burrito and return a VeggieBurritoBuilder object.
     *
     * @return VeggieBurritoBuilder
     */
    public VeggieBurritoBuilder noLettuce() {
      this.toppings1.remove(Topping.Lettuce);
      return returnBuilder();
    }

    /**
     * it removes Guacamole from veggie burrito and return a VeggieBurritoBuilder object.
     *
     * @return VeggieBurritoBuilder
     */
    public VeggieBurritoBuilder noGuacamole() {
      this.toppings1.remove(Topping.Guacamole);
      return returnBuilder();
    }

    /**
     * this class returns a VeggieBurrito object.
     *
     * @return VeggieBurrito object
     */
    public VeggieBurrito build() {
      //use the currently set values to create the Customer object
      return new VeggieBurrito(size1, proteins1, toppings1);
    }


  }


}
