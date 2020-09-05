package hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import api.Flow;

public class test {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		 File file = new File("a.txt");
		  Scanner scanner = new Scanner(file);
		  int i = 0;
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
		    	System.out.println(flowGames.size());
		    	for(int i1 = 0; i1 < flowGames.size(); i1++){
		    		System.out.println(flowGames.get(i1));
		    	}

		    } 
		    
		    else{
		    	System.out.println("null");
		    }

	}
	 public static ArrayList<FlowGame> readFile(String filename) throws FileNotFoundException
	  {
	    // TODO
		  File file = new File(filename);
		  Scanner scanner = new Scanner(file);
		  int counter = 0;
		  
		  while (scanner.hasNextLine()){
			  counter += 1;   
		  }
		  String[] line = new String[counter];
		  int i = 0;
		  while (scanner.hasNextLine()){
			  line[i] = scanner.nextLine();
		  }
	    return null;
	  }
	  
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
