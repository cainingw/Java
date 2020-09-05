package hw4;

import graph.Cell;

import java.awt.Color;

import main.Config;

import state.State;

/**
 * Food shape of map
 * @author Caining Wang
 */

public class Food implements State {
	protected int timer;
	
	public Food(){
		timer = 0;
	}
	
    /**
     * Food shall have a timer (counter) that gets updated each time it handles a cell. When it reaches
the MAX_FOOD_TIMER, food shall reset its timer to its initial value, 0.
	 * @param cell
	 *            The cell that this state belongs to
	 */
	public void handle(Cell cell)
    {
		timer += 1;
		
		if (timer >= Config.MAX_FOOD_TIMER ) {
			timer = 0;
		}

    }

	/**
	 * Get the current color of the state (can be used for drawing).
	 * 
	 * @return The color of the state
	 */
	public Color getColor()
    {
		return Config.FOOD_COLORS[timer];
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
        return true;
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
        return 'F';
    }

}
