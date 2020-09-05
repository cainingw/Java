package hw4;

import graph.Cell;

import java.awt.Color;

import state.State;

/**
 * Wall shape of map
 * @author Caining Wang
 */

public class Wall implements State {
    /**
	 * @param cell
	 *            The cell that this state belongs to
	 */
	public void handle(Cell cell)
    {
		return; // do not change
    }

	/**
	 * Get the current color of the state (can be used for drawing).
	 * 
	 * @return The color of the state
	 */
	public Color getColor()
    {
        return Color.white;
    }

	/**
	 * Get whether or not the cell is passable. Affects whether or not a state
	 * can move through another state via random movement or moving closer to
	 * the mouse.
	 * 
	 * @return true iff the state is passable
	 */
	public boolean isPassable()
    {
        return false;
    }

	/**
	 * Get the character representation for this State. Used for loading map
	 * text files.
	 * 
	 * @return
	 *   character representation for this State
	 */
	public char toChar()
    {
        return '#';
    }

}
