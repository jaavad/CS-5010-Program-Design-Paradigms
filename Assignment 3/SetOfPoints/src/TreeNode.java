import java.util.List;


/**
 * this interface creat a Tree node and has some methods which are implemented by two
 * {@link LeafNode class} and {@link GroupNode class}.
 */

interface TreeNode {

  /**
   * this class adds a single Point2D object to the set. The behavior
   * of this method if is dependent on the implementation.
   *
   * @param point a single Point2D object
   * @param depth of the tree
   */
  void add(Point2D point, int depth);


  /**
   * this method  takes the center of a circle (as a Point2D object) and a radius as a double and
   * returns a List of all the points in this set that lie inside or on this circle.
   *
   * @param centerOfCircle the center of the circle
   * @param radius         the radius of the circle
   * @return a List of all the points in this set that lie inside or on this circle
   */

  List<Point2D> allPointsWithinCircle(Point2D centerOfCircle, double radius);

  /**
   * this method returns a List of all the points currently in this set.
   *
   * @return a List of all the points currently in this set
   */
  List<Point2D> getPoints();

  /**
   * this takes a single query point Point2D and returns the point in this set that is closest to
   * this query point. If no such point exists, this method should return null.
   *
   * @param point query point
   * @return the point in this set that is closest to the query point
   */
  Point2D closestPoint(Point2D point);


}
