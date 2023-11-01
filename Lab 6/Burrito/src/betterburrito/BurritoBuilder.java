package betterburrito;

import java.util.HashMap;
import java.util.Map;

import burrito.PortionSize;
import burrito.Protein;
import burrito.Size;
import burrito.Topping;

/**
 * This class contains all the method definitions that are common to the concrete
 * classes {@link betterburrito.CustomBurrito.CustomBurritoBuilder} and
 * {@link betterburrito.VeggieBurrito.VeggieBurritoBuilder}.
 */

abstract public class BurritoBuilder<T extends BurritoBuilder<T>> {


  protected Size size1;
  protected final Map<Protein, PortionSize> proteins1;
  protected final Map<Topping, PortionSize> toppings1;

  /**
   * this creates a BurritoBuilder object.
   */
  public BurritoBuilder() {
    this.size1 = null;
    this.proteins1 = new HashMap<Protein, PortionSize>();
    this.toppings1 = new HashMap<Topping, PortionSize>();
  }

  /**
   * this changes the size of burrito.
   * @param size the size of burrito
   * @return BurritoBuilder
   */
  public T size(Size size) {
    this.size1 = size;
    //return this;
    return returnBuilder();
  }

  /**
   * this adds proteins to the burrito.
   * @param p the protein should be added
   * @param size the size of the protein
   * @return BurritoBuilder
   */
  public T addProtein(Protein p, PortionSize size) {
    this.proteins1.put(p, size);
    //return this;
    return returnBuilder();
  }

  /**
   * this adds toppings to the burrito.
   * @param p the topping should be added
   * @param size the size of the topping
   * @return BurritoBuilder
   */
  public T addTopping(Topping p, PortionSize size) {
    this.toppings1.put(p, size);
    //return this;
    return returnBuilder();
  }


  protected abstract T returnBuilder();

}
