import java.util.ArrayList;
import java.util.List;


/**
 * PointKDTree class that implements {@link SetOfPoints interface}.
 * An object of this class will represent a 2D K-D tree of points.
 */


public class PointKDTree implements SetOfPoints {


  private int threshold;

  private TreeNode node;

  //private List<Point2D> p;


  /**
   * This constructor builds a K-D tree that contains only these points. The constructor throw an
   * IllegalArgumentException if the list passed is null.
   * This implementation allow a leaf to contain at most 1 point.
   *
   * @param points list of points for building the K-D tree
   */

  public PointKDTree(List<Point2D> points) {
    if (points == null) {
      throw new IllegalArgumentException("The list is null");
    }

    // Sorting the points, just indices means integer?
    List<Point2D> px = points;
    px.sort((p1, p2) -> Integer.compare(p1.get(0), p2.get(0))); // why Integer?

    List<Point2D> py = points;
    py.sort((p1, p2) -> Integer.compare(p1.get(0), p2.get(0))); // why Integer?

    this.threshold = 1;

    this.node = recursiveBuildKdTree(points, px, py, threshold, 0);
    //this.p = points;
  }


  /**
   * this method takes a single Point2D object and adds this point to the set. The behavior
   * of this method if the point already exists in the set, is dependent on the implementation.
   *
   * @param point a single Point2D object
   */


  @Override
  public void add(Point2D point) {
    node.add(point, 0);
  }


  /**
   * this method returns a List of all the points currently in this set.
   *
   * @return a List of all the points currently in this set
   */
  @Override
  public List<Point2D> getPoints() {

    List<Point2D> list = new ArrayList<Point2D>();
    list = node.getPoints();

    return list;
  }


  /**
   * this method  takes the center of a circle (as a Point2D object) and a radius as a double and
   * returns a List of all the points in this set that lie inside or on this circle.
   *
   * @param centerOfCircle the center of the circle
   * @param radius         the radius of the circle
   * @return a List of all the points in this set that lie inside or on this circle
   */
  @Override
  public List<Point2D> allPointsWithinCircle(Point2D centerOfCircle, double radius) {

    List<Point2D> list = new ArrayList<Point2D>();

    list = node.allPointsWithinCircle(centerOfCircle, radius);

    return list;
  }

  /**
   * this takes a single query point Point2D and returns the point in this set that is closest to
   * this query point. If no such point exists, this method should return null.
   *
   * @param point query point
   * @return the point in this set that is closest to the query point
   */
  @Override
  public Point2D closestPoint(Point2D point) {

    Point2D p = node.closestPoint(point);

    return p;
  }


  private TreeNode recursiveBuildKdTree(List<Point2D> p, List<Point2D> px,
                                        List<Point2D> py, int threshold, int depth) {

    int a;
    int b;
    int c;
    int median;

    List<Point2D> pXBefore = new ArrayList<Point2D>();
    List<Point2D> pXAfter = new ArrayList<Point2D>();
    List<Point2D> pYBefore = new ArrayList<Point2D>();
    List<Point2D> pYAfter = new ArrayList<Point2D>();
    List<Point2D> on = new ArrayList<Point2D>();

    // diff List and ArrayList ?

    if (px.size() <= threshold) {

      if (px.size() == 0) {
        return new LeafNode(null);
      } else {
        Point2D point = px.get(0);
        return new LeafNode(point);
      }

    }


    if ((depth % 2) == 0) { //use vertical line
      median = px.get((px.size()) / 2).get(0); // x-coordinate of the middle point in Px
      //line is ax+by+c
      a = 1;
      b = 0;
    } else {
      median = py.get((py.size()) / 2).get(1); // x-coordinate of the middle point in Px
      //line is ax+by+c
      a = 0;
      b = 1;
    }
    c = -median;


    for (int i = 0; i < px.size(); i++) {
      int sd = signedDistance(px.get(i), a, b, c);
      if (sd < 0) {
        pXBefore.add(px.get(i));
      } else if (sd > 0) {
        pXAfter.add(px.get(i));
      } else {
        on.add(px.get(i));
      }
    }

    for (int i = 0; i < py.size(); i++) {
      int sd = signedDistance(py.get(i), a, b, c);
      if (sd < 0) {
        pYBefore.add(py.get(i));
      } else if (sd > 0) {
        pYAfter.add(py.get(i));
      }
    }

    //do the same for Py, but don't add points again to On

    TreeNode left = recursiveBuildKdTree(p, pXBefore, pYBefore, threshold, depth + 1);
    TreeNode right = recursiveBuildKdTree(p, pXAfter, pYAfter, threshold, depth + 1);
    return new GroupNode(left, right, on, a, b, c);
  }


  private int signedDistance(Point2D t, int a, int b, int c) { //find the signed distance of point
    // T from line ax+by+c=0
    return (a * t.get(0) + b * t.get(1) + c);
  }

}
