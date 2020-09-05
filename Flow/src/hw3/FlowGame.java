package hw3;

import java.util.ArrayList;

import api.Cell;
import api.Flow;

/**
 * Game state for a Flow Free game.
 * 
 * 
 * @author Caining Wang
 */
public class FlowGame
{
	/**
	 * The sequence of flows in this game.
	 */
	private ArrayList<Flow> flowGame = new ArrayList<Flow>();
	
	/**
	 * Width for this game
	 */
	private int width;

	/**
	 * Height for this game
	 */
	private int height;
	
	/**
	 * The current cell which mouse selected
	 */
	private Cell current;
	
	/**
	 * The index of which flow has been selected
	 */
	private int currentFlow;
	
  /**
   * Constructs a FlowGame to use the given array of Flows and
   * the given width and height.  Client is responsible for ensuring that all
   * cells in the given flows have row and column values within
   * the given width and height.
   * @param givenFlows
   *   an array of Flow objects
   * @param givenWidth
   *   width to use for the game
   * @param givenHeight
   *   height to use for the game
   */
  public FlowGame(Flow[] givenFlows, int givenWidth, int givenHeight)
  {
    // TODO
	  for(int i = 0; i < givenFlows.length; i++){
		  flowGame.add(givenFlows[i]);
	  }
	  width = givenWidth;
	  height = givenHeight;
  }
  
  /**
   * Constructs a FlowGame from the given descriptor.
   * @param descriptor
   *   array of strings representing initial endpoint positions
   */
  public FlowGame(String[] descriptor)
  {
    // TODO
	  ArrayList<Cell> cells = new ArrayList<Cell>();
	  for(int i = 0; i < descriptor.length; i++){
		  for(int j = 0; j < descriptor[i].length();j++){
			  if(Character.isAlphabetic(descriptor[i].charAt(j))){
				  Cell thisCell = new Cell(i, j, descriptor[i].charAt(j));
				  cells.add(thisCell);
			  }
		  }
	  }
	  
	  for(int m = 0; m < cells.size(); m++){
		  for(int n = 0; n < cells.size(); n++){
			  if((cells.get(m).colorMatches(cells.get(n).getColor())) && m != n){
				  if(flowGame.size()>0){
					  int counter = 0;
					  for(int i = 0; i < flowGame.size();i++){ 
						  if((flowGame.get(i).getEndpoint(0).colorMatches(cells.get(n).getColor()))){
							  counter = 1;  
						  }
						 
					  }
					  if(counter == 0){
						  Flow thisFlow = new Flow(cells.get(m), cells.get(n));
						  flowGame.add(thisFlow);
					  }
				  }
				  else{
					  Flow thisFlow = new Flow(cells.get(m), cells.get(n));
					  flowGame.add(thisFlow);
				  }  
			  }
		  }
	  }
	 
	  height = descriptor.length;
	  width = descriptor[0].length();
  }

/**
   * Returns the width for this game.
   * @return
   *  width for this game
   */
  public int getWidth()
  {
    // TODO
    return width;
  }
  
  /**
   * Returns the height for this game.
   * @return
   *   height for this game
   */
  public int getHeight()
  {
    // TODO
    return height;
  }
  
  /**
   * Returns the current cell for this game, possible null.
   * @return
   *   current cell for this game
   */
  public Cell getCurrent()
  {
    // TODO
	return current;
	      
  }
  
  /**
   * Returns all flows for this game.  Client should not modify
   * the returned array or lists.
   * @return
   *   array of flows for this game
   */
  public Flow[] getAllFlows()
  {
    // TODO
	  Flow[] allFlows = new Flow[flowGame.size()];
	  for(int i = 0; i < flowGame.size(); i++){
		  allFlows[i]= flowGame.get(i);
	  }
    return allFlows;
  }
  
  /**
   * Returns the number of occupied cells in all flows (including endpoints).
   * @return
   *   occupied cells in this game
   */
  public int getCount()
  {
    // TODO
	  int count = 0;
	  for(int i = 0; i < height; i++){
		  for(int j = 0; j < width; j++){
			  if(isOccupied(i,j)){
				  count += 1;
			  }
		  }
	  }
    return count;
  }
  
  /**
   * Returns true if all flows are complete and all cells are occupied.
   * @return
   *   true if all flows are complete and all cells are occupied
   */
  public boolean isComplete()
  {
    // TODO
	  int counter = 0;
	  for(int i = 0; i < flowGame.size();i++){
		  if(flowGame.get(i).isComplete()){
			  counter += 1;
		  }  
	  }
	  int temp = getCount();
	  if(counter == flowGame.size() && temp == height * width){
		  return true;
	  }
	  else{
		  return false;
	  }
  }
  
  /**
   * Attempts to set the "current" cell to be an existing cell at the given
   * row and column.  When using a GUI, this method is typically 
   * invoked when the mouse is pressed.  
   * <ul>
   *   <li>Any endpoint can be selected as the current cell.  Selecting an 
   *   endpoint clears the flow associated with that endpoint.
   *   <li>A non-endpoint cell can be selected as the current cell only if 
   *   it is the last cell in a flow. 
   * </ul>
   * If neither of the above conditions is met, this method does nothing.
   * 
   * @param row
   *   given row
   * @param col
   *   given column
   */
  public void startFlow(int row, int col)
  {
    // TODO
	  for(int i = 0; i < flowGame.size(); i++){
		  if(flowGame.get(i).getEndpoint(0).positionMatches(row, col) || flowGame.get(i).getEndpoint(1).positionMatches(row, col)){
			  current = new Cell (row, col, flowGame.get(i).getColor());
			  flowGame.get(i).clear();
			  currentFlow = i;
		  }
		  if(flowGame.get(i).getCells().size()>0){
			  if(flowGame.get(i).getCells().get(flowGame.get(i).getCells().size()-1).positionMatches(row, col)){
				  current = new Cell (row, col, flowGame.get(i).getColor());
				  currentFlow = i;
			  }
		  }
		  
	  }
  }
  
  /**
   * Clears the "current" cell. That is, directly after invoking this method,
   * <code>getCurrent</code> returns null. When using a GUI, this method is 
   * typically invoked when the mouse is released.
   */
  public void endFlow()
  {
    // TODO
	  current = null;
  }
  
  /**
   * Attempts to add a new cell to the flow containing the current cell.  
   * When using a GUI, this method is typically invoked when the mouse is 
   * dragged.  In order to add a cell, the following conditions must be satisfied:
   * <ol>
   *   <li>The current cell is non-null
   *   <li>The given position is horizontally or vertically adjacent to the 
   *   current cell
   *   <li>The given position either is not occupied OR it is occupied by 
   *   an endpoint for the flow that is not already in the flow
   * </ul>
   * If the three conditions are met, a new cell with the given row/column 
   * and correct color is added to the current flow, and the current cell 
   * is updated to be the new cell.
   * 
   * @param row
   *   given row for the new cell
   * @param col
   *   given column for the new cell
   */
  public void addCell(int row, int col)
  {
    // TODO
	  if(current != null){
		  if(((row == current.getRow() + 1 || row == current.getRow() - 1 || row == current.getRow()) && col == current.getCol()) || 
				  ((col == current.getCol() + 1 || col == current.getCol() - 1 || col == current.getCol()) && row == current.getRow())){
			  if(!isOccupied(row, col) || isEndpoints(row, col) ){
				  
				  Cell thisCell = new Cell(row, col, current.getColor());
				  flowGame.get(currentFlow).add(thisCell);
				  if(!(flowGame.get(currentFlow).getEndpoint(0).positionMatches(row, col) && flowGame.get(currentFlow).getEndpoint(1).positionMatches(row, col))){					  
					  current = thisCell;
				  }
				  else{
					  current = new Cell(-1, -1, null);
				  }
			  }
		  }
	  }
	  
  }
  
  /**
   * Returns true if the given position is occupied by a cell in a flow in
   * this game (possibly an endpoint).
   * @param row
   *   given row
   * @param col
   *   given column
   * @return
   *   true if any cell in this game has the given row and column, false otherwise
   */
  public boolean isOccupied(int row, int col)
  {
    // TODO
	
	  for(int i = 0; i < flowGame.size(); i++){
		  for(int j = 0; j < flowGame.get(i).getCells().size(); j++){
			  if(flowGame.get(i).getCells().get(j).positionMatches(row, col)){
				  return true;
			  }
		  }
		  if(flowGame.get(i).getEndpoint(0).positionMatches(row, col) || flowGame.get(i).getEndpoint(1).positionMatches(row, col)){
			  return true;
		  }
	  }
	  
	  
    return false;
  }
  
  /**
   * Returns true if it is occupied by 
   * an endpoint for the flow that is not already in the flow
   * @param row
   * 	given row
   * @param col
   * 	given column
   * @return
   * 	true if this cell is occupied by an endpoint for the flow that is not already in the flow, false otherwise
   */
  private boolean isEndpoints(int row, int col){
	  if(flowGame.get(currentFlow).getEndpoint(0).positionMatches(row, col) || flowGame.get(currentFlow).getEndpoint(1).positionMatches(row, col)){
		  return true;
	  }
 
	return false;  
  }
  
}
