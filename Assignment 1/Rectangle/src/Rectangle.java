import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class creat Rectangles and find their overlap, intersection and union.
 */


public class Rectangle {
  private final double X;
  private final double Y;
  private final double W;
  private final double H;


  /**
   * Constructs a {@code Rectangle} object. This class takes x,y w and h as x,y,width and height
   * of a rectangle respectively. Any attempt to create a rectangle with a negative width
   * or negative height through the constructor should throw an IllegalArgumentException.
   * It should also throw an IllegalArgumentException if the given denominator is zero.
   *
   * @param x The x coordinate of the rectangle's lower left corner.
   * @param y The y coordinate of the rectangle's lower left corner.
   * @param w The width of the rectangle.
   * @param h The height of the rectangle.
   * @throws IllegalArgumentException if any w (width) and h (height) are negative
   */
  public Rectangle(double x, double y, double w, double h) {
    if (w < 0 || h < 0 || w == 0 || h == 0) {
      throw new IllegalArgumentException("Negative and Zero width or height are not acceptable");
    }

    this.X = x;
    this.Y = y;
    this.W = w;
    this.H = h;

  }

  private ArrayList<Double> rectangleFeatures() {
    ArrayList<Double> list = new ArrayList<Double>();

    list.add(this.X);
    list.add(this.Y);
    list.add(this.W);
    list.add(this.H);

    return list;
  }


  @Override
  public boolean equals(Object o) {
    // Fast path for pointer equality:
    if (this == o) { //backward compatibility with default equals
      return true;
    }

    // If o isn't the Rectangle class then it can't be equal:
    if (!(o instanceof Rectangle)) {
      return false;
    }

    // The successful instanceof check means our cast will succeed:
    Rectangle that = (Rectangle) o;

    return (this.X == that.X) && (this.Y == that.Y) && (this.W == that.W) && (this.H == that.H);
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }


  /**
   * returns true if this rectangle overlaps with other, false otherwise.
   * Touching is not overlapping.We detected four conditions in which there would be never any
   * overlap, otherwise there is always overlap between two rectangles.
   *
   * <p>If we consider the attributes of two rectangles as following:
   *
   * <ul> <li>x: X of the first Rectangle
   * <li>y: Y of the first Rectangle
   * <li>w: Width of the first Rectangle
   * <li> h: Height of the first Rectangle
   * <li>ox: X of the second Rectangle
   * <li>oy: Y of the second Rectangle
   * <li>ow: Width of the second Rectangle
   * <li>oh: Height of the second Rectangle </li> </ul>
   *
   * <p>The four conditions in which there would be never overlap are:
   *
   * <ul><li>y  >= (oy+oh)
   * <li>oy >= (y+h)
   * <li>x  >  (ox+ow)
   * <li>ox >  (x+w) </li></ul>
   *
   * @param other Another Rectangle object
   * @return true if this rectangle overlaps with other, false otherwise
   */


  public boolean overlap(Rectangle other) {

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
    ArrayList<Double> attributes = other.rectangleFeatures();

    // The attributes of the first rectangle.
    double x = this.X;
    double y = this.Y;
    double w = this.W;
    double h = this.H;

    // The attributes of the second rectangle.
    double ox = attributes.get(0);
    double oy = attributes.get(1);
    double ow = attributes.get(2);
    double oh = attributes.get(3);


    return ((y < (oy + oh))) && ((oy < (y + h))) && ((x < (ox + ow))) && ((ox < (x + w)));
  }


  /**
   * returns a Rectangle object that represents the intersection of this rectangle
   * and the other rectangle (i.e. the region that overlaps between the two rectangles).
   * If no intersection exists, it should throw a NoSuchElementException with a helpful message.
   * We detected four conditions in which there would be never any overlap,
   * otherwise there is always overlap between two rectangles.
   *
   * <p>If we consider the attributes of two rectangles as following:
   *
   * <ul> <li>x: X of the first Rectangle
   * <li>y: Y of the first Rectangle
   * <li>w: Width of the first Rectangle
   * <li> h: Height of the first Rectangle
   * <li>ox: X of the second Rectangle
   * <li>oy: Y of the second Rectangle
   * <li>ow: Width of the second Rectangle
   * <li>oh: Height of the second Rectangle </li> </ul>
   *
   * <p>The four conditions in which there would be never overlap are:
   *
   * <ul> <li>y  >= (oy+oh)
   * <li>oy >= (y+h)
   * <li>x  >  (ox+ow)
   * <li>ox >  (x+w) </li> </ul>
   *
   * @param other Another Rectangle object
   * @return Rectangle object that represents the intersection
   * @throws NoSuchElementException if there is no overlap
   */


  public Rectangle intersect(Rectangle other) {

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

    ArrayList<Double> attributes = other.rectangleFeatures();

    double x = this.X;
    double y = this.Y;
    double w = this.W;
    double h = this.H;

    double ox = attributes.get(0);
    double oy = attributes.get(1);
    double ow = attributes.get(2);
    double oh = attributes.get(3);

    double interX = 0.0;
    double interY = 0.0;
    double interWidth = 0.0;
    double interHeight = 0.0;

    double re = oy + oh;
    if (re < Integer.MAX_VALUE)

      if ((y >= (oy + oh)) || (oy >= (y + h)) || (x >= (ox + ow)) || (ox >= (x + w))) {
        throw new NoSuchElementException("No overlap between two rectangles");
      }
    if ((ox >= x) & (oy >= y)) {
      interX = ox;
      interY = oy;

      if ((ox + ow) >= (x + w)) {
        interWidth = x + w - ox;
      } else {
        interWidth = ow;
      }
      if ((oy + oh) >= (y + h)) {
        interHeight = y + h - oy;
      } else {
        interHeight = oh;
      }


    } else if ((ox >= x) & (oy <= y)) {
      interX = ox;
      interY = y;

      if ((ox + ow) >= (x + w)) {
        interWidth = x + w - ox;
      } else {
        interWidth = ow;
      }
      if ((oy + oh) <= (y + h)) {
        interHeight = oy + oh - y;
      } else {
        interHeight = h;
      }

    } else if ((x >= ox) & (y >= oy)) {
      interX = x;
      interY = y;

      if ((x + w) >= (ox + ow)) {
        interWidth = ox + ow - x;
      } else {
        interWidth = w;
      }
      if ((y + h) >= (oy + oh)) {
        interHeight = oy + oh - y;
      } else {
        interHeight = h;
      }

    } else if ((x >= ox) & (y <= oy)) {
      interX = x;
      interY = oy;

      if ((x + w) >= (ox + ow)) {
        interWidth = ox + ow - x;
      } else {
        interWidth = w;
      }
      if ((y + h) <= (oy + oh)) {
        interHeight = y + h - oy;
      } else {
        interHeight = oh;
      }

    }
    return new Rectangle(interX, interY, interWidth, interHeight);
  }


  /**
   * returns a Rectangle object that represents the union of this rectangle and
   * the other rectangle. The union is the smallest rectangle that contains both rectangles.
   * Note that unlike the intersection, the union always exists.
   *
   * <p>we consider the attributes of two rectangles as following:
   *
   * <ul> <li>x: X of the first Rectangle
   * <li>y: Y of the first Rectangle
   * <li>w: Width of the first Rectangle
   * <li> h: Height of the first Rectangle
   * <li>ox: X of the second Rectangle
   * <li>oy: Y of the second Rectangle
   * <li>ow: Width of the second Rectangle
   * <li>oh: Height of the second Rectangle </li> </ul>
   *
   * @param other Another Rectangle object
   * @return Rectangle object that represents the union of this rectangle and the other rectangle
   */


  public Rectangle union(Rectangle other) {

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


    ArrayList<Double> attributes = other.rectangleFeatures();

    double x = this.X;
    double y = this.Y;
    double w = this.W;
    double h = this.H;

    double ox = attributes.get(0);
    double oy = attributes.get(1);
    double ow = attributes.get(2);
    double oh = attributes.get(3);

    double unionX = 0;
    double unionY = 0;
    double unionWidth = 0;
    double unionHeight = 0;


    if ((ox >= x) & (oy >= y)) {
      unionX = x;
      unionY = y;

      if ((ox + ow) >= (x + w)) {

        unionWidth = ox + ow - x;
      } else {
        unionWidth = w;
      }
      if ((oy + oh) >= (y + h)) {

        unionHeight = oy + oh - y;
      } else {
        unionHeight = h;
      }


    } else if ((ox >= x) & (oy <= y)) {
      unionX = x;
      unionY = oy;

      if ((ox + ow) >= (x + w)) {

        unionWidth = ox + ow - x;
      } else {
        unionWidth = w;
      }
      if ((oy + oh) <= (y + h)) {

        unionHeight = y + h - oy;
      } else {
        unionHeight = oh;
      }

    } else if ((x >= ox) & (y >= oy)) {
      unionX = ox;
      unionY = oy;

      if ((x + w) >= (ox + ow)) {

        unionWidth = x + w - ox;
      } else {
        unionWidth = ow;
      }
      if ((y + h) >= (oy + oh)) {

        unionHeight = y + h - oy;
      } else {
        unionHeight = oh;
      }

    } else if ((x >= ox) & (y <= oy)) {
      unionX = ox;
      unionY = y;

      if ((x + w) >= (ox + ow)) {

        unionWidth = x + w - ox;
      } else {
        unionWidth = ow;
      }
      if ((y + h) <= (oy + oh)) {

        unionHeight = oy + oh - y;
      } else {
        unionHeight = h;
      }

    }
    return new Rectangle(unionX, unionY, unionWidth, unionHeight);
  }

  @Override
  public String toString() {
    return "x" + ":" + this.X + "," + " " + "y" + ":" + this.Y + "," + " " + "w" + ":" + this.W
            + "," + " " + "h" + ":" + this.H;
  }
}
