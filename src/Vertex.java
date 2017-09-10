import java.util.Set;

/**
 * Vertex of a graph
 * 
 * @author rabiachaudry
 * @version 1.0
 * 
 * @param <V> id
 * @param <E> skills
 * @param <D> distance
 */
public interface Vertex<V,E,D> {
  
	/**
	 * Returns the vertex id.
	 * @return the element associated with the vertex 
	 */
	V getId();
	
	/**
	 * Sets the vertex Id.
	 * @param v the vertex id
	 */
	void setId(V v);
	
	/**
	 * Returns the skills (edge weight) associated with the vertex.
	 * @return the vertex skills.
	 */
	E getSkills();
	
	/** 
	 * Set the skills (edge weight) associated with the vertex.
	 * @param skills the vertex skills
	 */
	void setSkills(E skills);
	
	/**
	 * Returns the distance of the vertex from the source.
	 * @return the distance of the vertex
	 */
	D getDistance();
	
	/**
	 * Sets the distance of the vertex from the source.
	 * @param distance the distance of the vertex 
	 */
	void setDistance(D distance);
  
	/**
	 * Returns the predecessor of the vertex.
	 * @return the predecessor
	 */
	Vertex<V,E,D> getPredecessor();
	
	/**
	 * Sets the predecessor of the vertex
	 * @param predecesor the vertex to be set as the predecessor of the current vertex
	 */
	void setPredecessor(Vertex<V,E,D> predecesor);
	
	/**
	 * Returns the friends of the vertex.
	 * @return A set containing the friends of the vertex
	 */
	Set<V> getFriends();
	
	/**
	 * Sets the friends of the vertex.
	 * @param friends a set containing the ids of the user's friends
	 */
	void setFriends(Set<V> friends);
	
	/**
	 * Returns a boolean value representing the status of the visited flag.
	 * @return <b>true</b> if the vertex has been visited else <b>false</b>
	 */
	boolean isVisited();
	
	/**
	 * Sets the status of the visited flag to a boolean value
	 * @param visited a boolean value (<b>true</b> or <b>false</b>)
	 */
	void setVisited(boolean visited);
	
	/**
	 * Returns a hash code value for the object. 
	 * @return hash code value for the object as an <b>int</b>
	 */
	public int hashCode();
	
	/**
	 * Indicates whether some other object is "equal to" this one.
	 * @param obj the object passed for comparison
	 * @return a boolean value (<b>true</b> or <b>false</b>) indicating whether the two objects are equal or not.
	 */
	public boolean equals(Object obj);
	
}
