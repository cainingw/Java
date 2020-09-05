package hw4;

import graph.Cell;

public class CellUtil
{
  /**
   * Sets the mouse distance for the given cell and recursively sets
   * the mouse distance for all neighboring cells that a) do not already 
   * have a larger mouse distance and b) are open or passable.  Neighboring
   * cells satisfying these conditions are set to <code>distance - 1</code>.
   * If the given <code>distance</code> is less than or equal to zero, this 
   * method does nothing.
   * @param cell
   *   the cell whose distance is to be set
   * @param distance
   *   the distance value to be set in the given cell
   */
  public static void calculateMouseDistance(Cell cell, int distance)
  {
	  if (distance <= 0) return;
	  cell.setMouseDistance(distance);
	  Cell[] neighs = cell.getNeighbors();
	  for (int i = 0; i < neighs.length; i++) {  
		  if (neighs[i].getMouseDistance() < distance) {
			  if (neighs[i].getState() == null || neighs[i].getState().isPassable() == true) {
			 
				  neighs[i].setMouseDistance(distance-1);
				  calculateMouseDistance(neighs[i], distance-1);
			  }
		  }
	  }
  }
}
