

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.assertEquals;

/**
 * tests all specifications of {@link SetOfPoints interface} and {@link PointKDTree}.
 */


public class PointKDTreeTest {


  /**
   * this tests the constructor.
   */
  @Test
  public void constructorTest() {

    List<Point2D> listPoints = new ArrayList<Point2D>();
    Random rd = new Random(); // creating Random object
    System.out.println(rd.nextInt());
    for (int i = 0; i < 3; i++) {
      listPoints.add(new Point2D(rd.nextInt(), rd.nextInt()));
    }

    List<Point2D> expcetdList = listPoints;

    SetOfPoints pp = new PointKDTree(listPoints);

    assertEquals(true, expcetdList.containsAll(pp.getPoints()));

    //pp.add(new Point2D(3, 5));
    // assertEquals(false, expcetdList.containsAll(pp.getPoints()));
    //list expect

  }


  /**
   * this tests the constructor's exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void constructorTestException() {

    SetOfPoints pp = new PointKDTree(null);
  }


  /**
   * this test add method.
   */
  @Test
  public void addTest() {


    List<Point2D> listPoints = new ArrayList<Point2D>();
    Random rd = new Random(); // creating Random object

    for (int i = 0; i < 3; i++) {
      listPoints.add(new Point2D(rd.nextInt(), rd.nextInt()));
      // System.out.println(rd.nextInt());
    }
    List<Point2D> expcetdList = listPoints;


    for (int i = 0; i < listPoints.size(); i++) {
      System.out.println(listPoints.get(i).get(0));
      System.out.println(listPoints.get(i).get(1));
    }


    SetOfPoints pp = new PointKDTree(listPoints);

    List<Point2D> pList = pp.getPoints();
    System.out.println("p");
    for (int i = 0; i < pList.size(); i++) {
      System.out.println(pList.get(i).get(0));
      System.out.println(pList.get(i).get(1));
    }

    pp.add(new Point2D(3000, 50000));
    System.out.println("new pointList");

    for (int i = 0; i < listPoints.size(); i++) {
      System.out.println(listPoints.get(i).get(0));
      System.out.println("check");
      System.out.println(listPoints.get(i).get(1));
    }


    System.out.println("new p");
    List<Point2D> pList1 = pp.getPoints();
    for (int i = 0; i < pList1.size(); i++) {
      System.out.println(pList1.get(i).get(0));
      System.out.println(pList1.get(i).get(1));
    }


    assertEquals(false, expcetdList.containsAll(pp.getPoints()));
    //list expect


  }



}