import java.util.Collection;
import java.util.Set;

/**
 * Graph interface.
 * 
 * @author rabiachaudry
 * @version 1.0
 * 
 * @param <V> id
 * @param <E> skills 
 * @param <D> distance
 */
public interface Graph<V,E,D> {
	/**
	 * Returns the number of vertices in the graph as <b>int</b>
	 * @return number of vertices in the graph
	 */
	int numVertices();
	  	
	/**
	 * Inserts a new vertex in the graph with the given element, skills and friends.
	 * @param element - user id
	 * @param skills - skills of the user
	 * @param friends - friends of the user
	 * @return a vertex with the specified attributes
	 */
	Vertex<V, E, D> insertVertex(V id, E skills, Set<V> friends);
	
	/**
	 * Returns the set of vertices listed as the friends of the given vertex.
	 * @param vertex the id of the vertex
	 * @return the set of friends of the given vertex
	 */
	Set<V> getFriends(V vertex);
	
	/**
	 * Returns the Vertex identified by the specified vertex id
	 * @param v the vertex id
	 * @return the vertex identified by the id <b>v</b> or <b>null</b> if no such vertex exists
	 */
	public Vertex<V,E,D> getVertex(V v);
	
	/**
	 * Set the distance of a vertex.
	 * @param v the vertex id
	 * @param d the distance
	 */
	public void setDistance(V v, D d);
	
	/**
	 * Returns the distance of a vertex
	 * @param v the vertex id
	 * @return the distance of the vertex
	 */
	public D getDistance(V v);
	
	/**
	 * Set the predecessor of a vertex
	 * @param v the vertex id
	 * @param p the Vertex to be set as the predecessor
	 */
	public void setPredecessor(V v, Vertex<V,E,D> p);
	
	/**
	 * Returns the predecessor of a vertex
	 * @param v the vertex id
	 * @return the predecessor of the specified vertex
	 */
	public Vertex<V,E,D> getPredecessor(V v);
	
	/**
	 * Set the skills of a vertex.
	 * @param v the vertex id
	 * @param s the skills to be associate with the vertex
	 */
	public void setSkills(V v, E s);
	
	/**
	 * Returns the skills of a vertex.
	 * @param v the vertex id
	 * @return the skills associated with the vertex
	 */
	public E getSkills(V v);
	
	/**
	 * Utility function to print all the vertices in the graph.
	 */
	public void printGraph();
	
	/**
	 * Utility function to print a vertex
	 * @param vertex the vertex id
	 */
	public void printVertex(V vertex);
	
	/**
	 * Get all vertices in the graph
	 * @return a collection of all the vertices in the graph
	 */
	public Collection<Vertex<V, E, D>> getAllVertices();

	/**
	 * Remove a vertex
	 * @param v the vertex id
	 */
	public void remove(V v);
}
