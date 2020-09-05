package hw4;

import graph.Cell;
import main.Config;
import state.SnakeSegment;
import state.State;

public class test {

	public static void main(String[] args) {
		// simple test grid, three cells in a row
		// Create a cell whose state is DungeonessCrab and
				// give it an open neighbor. Note the color and
				// polygon won't be used, so we can leave them null
		// simple test grid, three cells in a row
	    Cell c0 = new Cell(null, null);
	    Cell c1 = new Cell(null, null);
	    Cell c2 = new Cell(null, null);
	    c2.setNeighbors(new Cell[]{});
	    c1.setNeighbors(new Cell[]{c2});
	    c0.setNeighbors(new Cell[]{c1});
	  
	    // make a snake head
	    SnakeHead head = new SnakeHead();
	    c0.setState(head);
	    
	    // do enough updates that it should "move"
	    for (int i = 0; i < Config.MAX_SNAKE_TIMER; ++i)
	    {
	      c0.update();
	      c1.update();
	      c2.update();
	    }
	    
	    // now c0 should be a segment, and the head should be at c1
	    System.out.println(c0.getState().getClass()); // should be SnakeSegment
	    System.out.println(c1.getState() == head);    // should be true
	    System.out.println(c2.getState());            // should be null
	    System.out.println(head.getLength()); // expected 4
	   
	    // ok, now try some food
	    c2.setState(new Food());
	    
	    // do enough updates that it should "move" again
	    for (int i = 0; i < Config.MAX_SNAKE_TIMER; ++i)
	    {
	      c0.update();
	      c1.update();
	      c2.update();
	    }
	 
	    System.out.println(c0.getState().getClass()); // should be SnakeSegment
	    System.out.println(c1.getState().getClass());    // should be SnakeSegment
	    System.out.println(c2.getState() == head);            // should be true
	    System.out.println(head.getLength()); // expected 5
	}

}
