import java.util.ArrayList;
import java.util.List;

/**
 * this class builds a leaf node and implement {@link TreeNode interface}.
 */


class LeafNode implements TreeNode {


  //List<Point2D> p;
  Point2D leaf;

  /**
   * this constructor builds a leaf node object.
   *
   * @param leaf Point2D point for leaf
   */

  LeafNode(Point2D leaf) {

    this.leaf = leaf;
    //this.p = p;

  }

  /**
   * this class adds a single Point2D object to the set as a leaf.
   *
   * @param point a single Point2D object
   * @param depth of the tree
   */
  @Override
  public void add(Point2D point, int depth) {


    if (leaf == null) {

      this.leaf = point;
      //p.add(point);
      // return new LeafNode(point,p);
      // new LeafNode(point,p);

    } else {
      int a = 0;
      int b = 0;
      int c = 0;

      List<Point2D> on = new ArrayList<Point2D>();
      on.add(leaf);
      TreeNode left = new LeafNode(null);
      TreeNode right = new LeafNode(null);

      if ((depth % 2) == 0) { //use vertical line
        //line is ax+by+c
        a = 1;
        b = 0;
        c = -leaf.get(0);
        int sd = signedDistance(point, a, b, c);
        if (sd < 0) {
          left = new LeafNode(point);
        } else if (sd > 0) {
          right = new LeafNode(point);
        } else {
          on.add(point);
        }
      } else {
        //line is ax+by+c
        a = 0;
        b = 1;
        c = -leaf.get(1);
        int sd = signedDistance(point, a, b, c);

        if (sd < 0) {
          left = new LeafNode(point);
        } else if (sd > 0) {
          right = new LeafNode(point);
        } else {
          on.add(point);
        }

      }

      //p.add(point);
      //return new GroupNode(left, right, p, on, a, b, c);
      new GroupNode(left, right, on, a, b, c);
    }


  }

  /**
   * this method returns a list containing the leaf node.
   *
   * @return a List containing the leaf node
   */

  @Override
  public List<Point2D> getPoints() {
    ArrayList<Point2D> list = new ArrayList<Point2D>();
    if (leaf != null) {
      list.add(leaf);
    }
    return list;
  }


  /**
   * this method  takes the center of a circle (as a Point2D object) and a radius as a double and
   * returns a list containing leaf node that lie inside or on this circle.
   *
   * @param centerOfCircle the center of the circle
   * @param radius         the radius of the circle
   * @return a List of the leaf that lies inside or on this circle
   */

  @Override
  public List<Point2D> allPointsWithinCircle(Point2D centerOfCircle, double radius) {

    List<Point2D> list = new ArrayList<Point2D>();

    if (leaf != null && leaf.distance(centerOfCircle) <= radius) {
      list.add(leaf);
    }
    return list;

  }

  /**
   * this takes a single query point Point2D and returns the point in this set that is closest to
   * this query point. If no such point exists, this method should return null.
   *
   * @param point query point
   * @return the point in this set that is closest to the query point
   */
  public Point2D closestPoint(Point2D point) {

    Point2D ppp = null;
    if (leaf != null) {
      ppp = leaf;
    }
    return ppp;
  }

  private int signedDistance(Point2D t, int a, int b, int c) { //find the signed distance of point
    // T from line ax+by+c=0
    return (a * t.get(0) + b * t.get(1) + c);
  }


}