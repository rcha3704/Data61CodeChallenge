import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Implements the Graph interface using a Map.
 * 
 * @author rabiachaudry
 * @version 1.0
 * 
 * @param <V> id 
 * @param <E> skills 
 * @param <D> distance
 */
public class AdjacencyListMap<V,E,D> implements Graph<V,E,D>{

	private Map<V, Vertex<V, E, D>> network;
	
	/**
	 * Default Constructor
	 */
	public AdjacencyListMap() {
		network = new HashMap<V, Vertex<V,E,D>>();
	}
	
	/**
	 * Returns the total number of vertices in the graph.
	 */
	@Override
	public int numVertices() {
		return this.network.size();
	}

	/**
	 * Inserts a new vertex in the graph with the given element, skills and friends.
	 * @param element - user id
	 * @param skills - skills of the user
	 * @param friends - friends of the user
	 * @return a vertex with the specified attributes
	 */
	@SuppressWarnings("unchecked")
	public Vertex<V,E,D> insertVertex(V element, E skills, Set<V> friends) {
		Vertex<V,E,D> v = new User<V,E,D>(element, skills,(D) new Double(Double.MAX_VALUE), friends);
		network.put(element, v);
		return v;
	}

	/**
	 * Returns the set of vertices listed as the friends of the given vertex.
	 * @param vertex the id of the vertex
	 * @return the set of friends of the given vertex
	 */
	public Set<V> getFriends(V vertex) {
		return network.get(vertex).getFriends();
	}
	
	/**
	 * Returns the Vertex identified by the specified vertex id
	 * @param v the vertex id
	 * @return the vertex identified by the id <b>v</b> or <b>null</b> if no such vertex exists
	 */
	public Vertex<V,E,D> getVertex(V v) {
		return network.get(v);
	}
	
	/**
	 * Set the distance of a vertex.
	 * @param v the vertex id
	 * @param d the distance
	 */
	public void setDistance(V v, D d) {
		Vertex<V,E,D> vertex = network.get(v);
		vertex.setDistance(d);
	}
	
	/**
	 * Returns the distance of a vertex
	 * @param v the vertex id
	 * @return the distance of the vertex
	 */
	public D getDistance(V v) {
		Vertex<V,E,D> vertex = network.get(v);
		return vertex.getDistance();
	}
	
	/**
	 * Set the predecessor of a vertex
	 * @param v the vertex id
	 * @param p the Vertex to be set as the predecessor
	 */
	public void setPredecessor(V v,Vertex<V,E,D> p) {
		Vertex<V,E,D> vertex = network.get(v);
		vertex.setPredecessor(p);
	}
	
	/**
	 * Returns the predecessor of a vertex
	 * @param v the vertex id
	 * @return the predecessor of the specified vertex
	 */
	public Vertex<V,E,D> getPredecessor(V v) {
		Vertex<V,E,D> vertex = network.get(v);
		return vertex.getPredecessor();
	}
	
	/**
	 * Set the skills of a vertex.
	 * @param v the vertex id
	 * @param s the skills to be associate with the vertex
	 */
	public void setSkills(V v, E s) {
		Vertex<V,E,D> vertex = network.get(v);
		vertex.setSkills(s);
	}
	
	/**
	 * Returns the skills of a vertex.
	 * @param v the vertex id
	 * @return the skills associated with the vertex
	 */
	public E getSkills(V v) {
		Vertex<V,E,D> vertex = network.get(v);
		return vertex.getSkills();
	}
	
	/**
	 * Utility function to print all the vertices in the graph.
	 */
	public void printGraph() {
		for(Vertex<V,E,D> v : network.values()) {
			System.out.println(v.toString());
		}
	}
	
	/**
	 * Utility function to print a vertex
	 * @param vertex the vertex id
	 */
	public void printVertex(V vertex) {
		Vertex<V,E,D> v = network.get(vertex);
		System.out.println(v.toString());
	}
	
	/**
	 * Get all vertices in the graph
	 * @return a collection of all the vertices in the graph
	 */
	public Collection<Vertex<V, E, D>> getAllVertices() {
		return network.values();
	}
	
	/**
	 * Remove a vertex
	 * @param v the vertex id
	 */
	public void remove(V e) {
		network.remove(e);
	}
}
