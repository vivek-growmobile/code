import java.io.*;
import java.util.*;


/**
 * A class that takes a coordinate pair and a file of coordinates
 * and prints the coordinates in the file in sorted order according
 * to their distance from the input coordinate pair
 * 
 * @author Vivek
 *
 */
public class CoordDistance {
	private Integer coord1;
	private Integer coord2;
	private ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
	
	//Constructor stores inputed distance
	public CoordDistance(Integer c1, Integer c2){
		coord1 = c1;
		coord2 = c2;
	}
	
	/**
	 * Inner class Defining a Coordinate
	 * @author Vivek
	 *
	 */
	private class Coordinate implements Comparable<Coordinate> {
		private char label;
		private Integer _c1;
		private Integer _c2;
		private double dist;
		
		private Coordinate(char l, Integer c1, Integer c2){
			label = l;
			_c1 = c1;
			_c2 = c2;
			dist = calcDistance(c1, c2);
		}
		
		//How Coordinates are compared
		@Override 
		public int compareTo(Coordinate other){
			if (dist < other.dist) return -1;
			else if (dist > other.dist) return 1;
			else return 0;
		}
		
		//Print out a coordinates details
		public void printDetails() {
			System.out.println(label +  " " + _c1 + " " + _c2 + " " + dist);
		}
		
	}
	
	//Calculate the distance between a point and the input point
	public double calcDistance(Integer c1, Integer c2){
		return (Math.sqrt(Math.pow((coord1 - c1), 2) + Math.pow((coord2 - c2), 2)));
	}
	
	//Sort coordinates
	public Coordinate[] sortByDistance(){
		Coordinate[] sorted = new Coordinate[coordinates.size()];
		coordinates.toArray(sorted);
		Arrays.sort(sorted);
		return sorted;
	}
	

	public static void main(String args[]){
		if (args.length != 3){
			System.out.println("Usage: Improper number of Arguments");
			return;
		}

		Integer coord1 = Integer.parseInt(args[0]);

		Integer coord2 = Integer.parseInt(args[1]);

		CoordDistance cd = new CoordDistance(coord1, coord2);

		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(args[2])));
			//Read each line
			while (reader.ready()){
				//Split it by commas and then make a new Cooordinate
				//Then add the coordinate to the array list
				String line = reader.readLine();
				String[] divided = line.split(",");
				if (divided.length != 3) {
					throw new IllegalArgumentException();
				}
				char label = divided[0].charAt(0);
				Integer c1 = Integer.parseInt(divided[1]);

				Integer c2 = Integer.parseInt(divided[2]);
				Coordinate curr = cd.new Coordinate(label, c1, c2);
				cd.coordinates.add(curr);
			}
			//Sort the array and print its details
			Coordinate[] sorted = cd.sortByDistance();
			for (int i = 0; i < sorted.length; i++){
				sorted[i].printDetails();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (IllegalArgumentException a){
			System.out.println("File Was Malformed");
		} catch (IOException io){
			System.out.println("IO Exception");
		}
	}

}