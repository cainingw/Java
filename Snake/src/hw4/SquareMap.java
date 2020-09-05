package hw4;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

import graph.GraphMap;
import graph.Cell;

/**
 * @author Caining Wang
 *
 */
public class SquareMap extends GraphMap {

	/**
	 * Set the distance between cells in pixels.
	 * 
	 * @param distance
	 *            The distance in pixels
	 */
	@Override
	public void setDistance(int distance) {
		super.setDistance(distance);
	}
	
	/**
	 * Gets the width of the window in pixels for rendering,
	 * including the border area.
	 * @return The width in pixels
	 */
	@Override
	public int getPixelWidth() {
		return (int)((this.getCells()[0].length + 1) * getDistance());
	}

	/**
	 * Gets the height of the window in pixels for rendering,
	 * including the border area.
	 * @return The height in pixels
	 */
	@Override
	public int getPixelHeight() {
		return (this.getCells().length+1) * getDistance();
	}

	/**
	 * Create an array of neighbors for the cell with given row and column.
	 * @param col The column index of a Cell
	 * @param row The row index of a Cell
	 * @return An array containing adjacent cells
	 */
	@Override
	public Cell[] createNeighbors(int x, int y) {
		
		Cell[][] map = getCells();
		
		int[][] relative = {{-1, 0},{0, 1},{1, 0},{0, -1}};

		List<Cell> cells = new ArrayList<Cell>();
		for(int i = 0; i < relative.length; i++){
			int yCoor = y + relative[i][0];
			int xCoor = x + relative[i][1];
			if (yCoor >= 0 && yCoor < map.length){
				if (xCoor >= 0 && xCoor < map[yCoor].length){
					cells.add(map[yCoor][xCoor]);
				}
			}
		}
		
		return cells.toArray(new Cell[0]);
	}

	/**
	 * Get the column and row indices for the cell closest to a given
	 * pixel (x, y) coordinate, returned as a Point object in which 
	 * x is the column and y is the row.
	 * @param x The x coordinate in pixels
	 * @param y The y coordinate in pixels
	 * @return column and row indices for the cell closest to the given (x, y)
	 */
	@Override
	protected Point selectClosestIndex(int x, int y) {
		x -= getDistance()/2;
		x /= getDistance();
        y -= getDistance()/2;
		y /= getDistance();
		
		return new Point(x, y);
	}

	/**
	 * Create a polygon for the cell with the given column and row.
	 * @param col The column index of a Cell
	 * @param row The row index of a Cell
	 * @return A polygon with correct pixel coordinates for rendering the cell
	 */
	@Override
	public Polygon createPolygon(int x, int y) {
		int yOffset0 = getDistance() / 2;
		yOffset0 += y * getDistance();

		int yOffset1 = yOffset0 + getDistance();

		int xOffset0 = getDistance() / 2;
		xOffset0 += x * getDistance();

		int xOffset1 = xOffset0 + getDistance();

		int[] yCoor = { yOffset0, yOffset1, yOffset1, yOffset0, yOffset0 };
		int[] xCoor = { xOffset0, xOffset0, xOffset1, xOffset1, xOffset0 };

		return new Polygon(xCoor, yCoor, 4);
	}

}
