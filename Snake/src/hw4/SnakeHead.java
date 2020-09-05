package hw4;

import graph.Cell;
import java.awt.Color;
import main.Config;
import state.State;
import state.Snake;
import state.SnakeSegment;

/**
 * Snake head
 * @author Caining Wang
 */
public class SnakeHead implements State, Snake {

	/**
	 * The head that this segment "follows".
	 */
	private int length;
	/**
	 * The number for frames this state has persisted.
	 */
	private int timer;

	/**
	 * Creates a segment that looks like it's following a snake head.
	 * initial the value of length and timer
	 * @param snakeHead
	 *            The head of the snake
	 */
	public SnakeHead() {
		length = 4;
		timer = 0;
	}

	/**
	 *  the head of the snake shall have a timer with initial value 0. Upon reaching
MAX_SNAKE_TIMER, the timer resets. SnakeHeads also keep track of the length of a snake 
(i.e., they inherit from Snake), which should initially be set to 4. When the timer resets, the
snake shall try to find a random cell closer to the mouse, using the appropriate method of the
Cell class. If no such cell exists, it will then try to find any random open cell. If no such second
cell exists, then the player loses the game, and the program shall use a static method in Config to
end the game. The score is the total length of the snake at that point.
	 * @param cell
	 *            The cell that this state belongs to
	 * @see state.State#handle(graph.Cell)
	 */
	@Override
	public void handle(Cell cell) {
		timer += 1;
		if (timer >= Config.MAX_SNAKE_TIMER) {
			timer = 0;
			Cell c = cell.getRandomCloser();
			Cell temp = c;
			if (c != null) {
				if (temp.getState() instanceof Food) {
					length++;
					System.out.println(length);
				}
				cell.moveState(c);
				cell.setState(new SnakeSegment(this));				
			}
			else {
				c = cell.getRandomOpen();
				if (c == null) {
					Config.endGame(length); // end game
				}
			}
			
		}
		
	}

	/**
	 * Get the current color of the state (can be used for drawing).
	 * 
	 * @return The color of the state
	 */
	@Override
	public Color getColor() {
		return Color.blue;
	}

	/**
	 * Get whether or not the cell is passable. Affects whether or not a state
	 * can move through another state via random movement or moving closer to
	 * the mouse.
	 * 
	 * @return true iff the state is passable
	 * @see state.State#isPassable()
	 */
	@Override
	public boolean isPassable() {
		return false;
	}

	/**
	 * Get the character representation for this State. Used for loading map
	 * text files.
	 * 
	 * @return
	 *   character representation for this State
	 */
	@Override
	public char toChar() {
		return 'S';
	}

	/**
	 * The current length in cells of the snake.
	 * @return The length in cells
	 */
	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return length;
	}

}
