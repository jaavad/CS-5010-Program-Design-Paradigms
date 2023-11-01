import java.util.ArrayList;
import java.util.List;

/**
 * this class builds a group node and implement {@link TreeNode interface}.
 */

class GroupNode implements TreeNode {

  private TreeNode left;
  private TreeNode right;

  //private List<Point2D> p;
  private List<Point2D> on;

  private int a;
  private int b;
  private int c;


  /**
   * this constructor builds a group node object.
   *
   * @param left  child of the current tree
   * @param right child of the current tree
   * @param on    the current on the line (group node) nodes list
   * @param a     of the dividing line
   * @param b     of the dividing line
   * @param c     of the dividing line
   */


  GroupNode(TreeNode left, TreeNode right, List<Point2D> on, int a, int b,
            int c) {

    this.left = left;
    this.right = right;
    this.on = on;
    //this.p = p;
    this.a = a;
    this.b = b;
    this.c = c;
  }

  /**
   * this class adds a single Point2D object to the set. it does it recursively.
   *
   * @param point a single Point2D object
   * @param depth of the tree
   */
  @Override
  public void add(Point2D point, int depth) {
    //Treenode add(Point2D point,int depth,List<Point2D> p)
    int sd = signedDistance(point, a, b, c);

    if (sd < 0) {
      left.add(point, depth + 1);
      //p.add(point);
    } else if (sd > 0) {
      right.add(point, depth + 1);
      //p.add(point);
    } else {
      on.add(point);
      //p.add(point);
    }
    //return this;
  }

  /**
   * this method returns a List of all the points currently in the current set.
   * it does it recursively.
   *
   * @return a List of all the points in the current set
   */
  @Override
  public List<Point2D> getPoints() {
    List<Point2D> list = new ArrayList<Point2D>();
    List<Point2D> listLeft = new ArrayList<Point2D>();
    List<Point2D> listRight = new ArrayList<Point2D>();
    List<Point2D> listOnLine = new ArrayList<Point2D>();


    listLeft.addAll(left.getPoints());
    listRight.addAll(right.getPoints());
    listOnLine.addAll(on);

    list.addAll(listLeft);
    list.addAll(listRight);
    list.addAll(listOnLine);

    return list;

  }

  /**
   * this method  takes the center of a circle (as a Point2D object) and a radius as a double and
   * returns a List of all the points in the current set that lie inside or on this circle.
   * it does it recursively.
   *
   * @param centerOfCircle the center of the circle
   * @param radius         the radius of the circle
   * @return a List of all the points in this set that lie inside or on this circle
   */

  @Override
  public List<Point2D> allPointsWithinCircle(Point2D centerOfCircle, double radius) {

    List<Point2D> list = new ArrayList<Point2D>();
    List<Point2D> listLeft = new ArrayList<Point2D>();
    List<Point2D> listLeftRight = new ArrayList<Point2D>();
    List<Point2D> listRight = new ArrayList<Point2D>();
    List<Point2D> listRightLeft = new ArrayList<Point2D>();
    List<Point2D> listOnLine = new ArrayList<Point2D>();


    int sd = signedDistance(centerOfCircle, a, b, c);

    if (sd < 0) {

      listLeft = left.allPointsWithinCircle(centerOfCircle, radius);

      if (Math.abs(sd) < radius) {

        listLeftRight = right.allPointsWithinCircle(centerOfCircle, radius);
        listLeft.addAll(listLeftRight);

      }

    } else if (sd > 0) {
      listRight = right.allPointsWithinCircle(centerOfCircle, radius);

      if (Math.abs(sd) < radius) {
        listRightLeft = left.allPointsWithinCircle(centerOfCircle, radius);
        listRight.addAll(listRightLeft);
      }

    } else {

      for (int i = 0; i < on.size() - 1; i++) {
        if (on.get(i).distance(centerOfCircle) <= radius) {
          listOnLine.add(on.get(i));
        }
      }

    }

    list.addAll(listLeft);
    list.addAll(listRight);
    list.addAll(listOnLine);

    return list;

  }


  /**
   * this takes a single query point Point2D and returns the point in the current set that
   * is closest to this query point. If no such point exists, this method should return null.
   * it does it recursively.
   *
   * @param point query point
   * @return the point in this set that is closest to the query point
   */

  public Point2D closestPoint(Point2D point) {

    int sd = signedDistance(point, a, b, c);

    Point2D pp = null;

    Point2D center = point;
    double radius = Double.MAX_VALUE;

    if (sd < 0) {
      pp = left.closestPoint(center);

      if (pp != null) {
        radius = center.distance(pp);
      }

      if (pp == null || Math.abs(sd) <= radius) {

        Point2D ppp = null;

        // what if  on.size() =0 d --> for (int i = 0; i < on.size() - 1; i++)?
        for (int i = 0; i < on.size() - 1; i++) {
          if (center.distance(on.get(i)) <= radius) {
            ppp = on.get(i);
            radius = center.distance(on.get(i));
          }
        }


        // if we are finding in the right side why we again want to find it in the next step?

        if (ppp == null) {

          ppp = right.closestPoint(center);

        }

        if (ppp != null && center.distance(ppp) <= radius) {
          pp = ppp;
          radius = center.distance(ppp);
        }
      }

      // we already checked right side so why check again

      if (Math.abs(sd) <= radius) {

        List<Point2D> list = new ArrayList<Point2D>();
        Point2D ppp = null;
        list = right.allPointsWithinCircle(center, radius);

        for (int i = 0; i < list.size() - 1; i++) {
          if (center.distance(list.get(i)) <= radius) {
            ppp = list.get(i);
            radius = center.distance(list.get(i));
          }
        }

        if (ppp != null) {

          pp = ppp;

        }


      }


    } else {

      pp = right.closestPoint(center);

      if (pp != null) {
        radius = center.distance(pp);
      }

      if (pp == null || Math.abs(sd) <= radius) {

        Point2D ppp = null;

        for (int i = 0; i < on.size() - 1; i++) {
          if (center.distance(on.get(i)) <= radius) {
            ppp = on.get(i);
            radius = center.distance(on.get(i));
          }
        }


        // if we are finding in the right side why we again want to find it in the next step?

        if (ppp == null) {

          ppp = left.closestPoint(center);

        }

        if (ppp != null && center.distance(ppp) <= radius) {
          pp = ppp;
          radius = center.distance(ppp);
        }
      }

      // we already checked right side so why check again

      if (Math.abs(sd) <= radius) {

        List<Point2D> list = new ArrayList<Point2D>();
        Point2D ppp = null;
        list = left.allPointsWithinCircle(center, radius);

        for (int i = 0; i < list.size() - 1; i++) {
          if (center.distance(list.get(i)) <= radius) {
            ppp = list.get(i);
            radius = center.distance(list.get(i));
          }
        }

        if (ppp != null) {

          pp = ppp;

        }



      }

    }
    return pp;
  }


  private int signedDistance(Point2D t, int a, int b, int c) { //find the signed distance of point
    // T from line ax+by+c=0
    return (a * t.get(0) + b * t.get(1) + c);
  }


}



