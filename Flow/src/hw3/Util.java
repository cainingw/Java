package hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import api.Cell;
import api.Flow;

/**
 * Utility class with methods for creating Flows from string descriptors
 * and reading string descriptors from a file.  A string descriptor is
 * an array of strings, all with the same length, in which an alphabetic
 * character at a given row and column represents a cell at that
 * row and column.  The color of the cell is determined from the character
 * by the Cell constructor.  A descriptor is invalid if not all strings
 * are the same length or if there is an alphabetic character that 
 * does not appear exactly twice.
 * 
 * @author caining wang
 */



public class Util
{
  /**
   * Creates an array of Flow objects based on the string descriptor.
   * If the descriptor is invalid, this method returns null.
   * @param descriptor
   *   array of strings
   * @return
   *   array of Flow objects determined by the descriptor 
   */
  public static Flow[] createFlowsFromStringArray(String[] descriptor)
  {
    // TODO
	  ArrayList<Flow> flowGame = new ArrayList<Flow>();
	  if(checkValid(descriptor)){
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
		  Flow[] flows = new Flow[flowGame.size()];
		  for(int i = 0; i < flowGame.size();i++){
			  flows[i] = flowGame.get(i);
		  }			  
		  return flows; 
	  }
	  else{
		  return null;
	  }
    
  }
 
  /**
   * Reads the given file and constructs a list of FlowGame objects, one for
   * each descriptor in the file.  Descriptors in the file are separated by 
   * some amount of whitespace, but the file need not end with whitespace and
   * may have extra whitespace at the beginning.  Invalid descriptors in the file
   * are ignored, so the method may return an empty list.
   * @param filename
   *   name of the file to read
   * @return
   *   list of FlowGame objects created from the valid descriptors in the file
   * @throws FileNotFoundException
   */
  public static ArrayList<FlowGame> readFile(String filename) throws FileNotFoundException
  {
    // TODO
	  File file = new File(filename);
	  Scanner scanner = new Scanner(file);
	  ArrayList<FlowGame> flowGames = new ArrayList<FlowGame>();
	    ArrayList<String> descriptorList = new ArrayList<String>();
	    
	    while (scanner.hasNextLine())
	    {
	    	
	      String line = scanner.nextLine();
	      if(!line.trim().isEmpty()){
	    	  descriptorList.add(line);
	      }
	      else{
	    	  String[] descriptor = new String[descriptorList.size()];
	    	  descriptor = descriptorList.toArray(descriptor);
	    	  descriptorList.clear();
	    	  if(checkValid(descriptor)){
	    		  flowGames.add( new FlowGame(descriptor));
	    	  }
	      }	      
	    }
	    String[] descriptor = new String[descriptorList.size()];
    	  descriptor = descriptorList.toArray(descriptor);
    	  descriptorList.clear();
    	  if(checkValid(descriptor)){
    		  flowGames.add( new FlowGame(descriptor));
    	  }
	    scanner.close();
	    if(flowGames.size() > 0){
	    	return flowGames;
	    }
	    
	    else{
	    	return null;
	    }
    
  }
  
  /**
   * Check if it is a valid descriptor
   * @param descriptor
   *    the descriptor needs to check
   * @return
   * 	true if it is valid, false otherwise
   */
  
  private static boolean checkValid(String[] descriptor){
	  if(descriptor.length < 1){
			 return false;
		 }
	  for(int i = descriptor.length; i > 1; i--){
		  if(descriptor[i-1].length() != descriptor[i-2].length()){
			  return false;
		  }
	  }
	  ArrayList<String> color = new ArrayList<String>();
	  for(int i = 0; i < descriptor.length; i++){
		  for(int j = 0; j < descriptor[i].length();j++){
			  if(Character.isAlphabetic(descriptor[i].charAt(j))){
				  color.add(String.valueOf(descriptor[i].charAt(j)));
			  }
		  }
	  }
	  for(int i = 0; i < color.size();i++){
		  int counter = 0;
		  for(int j = 0; j < color.size();j++){
			  if(color.get(i).equals(color.get(j)) && i!=j){
				  counter += 1;
			  }
		  }
		  if(counter != 1){
			  return false;
		  }
	  }
	return true;
	  
  }

}
