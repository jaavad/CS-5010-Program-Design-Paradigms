package betterburrito;

import org.junit.Test;
import burrito.PortionSize;
import burrito.Protein;
import burrito.Size;
import burrito.Topping;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class for betterBurritos.
 */

public class BetterBurritoTest {



  /**
   * this tests CustomBurrito class.
   */
  @Test
  public void testCustomBurrito() {
    ObservableBurrito customBurrito =  new CustomBurrito.CustomBurritoBuilder().
            size(Size.Normal).addTopping(Topping.Corn, PortionSize.Normal).
            addProtein(Protein.BlackBeans,PortionSize.Normal).build();
    double cost = customBurrito.cost();
    double expectedCost = 3.5;
    assertEquals(expectedCost, cost,0.1);
  }

  /**
   * this tests VeggieBurrito class.
   */

  @Test
  public void testVeggieBurrito() {

    ObservableBurrito veggieBurrito = new VeggieBurrito.VeggieBurritoBuilder().
            size(Size.Normal).noCheese().build();
    double cost = veggieBurrito.cost();
    double expectedCost = 6.0;
    assertEquals(expectedCost, cost,0.1);
  }


}