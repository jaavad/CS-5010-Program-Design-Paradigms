import java.util.List;


/**
 * this interface represents a set of 2D points, that can be searched using KD Tree. It is
 * implemented by {@link PointKDTree class}.
 */

public interface SetOfPoints {

  /**
   * this method takes a single Point2D object and adds this point to the set. The behavior
   * of this method if the point already exists in the set, is dependent on the implementation.
   *
   * @param point a single Point2D object
   */

  void add(Point2D point);

  /**
   * this method returns a List of all the points currently in this set.
   *
   * @return a List of all the points currently in this set
   */
  List<Point2D> getPoints();


  /**
   * this method  takes the center of a circle (as a Point2D object) and a radius as a double and
   * returns a List of all the points in this set that lie inside or on this circle.
   *
   * @param centerOfCircle the center of the circle
   * @param radius         the radius of the circle
   * @return a List of all the points in this set that lie inside or on this circle
   */

  // radius is int or double?
  List<Point2D> allPointsWithinCircle(Point2D centerOfCircle, double radius);


  /**
   * this takes a single query point Point2D and returns the point in this set that is closest to
   * this query point. If no such point exists, this method should return null.
   *
   * @param point query point
   * @return the point in this set that is closest to the query point
   */
  Point2D closestPoint(Point2D point);

}
