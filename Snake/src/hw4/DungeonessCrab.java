package hw4;

import graph.Cell;

import java.awt.Color;

import main.Config;

import state.State;

/**
 * DungeonessCrab shape of map
 * @author Caining Wang
 */

public class DungeonessCrab extends Food {
	
	
    /**
     * 
     * A subtype of Food that shall walk! Whenever the dungeoness crab's timer resets, it shall look for
a randomly open cell. If one exists, it shall move to that cell. 
	 * @param cell
	 *            The cell that this state belongs to
	 */
	public void handle(Cell cell)
    {
		timer += 1;
		
		if (timer >= Config.MAX_FOOD_TIMER ) {
			timer = 0;
			
			Cell c = cell.getRandomOpen();
			cell.setState(null);
			if (c != null) c.setState(this);
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
        return 'D';
    }

}
