import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
//import java.util.Stack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * SocialNetwork class is the application entry point. 
 * @author rabiachaudry
 * @version 1.0
 */
public class SocialNetwork {
	
	// Graph representing the social network
	private Graph<Long,Double,Double> graph;
	
	// Create a (min) priority queue to order the users by their distance from the source
	Queue<Entry<Long,Double>> pq;
	
	/**
	 * Default Constructor
	 */
	public SocialNetwork() {
		graph = new AdjacencyListMap<Long,Double,Double>();
		pq = new PriorityQueue<>();
	}

	/**
	 * Loads data from the JSON file into the graph. 
	 * 
	 * @param filename
	 * @return
	 */
	public boolean loadData(String filename) {
		// Initialize the graph 
		graph = new AdjacencyListMap<Long,Double,Double>();
		// Buffered reader for reading from file
		BufferedReader br = null;
		// JSON parser for parsing JSON file
		JSONParser parser = new JSONParser();
        
		try {
			// holds the last line read from the file
			String sCurrentLine;
			// set the file to be read
			br = new BufferedReader(new FileReader(filename));
			
			// while not EOF, read and parse each line
			while ((sCurrentLine = br.readLine()) != null) {
                Object object; 
                try {
                	// parse the current line
                	object = parser.parse(sCurrentLine);
                    JSONObject jsonObject = (JSONObject) object;
                    Long id = (Long) jsonObject.get("user");
                    Long skill = (Long)jsonObject.get("skill");
                    JSONArray friends = (JSONArray)jsonObject.get("friends");

                    // create a set of the user's friends 
                    Set<Long> friendsList = new HashSet<Long>();
                    for (Object friend : friends) {
            			friendsList.add((Long)friend);
            		}
                    
                    /* Add the user to the network. 
                     * Set skills to inverse i.e. it can range from 0.0-1.0 for values from 1.0 to Double.MAX_VALUE.
                     * Set skills = 2 where skills = 0 since 1/0 is infinity. 
                    */
                    this.graph.insertVertex(id, (Double)(skill == 0 ? 2.0 : 1.0/skill), friendsList);	// Using inverse skill to convert it into a shortest path problem
                    
                  // exception handling
                } catch(ParseException e) {
                	e.printStackTrace();
                }
			} // end while    
        } catch(FileNotFoundException fe) {
            fe.printStackTrace();
            return false;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (br != null)
                	br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
		return true;
	}

	/**
	 * Returns the number of users in the network.
	 * @return the total number of users in the network
	 */
	public Long numUsers() {
		return new Long(graph.numVertices());
		
	}
	
	/**
	 * Find the Shortest Path between two people in a network through the strongest coders 
	 * (Dijkstra's algorithm).
	 * @param source the source user 
	 * @param destination the destination user
	 * @return
	 */
	public Collection<Long> findShortestPathBetween(User<Long, Double, Double> source, User<Long, Double, Double> destination) {
		if (source == null || destination == null)
			return null;
		
		// initialize the priority queue
		pq = new PriorityQueue<>();
		// current entry extracted from the priority queue
		Entry<Long,Double> curr;
		
		// initialize the distance of the source to 0
		source.setDistance(new Double(0.0));
		
		// Add source to the pq 
		pq.add(new Entry<Long,Double>(source.getId(),source.getDistance()));
				
		while(!pq.isEmpty()) {
			// extract the friend with the shortest distance from the PQ
			curr = pq.poll();
			
			/* Stop searching if the destination has been found. 
			 * No need to traverse the rest of the paths.
			 * Improved efficiency in average case. */
			if (curr.getId() == destination.getId()) {
				break;
			}
			
			// Update distance of the friends from the current user 
			updateFriends(curr);
		}
		// return the path from the source to the destination, if it exists
		return pathFrom(source,destination);
	}
	
	/**
	 * Evaluates and updates the distance of friends to the shortest distance, if applicable
	 * @param v the entry pulled from the priority queue containing the user id and distance
	 */
	public void updateFriends(Entry<Long,Double> v) {
		// Get the Friend with the given Id
		User<Long,Double,Double> curr = (User<Long, Double, Double>) graph.getVertex(v.getId());
		// if it has been processed before, return without doing anything
		if (curr.isVisited()) 
			return;
		// othewise, update distance of all the friends where the new distance is less than the existing one
		for (Long f : curr.getFriends()) {
			User<Long,Double,Double> friend = getUser(f);
			if (!friend.isVisited() && friend.getDistance() > curr.getDistance() + friend.getSkills()) {
				friend.setDistance(curr.getDistance() + friend.getSkills());
				friend.setPredecessor(curr);
				// add an entry relating to the processed friend to the PQ
				pq.add(new Entry<Long, Double>(f,friend.getDistance()));
			}
		}
		// mark the current node as visited to avoid it from being processed again
		curr.setVisited(true);
	}
	
	/**
	 * Returns the path from the source to the destination user. 
	 * Returns null if a path is not found.
	 *  
	 * @param source the source user to find path from
	 * @param destination the destination user to find a path to
	 * @return the shortest path from the source to the destination user
	 */
	public List<Long> pathFrom (User<Long,Double,Double> source, User<Long, Double, Double> destination) {
		
		/* if either the source or destination is null or 
		 * source and destination are the same, return null */
		if (source == null || destination == null || source.getId() == destination.getId())
			return null;
		
		// Create an ArrayList to store the path 
		List<Long> path = new ArrayList<Long>();
				
		// start from the destination and trace back to source
		User<Long,Double,Double> curr = destination;
		
		// loop until the source is found or the user is null
		while (curr != null) {
			path.add(curr.getId());
			if (curr.getId() == source.getId())
				break;
			curr = (User<Long, Double, Double>) curr.getPredecessor(); 
		}
		
		// if there is no user, it means that the path was not found, hence, return null
		if (curr == null)	
			return null;
		
		// Reverse the list and return the path from source to destination
		Collections.reverse(path);
		return path;
	}
	
	/**
	 * Utility function to print all the users in the graph.
	 */
	public void printNetwork() {
		graph.printGraph();
	}

	/**
	 * Utility function to print a user
	 * @param id the user id
	 */
	public void printUser(Long id) {
		graph.printVertex(id);
	}
	
	/**
	 * Returns the user identified by the specified user id
	 * @param id the user id
	 * @return the user identified by the <b>id</b> or <b>null</b> if no such user exists
	 */
	public User<Long,Double,Double> getUser(Long id) {
		return (User<Long, Double, Double>) graph.getVertex(id);
	}
	
	/**
	 * Get all users in the graph
	 * @return a collection of all the users in the graph
	 */
	public Collection<Vertex<Long,Double,Double>> getAllUsers() {
		return graph.getAllVertices();
	}
	
	/**
	 * Utility function to print the path from source to destination.
	 * @param path the collection of the users connecting the source to destination
	 * including the source and the destination
	 */
	public void printPath(Collection<Long> path) {
		System.out.print("Path [");
		for (Long e : path) {
			System.out.print(e + " ");
		}
		System.out.println("]");
	}
	
	/**
	 * Returns the graph of the network
	 * @return the graph
	 */
	public Graph<Long, Double, Double> getMap() {
		return this.graph;
	}
	
}
