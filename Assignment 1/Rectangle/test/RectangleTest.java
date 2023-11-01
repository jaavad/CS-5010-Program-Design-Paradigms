
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.rules.ExpectedException;
import java.util.NoSuchElementException;

/**
 * tests all specifications of {@link Rectangle}s.
 */


public class RectangleTest {

  private Rectangle first;
  private Rectangle second;
  private Rectangle third;
  private Rectangle fourth;

  private Rectangle fifth;

  private Rectangle sixth;

  @Before
  public void setup() {
    first = new Rectangle(0, 0, 3, 3);

    second = new Rectangle(4, 0, 1, 1);

    third = new Rectangle(0, 4, 1, 1);

    fourth = new Rectangle(-2, 0, 1, 1);

    fifth = new Rectangle(0, -2, 1, 1);

    sixth = new Rectangle(1, 1, 1, 1);


  }

  /**
   * Tests overridden toString method.
   */
  @Test
  public void testToString() {
    String expectedOutput = "x:2.0, y:3.0, w:1.0, h:2.0";
    assertEquals(expectedOutput, first.toString());
  }

  @Rule
  public ExpectedException exception = ExpectedException.none();

  /**
   * Testing Rectangle Constructor when gets negative width or height. We prepared for loop to test
   * all situations.
   */
  @Test
  public void testConstructorWhenWidthOrHeightIsNegativeOrZero() {

    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Negative and Zero width or height are not acceptable");
    for (int i = -1; i < 2; i = i + 1) {
      for (int j = -1; j < 2; i++) {
        new Rectangle(-1, 1, i, j);
      }
    }

  }

  /**
   * Testing Constructor when the given width and height are acceptable.
   */
  @Test
  public void testConstructorForAcceptableWidthAndHeight() {

    Rectangle acceptableRectangle = new Rectangle(1, 1, 8, 9);
    String expectedOutput = "x:1.0, y:1.0, w:8.0, h:9.0";
    assertEquals(expectedOutput, acceptableRectangle.toString());
  }

  /**
   * Testing Overlap method. This method should return true if this rectangle overlaps with other,
   * false otherwise. Touching is not overlapping.We detected four conditions in which there would
   * be never any overlap, otherwise there is always overlap between two rectangles.
   */
  @Test
  public void testOverlap() {

    // When there is overlap between two rectangles.
    boolean result = first.overlap(sixth);
    assertEquals(true, result);


    // When there is no overlap between two rectangles.

    result = first.overlap(second);
    assertEquals(false, result);

    result = first.overlap(third);
    assertEquals(false, result);

    result = first.overlap(fourth);
    assertEquals(false, result);

    result = first.overlap(fifth);
    assertEquals(false, result);

  }


  /**
   * Testing intersect method in which we should test 17 different if-then conditions. 16 different
   * situation when there is an overlap but needs to calculate the rectangle with different formula.
   * And one situation when there is no overlap.
   */

  // Testing throw a NoSuchElementException when there is no overlap.
  @Test
  public void testIntersectWhenThereIsNoOverlap() {
    exception.expect(NoSuchElementException.class);
    exception.expectMessage("No overlap between two rectangles");
    first.intersect(second);

  }

  /*
  Testing intersect method when return the correct Rectangle intersection.
  Here there are 16 different situations need to calculate the Rectangle intersection differently.
  For testing intersect we should calculate the expected values different from the formula provided
  in the Rectangle class to have a correct test. For calculating the expected value we use drawing
  technique in which both rectangles would be drawn and then the intersection values are measured.
   */
  @Test
  public void testIntersect() {

    /*
    x: X of the first Rectangle
    y: Y of the first Rectangle
    w: Width of the first Rectangle
    h: Height of the first Rectangle
    ox: X of the second Rectangle
    oy: Y of the second Rectangle
    ow: Width of the second Rectangle
    oh: Height of the second Rectangle
     */

    Rectangle firstRectangle = new Rectangle(0, 0, 4, 4);

    // When (ox >= x) & (oy >= y)
    //* (ox+ow) >= (x+w) && (oy+oh) >= (y+h)
    Rectangle secondRectangle = new Rectangle(1, 1, 4, 4);
    Rectangle result = firstRectangle.intersect(secondRectangle);
    Rectangle expected = new Rectangle(1, 1, 3, 3);
    assertEquals(expected, result);

    // (ox+ow) >= (x+w) && (oy+oh) < (y+h)
    secondRectangle = new Rectangle(1, 1, 4, 2);
    result = firstRectangle.intersect(secondRectangle);
    expected = new Rectangle(1, 1, 3, 2);
    assertEquals(expected, result);

    // (ox+ow) < (x+w) && (oy+oh) < (y+h)
    secondRectangle = new Rectangle(1, 1, 2, 2);
    result = firstRectangle.intersect(secondRectangle);
    expected = new Rectangle(1, 1, 2, 2);
    assertEquals(expected, result);

    // (ox+ow) < (x+w) && (oy+oh) >= (y+h)
    secondRectangle = new Rectangle(1, 1, 2, 4);
    result = firstRectangle.intersect(secondRectangle);
    expected = new Rectangle(1, 1, 2, 3);
    assertEquals(expected, result);


    //* When (ox >= x) & (oy < y)
    // (ox+ow) >= (x+w) && (oy+oh) >= (y+h)
    secondRectangle = new Rectangle(1, -1, 4, 6);
    result = firstRectangle.intersect(secondRectangle);
    expected = new Rectangle(1, 0, 3, 4);
    assertEquals(expected, result);

    // (ox+ow) >= (x+w) && (oy+oh) < (y+h)
    secondRectangle = new Rectangle(1, -1, 4, 4);
    result = firstRectangle.intersect(secondRectangle);
    expected = new Rectangle(1, 0, 3, 3);
    assertEquals(expected, result);

    // (ox+ow) < (x+w) && (oy+oh) < (y+h)
    secondRectangle = new Rectangle(1, -1, 2, 4);
    result = firstRectangle.intersect(secondRectangle);
    expected = new Rectangle(1, 0, 2, 3);
    assertEquals(expected, result);

    // (ox+ow) < (x+w) && (oy+oh) >= (y+h)
    secondRectangle = new Rectangle(1, -1, 2, 6);
    result = firstRectangle.intersect(secondRectangle);
    expected = new Rectangle(1, 0, 2, 4);
    assertEquals(expected, result);


    //* When (ox < x) & (oy < y)
    // (ox+ow) >= (x+w) && (oy+oh) >= (y+h)
    secondRectangle = new Rectangle(-1, -1, 6, 6);
    result = firstRectangle.intersect(secondRectangle);
    expected = new Rectangle(0, 0, 4, 4);
    assertEquals(expected, result);

    // (ox+ow) >= (x+w) && (oy+oh) < (y+h)
    secondRectangle = new Rectangle(-1, -1, 6, 4);
    result = firstRectangle.intersect(secondRectangle);
    expected = new Rectangle(0, 0, 4, 3);
    assertEquals(expected, result);

    // (ox+ow) < (x+w) && (oy+oh) < (y+h)
    secondRectangle = new Rectangle(-1, -1, 4, 4);
    result = firstRectangle.intersect(secondRectangle);
    expected = new Rectangle(0, 0, 3, 3);
    assertEquals(expected, result);

    // (ox+ow) < (x+w) && (oy+oh) >= (y+h)
    secondRectangle = new Rectangle(-1, -1, 4, 6);
    result = firstRectangle.intersect(secondRectangle);
    expected = new Rectangle(0, 0, 3, 4);
    assertEquals(expected, result);


    //* When (ox < x) & (oy >= y)
    // (ox+ow) >= (x+w) && (oy+oh) >= (y+h)
    secondRectangle = new Rectangle(-1, 1, 6, 4);
    result = firstRectangle.intersect(secondRectangle);
    expected = new Rectangle(0, 1, 4, 3);
    assertEquals(expected, result);

    // (ox+ow) >= (x+w) && (oy+oh) < (y+h)
    secondRectangle = new Rectangle(-1, 1, 6, 2);
    result = firstRectangle.intersect(secondRectangle);
    expected = new Rectangle(0, 1, 4, 2);
    assertEquals(expected, result);

    // (ox+ow) < (x+w) && (oy+oh) < (y+h)
    secondRectangle = new Rectangle(-1, 1, 4, 2);
    result = firstRectangle.intersect(secondRectangle);
    expected = new Rectangle(0, 1, 3, 2);
    assertEquals(expected, result);

    // (ox+ow) < (x+w) && (oy+oh) >= (y+h)
    secondRectangle = new Rectangle(-1, 1, 4, 4);
    result = firstRectangle.intersect(secondRectangle);
    expected = new Rectangle(0, 1, 3, 3);
    assertEquals(expected, result);

  }


  /**
   * Testing Union method in which we should test 16 different if-then conditions.
   */


  /*
  Testing union method.
  Here there are 16 different situations need to calculate the Rectangle union differently.
  For testing union we should calculate the expected values different from the formula provided
  in the Rectangle class to have a correct test. For calculating the expected value we use drawing
  technique in which both rectangles would be drawn and then the union values are measured.
   */
  @Test
  public void testUnion() {

    /*
    x: X of the first Rectangle
    y: Y of the first Rectangle
    w: Width of the first Rectangle
    h: Height of the first Rectangle
    ox: X of the second Rectangle
    oy: Y of the second Rectangle
    ow: Width of the second Rectangle
    oh: Height of the second Rectangle
     */


    Rectangle firstRectangle = new Rectangle(0, 0, 4, 4);

    // When (ox >= x) & (oy >= y)

    //* (ox+ow) >= (x+w) && (oy+oh) >= (y+h)
    Rectangle secondRectangle = new Rectangle(1, 1, 4, 4);
    Rectangle result = firstRectangle.union(secondRectangle);
    Rectangle expected = new Rectangle(0, 0, 5, 5);
    assertEquals(expected, result);

    // (ox+ow) >= (x+w) && (oy+oh) < (y+h)
    secondRectangle = new Rectangle(1, 1, 4, 2);
    result = firstRectangle.union(secondRectangle);
    expected = new Rectangle(0, 0, 5, 4);
    assertEquals(expected, result);

    // (ox+ow) < (x+w) && (oy+oh) < (y+h)
    secondRectangle = new Rectangle(1, 1, 2, 2);
    result = firstRectangle.union(secondRectangle);
    expected = new Rectangle(0, 0, 4, 4);
    assertEquals(expected, result);

    // (ox+ow) < (x+w) && (oy+oh) >= (y+h)
    secondRectangle = new Rectangle(1, 1, 2, 4);
    result = firstRectangle.union(secondRectangle);
    expected = new Rectangle(0, 0, 4, 5);
    assertEquals(expected, result);


    //* When (ox >= x) & (oy < y)
    // (ox+ow) >= (x+w) && (oy+oh) >= (y+h)
    secondRectangle = new Rectangle(1, -1, 4, 6);
    result = firstRectangle.union(secondRectangle);
    expected = new Rectangle(0, -1, 5, 6);
    assertEquals(expected, result);

    // (ox+ow) >= (x+w) && (oy+oh) < (y+h)
    secondRectangle = new Rectangle(1, -1, 4, 4);
    result = firstRectangle.union(secondRectangle);
    expected = new Rectangle(0, -1, 5, 5);
    assertEquals(expected, result);

    // (ox+ow) < (x+w) && (oy+oh) < (y+h)
    secondRectangle = new Rectangle(1, -1, 2, 4);
    result = firstRectangle.union(secondRectangle);
    expected = new Rectangle(0, -1, 4, 5);
    assertEquals(expected, result);

    // (ox+ow) < (x+w) && (oy+oh) >= (y+h)
    secondRectangle = new Rectangle(1, -1, 2, 6);
    result = firstRectangle.union(secondRectangle);
    expected = new Rectangle(0, -1, 4, 6);
    assertEquals(expected, result);


    //* When (ox < x) & (oy < y)
    // (ox+ow) >= (x+w) && (oy+oh) >= (y+h)
    secondRectangle = new Rectangle(-1, -1, 6, 6);
    result = firstRectangle.union(secondRectangle);
    expected = new Rectangle(-1, -1, 6, 6);
    assertEquals(expected, result);

    // (ox+ow) >= (x+w) && (oy+oh) < (y+h)
    secondRectangle = new Rectangle(-1, -1, 6, 4);
    result = firstRectangle.union(secondRectangle);
    expected = new Rectangle(-1, -1, 6, 5);
    assertEquals(expected, result);

    // (ox+ow) < (x+w) && (oy+oh) < (y+h)
    secondRectangle = new Rectangle(-1, -1, 4, 4);
    result = firstRectangle.union(secondRectangle);
    expected = new Rectangle(-1, -1, 5, 5);
    assertEquals(expected, result);

    // (ox+ow) < (x+w) && (oy+oh) >= (y+h)
    secondRectangle = new Rectangle(-1, -1, 4, 6);
    result = firstRectangle.union(secondRectangle);
    expected = new Rectangle(-1, -1, 5, 6);
    assertEquals(expected, result);


    //* When (ox < x) & (oy >= y)
    // (ox+ow) >= (x+w) && (oy+oh) >= (y+h)
    secondRectangle = new Rectangle(-1, 1, 6, 4);
    result = firstRectangle.union(secondRectangle);
    expected = new Rectangle(-1, 0, 6, 5);
    assertEquals(expected, result);

    // (ox+ow) >= (x+w) && (oy+oh) < (y+h)
    secondRectangle = new Rectangle(-1, 1, 6, 2);
    result = firstRectangle.union(secondRectangle);
    expected = new Rectangle(-1, 0, 6, 4);
    assertEquals(expected, result);

    // (ox+ow) < (x+w) && (oy+oh) < (y+h)
    secondRectangle = new Rectangle(-1, 1, 4, 2);
    result = firstRectangle.union(secondRectangle);
    expected = new Rectangle(-1, 0, 5, 4);
    assertEquals(expected, result);

    // (ox+ow) < (x+w) && (oy+oh) >= (y+h)
    secondRectangle = new Rectangle(-1, 1, 4, 4);
    result = firstRectangle.union(secondRectangle);
    expected = new Rectangle(-1, 0, 5, 5);
    assertEquals(expected, result);

  }


}