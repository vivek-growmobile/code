package edu.upenn.cis.cis121.project;
import java.util.*;
import java.sql.*;

public class NetworkAlgorithms {
	
	/**
	 * A vertex class for holding a users id and a weight
	 * @author Vivek
	 *
	 */
	public class Vertex implements Comparable<Vertex> {
		private int user_id;
		private double weight;
		
		/**
		 * A Vertex Constructor
		 * @param id - the user's id
		 * @param w - the weight
		 */
		public Vertex (int id, double w){
			user_id = id;
			weight = w;
		}
		
		/**
		 * A Vertex Constructor
		 * @param id - the user's id
		 */
		public Vertex (int id){
			user_id = id;
		}

		public int getId(){
			return user_id;
		}

		public double getWeight(){
			return weight;
		}

		public void setWeight(double w){
			weight = w;
		}
		
		/**
		 * Compare two vertices by weight
		 */
		@Override
		public int compareTo(Vertex o) {
			if (weight < o.getWeight()){
				return -1;
			}
			else if (weight == o.getWeight()){
				return 0;
			}
			else {
				return 1;
			}
		}
	}
	
	/**
	 * A person class for holding an id weight an distance
	 * @author Vivek
	 *
	 */
	public class Person implements Comparable<Person>{
		private int id;
		private double[] location;
		private double distance;
		
		/**
		 * A person constructor
		 * @param uid - the person's id
		 * @param loc - the person's location
		 */
		public Person(int uid, double[] loc){
			id = uid;
			location = loc;
		}
		
		public int getId(){
			return id;
		}
		
		public double[] getLocation(){
			return location;
		}
		
		public void setDistance(double dist){
			distance = dist;
		}
		
		public double getDistance(){
			return distance;
		}
		
		/**
		 * Compare two people by distance
		 */
		@Override
		public int compareTo(Person o) {
			if (distance < o.getDistance()){
				return -1;
			}
			else if (distance == o.getDistance()){
				return 0;
			}
			else {
				return 1;
			}
		}
		
	}
	
	/**
	 * A Place class that holds a place id, location, likes, distance and suitability
	 * @author Vivek
	 *
	 */
	public class Place implements Comparable<Place> {
		private int id;
		private double[] location;
		private int likes;
		private double dist;
		private double suitability;
		
		/**
		 * A place constructor
		 * @param uid - the user's id
		 * @param loc - the user's location
		 */
		public Place (int uid, double[] loc){
			id = uid;
			location = loc;
		}
		
		public int getId(){
			return id;
		}
		
		public double[] getLocation(){
			return location;
		}
		
		public void setLikes(int l){
			likes = l;
		}
		
		public int getLikes(){
			return likes;
		}
		
		public void setDistance(double d){
			dist = d;
		}
		
		public double getDistance(){
			return dist;
		}
		
		public void setSuitability(double s){
			suitability = s;
		}
		
		public double getSuitability(){
			return suitability;
		}
		
		/**
		 * Compare two places based on the inverse of their suitability
		 */
		
		@Override
		public int compareTo(Place o) {
			if (suitability > o.getSuitability()){
				return -1;
			}
			else if (suitability == o.getSuitability()){
				return 0;
			}
			else {
				return 1;
			}
		}
	}

	private DBWrapper db;
	
	/**
	 * A network algorithms constructor
	 * @param dbUser - user name
	 * @param dbPass - password
	 * @param dbSID - SID
	 * @param dbHost - host number
	 * @param port - port number
	 */
	public NetworkAlgorithms(String dbUser, String dbPass, String dbSID, String dbHost, int port) {
		db = new DBWrapper(dbUser,dbPass,dbSID,dbHost,port);
	}
	
	/**
	 * A method to close the dbwrappers constructor
	 */
	public void closeDBConnection() {
		db.closeDBConnection();
	}

	/**
	 * A method for finding the Kevin Bacon distance between two friends
	 * @param user_id1 - user 1's id
	 * @param user_id2 - user 2's id
	 * @return
	 * @throws IllegalArgumentException if either user isn't in the database
	 */
	public int distance (int user_id1, int user_id2) throws IllegalArgumentException{
		Set<Integer> users = db.users();
		if (!users.contains(user_id1) || !users.contains(user_id2)){
			throw new IllegalArgumentException();
		}
		//A tracker for the distance
		int dist = 0;
		//Check if the user_1 is user_2
		if (user_id1 == user_id2){
			return dist;
		}
		//Increment the distance
		dist++;
		//An array for the current level
		int[] exploring = db.getFriends(user_id1);
		//A set of seen id's
		HashSet<Integer> seen = new HashSet<Integer>();
		seen.add(Integer.valueOf(user_id1));
		//While there are still users to explore
		while (exploring.length > 0){
			//Iterate through the current level to check if user2 is there
			for (int i = 0; i < exploring.length; i++){
				//If so return the distance
				if (exploring[i] == user_id2){
					return dist;
				}
			}
			//If not add all these people to seen
			for (int i = 0; i < exploring.length; i++){
				seen.add(Integer.valueOf(exploring[i]));
			}
			//An array list of the next level
			ArrayList<Integer> toexplore = new ArrayList<Integer>();
			//add all of the friends of the user
			for (int i = 0; i < exploring.length; i++){
				int curr = exploring[i];
				//Get all the friends of the current user
				int[] friends = db.getFriends(curr);
				for (int j = 0; j < friends.length; j++){
					if (!seen.contains(Integer.valueOf(friends[j])) && !toexplore.contains(Integer.valueOf(friends[j]))){
						toexplore.add(Integer.valueOf(friends[j]));
					}
				}
			}
			//Update the current level to the new level
			exploring = new int[toexplore.size()];
			for (int i = 0; i < toexplore.size(); i++){
				exploring[i] = (toexplore.get(i).intValue());
			}
			//Update the distance count
			dist++;
		}
		//If the person isn't found return -1
		return -1;
	}
	
	//A helper function for getting the weight between two people
	private double getWeight(int[] likes, int friend){
		// Get the place types of the user
		int[] usertypes = new int[likes.length];
		for (int i = 0; i < likes.length; i++){
			usertypes[i] = db.getPlaceType(likes[i]);
		}
		//Get the friends likes
		int[] friendlikes = db.getLikes(friend);
		int[] friendtypes = new int[friendlikes.length];
		//Get the place types for places the friend likes
		for (int i = 0; i < friendlikes.length; i++){
			friendtypes[i] = db.getPlaceType(friendlikes[i]);
		}
		//Compute the common types
		double comtypes = 0.0;
		boolean[] checked = new boolean[friendlikes.length];
		for (int i = 0; i < checked.length; i++){
			checked[i] = false;
		}
		for (int i = 0; i < usertypes.length; i++){
			for (int j = 0; j < friendtypes.length; j++){
				if (usertypes[i] == friendtypes[j] && checked[j] == false){
					comtypes++;
					checked[j] = true;
					break;
				}
			}
		}
		//Compute the common places
		double complaces = 0.0;
		for (int i = 0; i < likes.length; i++){
			for (int j = 0; j < friendlikes.length; j++){
				if (likes[i] == friendlikes[j]){
					complaces++;
				}
			}
		}
		return 1/(complaces + 0.1*(comtypes) + 0.01);
	}
	
	public List<Integer> recommendFriends(int user_id, int numRec) throws IllegalArgumentException{
		Set<Integer> users = db.users();
		if (!users.contains(user_id)){
			throw new IllegalArgumentException();
		}
		//Create a list of friend recommendations
		List<Integer> recs = new LinkedList<Integer>();
		//Make a min heap for the vertex cloud
		PriorityQueue<Vertex> exploring = new PriorityQueue<Vertex>();
		//Create a vertex of the user and add it to the cloud
		Vertex curr = new Vertex(user_id,0);
		exploring.add(curr);
		//Keep track of the users set of friends
		int[] origfriends = db.getFriends(user_id);
		int total = numRec;
		//While still looking for recommendations
		while (numRec > 0){
			if (recs.size() == total){
				break;
			}
			PriorityQueue<Vertex> dummy = new PriorityQueue<Vertex>();
			//Resort the heap with a placeholder heap
			while (!exploring.isEmpty()){
				dummy.add(exploring.remove());
			}
			exploring = dummy;
			//Remove the min
			curr = exploring.remove();
			//Get the likes of the current user
			int[] likes = db.getLikes(curr.getId());
			//Get the friends of the min
			int[] currfriends = db.getFriends(curr.getId());
			//For each of the users friends
			for (int i = 0; i < currfriends.length; i++){
				//Calculate the weight
				double weight = curr.getWeight() + getWeight(likes,currfriends[i]);
				boolean processed = false;
				//Check if this vertex is already in the queue
				for (Vertex user : exploring){
					if (user.getId() == currfriends[i]){
						//If it is and the new weight happens to be less update the weight
						if (weight < user.getWeight()){
							user.setWeight(weight);
						}
						//Mark that the friend has been processed
						processed = true;
					}
				}
				//If the friend hasn't been process a new vertex needs to be made and added
				if (!processed){
					Vertex toadd = new Vertex(currfriends[i],weight);
					exploring.add(toadd);
				}
			}
			boolean add = true;
			//Check if it is already in the recommended friends
			for (Integer rec : recs){
				if (rec == curr.getId()){
					add = false;
				}
			}
			//Check that it isn't the user
			if (curr.getId() == user_id){
				add = false;
			}
			//Check if the person is already friends with the user
			for (int f = 0; f < origfriends.length; f++){
				if (curr.getId() == origfriends[f]){
					add = false;
				}
			}
			//If they aren't in the list, not the user, or not friends with the 
			//user add them to the list
			if (add){
				recs.add(curr.getId());
				//Decrement the number of recs still needed
				numRec--;
			}
		}
		return recs;
	}
	
	public String recommendActivities(int user_id, int maxFriends, int maxPlaces) throws IllegalArgumentException {
		Set<Integer> users = db.users();
		if (!users.contains(user_id) || maxFriends < 1 || maxPlaces < 1){
			throw new IllegalArgumentException();
		}
		//Store the users location
		double[] userlocation = db.getUserLocation(user_id);
		//Get the friends of the user
		int[] friends = db.getFriends(user_id);
		//Make a priority queue to hold queues by distance
		PriorityQueue<Person> friendsbuf = new PriorityQueue<Person>();
		//For each friend in friends
		for (int i = 0; i < friends.length; i++){
			//Get the location of the friend
			double[] location = db.getUserLocation(friends[i]);
			//Create new person with the friend's id and location
			Person curr = new Person(friends[i],location);
			//Set the friend's distance from the user
			curr.setDistance(Math.sqrt(Math.pow((userlocation[0] - curr.getLocation()[0]),2) + Math.pow(userlocation[1] - curr.getLocation()[1],2)));
			//Add the new friend to the queue
			friendsbuf.add(curr);
		}
		//Determine the number of friends to add
		int length = maxFriends;
		if (friendsbuf.size() < maxFriends){
			length = friendsbuf.size();
		}
		//Create an array to hold the close friends
		Person[] closefriends = new Person[length];
		//Remove from the queue and add to the array
		for (int i = 0; i < length; i++){
			closefriends[i] = friendsbuf.remove();
		}
		//Keep track of the sum of the latitude and longitude
		double centerLat = 0.0;
		double centerLon = 0.0;
		centerLat += userlocation[0];
		centerLon += userlocation[1];
		for (int i = 0; i < length; i++){
			centerLat += closefriends[i].getLocation()[0];
			centerLon += closefriends[i].getLocation()[1];
		}
		//A double array to hold the central location
		double[] center = new double[2];
		//Find the average latitude and longitudes for the "center"
		center[0] = centerLat/(closefriends.length + 1);
		center[1] = centerLon/(closefriends.length + 1);
		ArrayList<Place> placeholder = new ArrayList<Place>();
		//Get the places the user likes
		int[] likes = db.getLikes(user_id);
		for (int i = 0; i < likes.length; i++){
			//For each of the places the person likes make a new place
			Place curr = new Place(likes[i],db.getLocation(likes[i]));
			//Update the places likes
			curr.setLikes(curr.getLikes() + 1);
			//Update the places distance
			curr.setDistance(Math.sqrt(Math.pow((center[0] - curr.getLocation()[0]),2) + Math.pow(center[1] - curr.getLocation()[1],2)));
			//Update the suitability
			curr.setSuitability((curr.getLikes()/(curr.getDistance() + 0.01)));
			//Add the place to the queue
			placeholder.add(curr);
		}
		//For each of the close friends
		for (int i = 0; i < closefriends.length; i++){
			//For each friend find the places they like
			int[] friendlikes = db.getLikes(closefriends[i].getId());
			//for each place the current friend likes
			for (int j = 0; j < friendlikes.length; j++){
				boolean processed = false;
				//Check the queue to see if the place is already there
				for (Place curr : placeholder){
					if (curr.getId() == friendlikes[j]){
						//If so just bump its likes
						curr.setLikes(curr.getLikes() + 1);
						//Then update its suitability
						curr.setSuitability((curr.getLikes()/(curr.getDistance() + 0.01)));
						//And mark it as processed
						processed = true;
					}
				}
				//If the place isn't already in the queue
				if (!processed){
					//Create a new place
					Place curr = new Place(friendlikes[j],db.getLocation(friendlikes[j]));
					//Set its likes
					curr.setLikes(curr.getLikes() + 1);
					//Set its distance from the center
					curr.setDistance(Math.sqrt(Math.pow((center[0] - curr.getLocation()[0]),2) + Math.pow(center[1] - curr.getLocation()[1],2)));
					//Set its suitability
					curr.setSuitability((curr.getLikes()/(curr.getDistance() + 0.01)));
					//Add it to the queue
					placeholder.add(curr);
					processed = true;
				}
			}
		}
		//Create a priority queue to hold places
	    PriorityQueue<Place> placesbuf = new PriorityQueue<Place>();
		while (!placeholder.isEmpty()){
			placesbuf.add(placeholder.remove(0));
		}
		//Determine how many places are wanted
		int length2 = maxPlaces;
		if (placesbuf.size() < maxFriends){
			length2 = placesbuf.size();
		}
		//Create a new place array to hold the places
		Place[] places = new Place[length2];
		//Remove as many places as desired from the queue
		for (int i = 0; i < length2; i++){
			places[i] = placesbuf.remove();
		}
		//Get the users info
		String[] userinfo = db.userInfo(user_id);
		userinfo[1] = userinfo[1].replaceAll("'", "");
		userinfo[2] = userinfo[2].replaceAll("'", "");
		//Add to JSON
		String json = "{\"user\":{\"user_id\":" + userinfo[0] +
				",\"first_name\":\"" + userinfo[1] +
				"\",\"last_name\":\"" + userinfo[2] +
				"\",\"latitude\":" + userinfo[3] +
				",\"longitude\":" + userinfo[4] + "}";
		if (closefriends.length > 0){
			json +=	",\"friends\": {\"";
		}
		int friendcount = 0;
		//Get each friends info
		for (int i = 0; i < closefriends.length; i++){
			String[] friendinfo = db.userInfo(closefriends[i].getId());
			friendinfo[1] = friendinfo[1].replaceAll("'", "");
			friendinfo[2] = friendinfo[2].replaceAll("'", "");
			//Add to JSON
			json += friendcount + "\" : {\"user_id\":" + friendinfo[0] + 
					",\"first_name\":\"" + friendinfo[1] +
					"\",\"last_name\":\"" + friendinfo[2] +
					"\",\"latitude\":" + friendinfo[3] +
					",\"longitude\":" + friendinfo[4] +
					"}";
			friendcount++;
			if (i + 1 == closefriends.length){
				json += "}";
			} 
			else {
				json += ",\"";
			}
		}
		json += "\"places\": {\"";
		int placecount = 0;
		//Get each places info
		for (int i = 0; i < places.length; i++){
			String[] placesinfo = db.placeInfo(places[i].getId());
			//Add to JSON
			json += placecount + "\" : {\"place_id\":" + placesinfo[0] +
					",\"place_name\":\"" + placesinfo[1] +
					"\",\"description\":\"" + db.getPlaceDescription(Integer.parseInt(placesinfo[2])) +
					"\",\"latitude\":" + placesinfo[3] +
					",\"longitude\":" + placesinfo[4] +
					"}";
			placecount++;
			if (i + 1 == places.length){
				json += "}";
			} 
			else {
				json += ",\"";
			}
		}
		json += "}";
		return json;			
	}
	
	public static void main(String[] args){
		NetworkAlgorithms na = new NetworkAlgorithms("vsiv","Siakvili","CIS","fling.seas.upenn.edu",1521);
		
		/*

		System.out.println(na.distance(0,0));
		System.out.println(na.distance(0,58));
		System.out.println(na.distance(0, 1));
		System.out.println(na.distance(1,324));
		*/
		
		List<Integer> list = na.recommendFriends(0, 4);
		for (Integer friend : list){
			System.out.println(friend);
		}
		/*
		System.out.println(na.getWeight(na.db.getLikes(84), 510));
		System.out.println(na.getWeight(na.db.getLikes(93), 433));
		System.out.println(na.getWeight(na.db.getLikes(100), 336));
		System.out.println(na.getWeight(na.db.getLikes(40), 41));
		*/
		na.closeDBConnection();
		
		/*
		String result = na.recommendActivities(197, 2, 2);
		System.out.println(result);
		*/
	}
	
	
	


}
