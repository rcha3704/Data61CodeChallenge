/**
 * Entry class to define entries held in the Priority Queue.
 * Contains only id and distance of node
 * 
 * @author rabiachaudry
 * @version 1.0
 * 
 * @param <V> id
 * @param <E> distance
 */
public class Entry<V,E> implements Comparable<Entry<V,E>>{
	
	private V id;
	private E distance;
	
	/**
	 * Constructor: initializes the Entry object with the supplied id and distance 
	 * @param id
	 * @param distance
	 */
	public Entry(V id, E distance) {
		this.id = id;
		this.distance = distance;
	}
	
	/**
	 * Get the Id of the Entry object
	 * @return the Entry Id
	 */
	public V getId() {
		return id;
	}
	
	/**
	 * Set the Entry id
	 * @param id
	 */
	public void setId(V id) {
		this.id = id;
	}
	
	/**
	 * Get the distance
	 * @return the current distance 
	 */
	public E getDistance() {
		return distance;
	}
	
	/**
	 * Set the distance 
	 * @param distance
	 */
	public void setDistance(E distance) {
		this.distance = distance;
	}
	
	/**
	 * Compare Entry objects 
	 * @return 0 if the objects are equal, 
	 * 1 if the first object is greater than the second and 
	 * -1 if the first object is smaller than the second 
	 */
	@Override
	public int compareTo(Entry<V, E> v) {
		if ((double) this.getDistance() > (double) v.getDistance())
			return 1;
		if ((double) this.getDistance() < (double) v.getDistance())
			return -1;
		return 0;
	}
	
	/**
	 * Overriding the Object toString() method for Entry
	 * @return returns the string representation of the Entry object
	 */
	@Override
	public String toString() {
		return "Entry [id=" + id + ", distance=" + distance + "]";
	}

}
