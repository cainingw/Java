package hw4;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import graph.Cell;
import hw4.SquareMap;

public class MapTest
{
  private static final String[] grid = {
    "----",
    "----",
    "----",    
  };
  
  public static void main(String[] args)
  {
    // set up a SquareMap using the string array to initialize
    TempSquareMap map = new TempSquareMap();
    map.initialize(grid);
    map.setDistance(10);
    
    // check width and height
    System.out.println(map.getPixelWidth());  // 50
    System.out.println(map.getPixelHeight()); // 40
    
    // try a polygon
    Polygon actual = map.createPolygon(0, 0);
    printPolygon(actual);  
    // expected [(5, 5), (15, 5), (15, 15), (5, 15)], however,
    // the points can be listed clockwise or counter clockwise, or
    // could start at a different corner
    
    // try out createNeighbors
    Cell[] neighbors = map.createNeighbors(2, 2);
    ArrayList<Cell> expected = new ArrayList<>();
    Cell[][] grid = map.getCells();
    expected.add(grid[2][1]);
    expected.add(grid[1][2]);
    expected.add(grid[2][3]);
    checkNeighbors(expected, neighbors);

    // try out selectClosestIndex
    Point closest = map.selectClosestIndex(34, 16);
    System.out.println("column " + closest.x + ", " + "row " + closest.y);
    // expected column 2, row 1
  }
  
  
  private static void printPolygon(Polygon p)
  {
    System.out.print("[");
    for (int i = 0; i < p.npoints; ++i)
    {
      if (i > 0) System.out.print(", ");
      System.out.print("(" + p.xpoints[i] + ", " + p.ypoints[i] + ")");     
    }
    System.out.println("]");
  }
  

  private static void checkNeighbors(ArrayList<Cell> expected, Cell[] neighbors)
  {
    // the neighbors are not required to be in any particular order, 
    // just make sure they are all there, and no extras
    if (expected.size() != neighbors.length)
    {
      System.out.println("Wrong number of neighbors: " + neighbors.length);
      return;
    }
    List<Cell> actual = Arrays.asList(neighbors);
    for (Cell c : actual)
    {
      if (!expected.contains(c))
      {
        System.out.println("Wrong neighbor present in array");
        return;
      }
    }
    for (Cell c : expected)
    {
      if (!actual.contains(c))
      {
        System.out.println("Missing neighbor in array");
        return;
      }
    }
  }
  
  
  // A "nested class" that will be identical to SquareMap, except that 
  // the getCells and selectClosestIndex methods are public so we can use them
  // in testing
  private static class TempSquareMap extends SquareMap
  {
    @Override
    public Cell[][] getCells()
    {
      return super.getCells();
    }
    
    @Override
    public Point selectClosestIndex(int x, int y)
    {
      return super.selectClosestIndex(x, y);
    } 
  }
}