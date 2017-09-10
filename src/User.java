import java.util.Set;

/**
 * This class implements the Vertex interface.
 * 
 * @author rabiachaudry
 * @version 1.0
 * 
 * @param <V> id - the user id
 * @param <E> skills - the user skills
 * @param <D> distance - the distance of user from a specific user
 */
public class User<V,E,D> implements Vertex<V,E,D>{

	private V id; 						// User Id 
	private E skills;					// Skill level
	private D distance;					// Distance from source
	private Vertex<V,E,D> predecessor;	// Predecessor of this vertex
	private Set<V> friends;				// Set of friends
	private boolean visited;			// Visited flag
	
	/**
	 * Constructor to initialize a user with id, skills and distance.
	 * @param id - the user id
	 * @param skill - the user skills level
	 * @param distance - the distance from a specific user
	 */
	public User(V id, E skill, D distance) {
		this.id = id;
		this.skills = skill;
		this.distance = distance;
		this.predecessor = null;
		this.visited = false;
		this.friends = null;
	}
	
	/**
	 * Constructor to initialize a user with id, skills, distance and friends.
	 * @param id - the user id
	 * @param skill - the user skills level
	 * @param distance - the distance from a specific user
	 * @param friends - the set of firends
	 */
	public User(V id, E skill, D distance, Set<V> friends) {
		this(id, skill, distance);
		this.friends = friends;
	}
	
	/**
	 * Returns the user id.
	 * @return the id associated with the user 
	 */
	public V getId() {
		return this.id;
	}
	
	/**
	 * Sets the user Id.
	 * @param id the user id
	 */
	public void setId(V id) {
		this.id = id;
	}

	/**
	 * Returns the skills associated with the user.
	 * @return the user skills.
	 */
	public E getSkills() {
		return this.skills;
	}
	
	/** 
	 * Set the skills associated with the user.
	 * @param skills the user skills
	 */
	public void setSkills(E skills) {
		this.skills = skills;
	}

	/**
	 * Returns the distance of the user from a specific user.
	 * @return the distance of the user
	 */
	public D getDistance() {
		return this.distance;
	}
	
	/**
	 * Sets the distance of the user from a specific user.
	 * @param distance the distance of the user 
	 */
	public void setDistance(D distance) {
		this.distance = distance;
	}
	
	/**
	 * Sets the predecessor of the user
	 * @param predecesor the user to be set as the predecessor of the current user
	 */
	public void setPredecessor(Vertex<V,E,D> predecessor) {
		this.predecessor = predecessor;
	}

	/**
	 * Returns the predecessor of the user.
	 * @return the predecessor
	 */
	public Vertex<V,E,D> getPredecessor() {
		return this.predecessor;
	}
	
	/**
	 * Returns the friends of the user as a set.
	 * @return A set containing the friends of the user
	 */
	public Set<V> getFriends() {
		return this.friends;
	}
	
	/**
	 * Sets the friends of the user.
	 * @param friends a set containing the ids of the user's friends
	 */
	public void setFriends(Set<V> friends) {
		this.friends = friends;
	}

	/**
	 * Returns a boolean value representing the status of the visited flag.
	 * @return <b>true</b> if the user has been visited else <b>false</b>
	 */
	public boolean isVisited() {
		return this.visited;
	}

	/**
	 * Sets the status of the visited flag to a boolean value
	 * @param visited a boolean value (<b>true</b> or <b>false</b>)
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	
	@Override
	public String toString() {
		return "User [id=" + id + ", skills=" + skills + ", distance=" + distance + ", predecessor=" + (predecessor!=null ? predecessor.getId() : predecessor)
				+ ", friends=" + friends + ", visited=" + visited + "]";
	}

	/**
	 * Returns a hash code value for the object. 
	 * @return hash code value for the object as an <b>int</b>
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((distance == null) ? 0 : distance.hashCode());
		result = prime * result + ((friends == null) ? 0 : friends.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((predecessor == null) ? 0 : predecessor.hashCode());
		result = prime * result + ((skills == null) ? 0 : skills.hashCode());
		result = prime * result + (visited ? 1231 : 1237);
		return result;
	}
	
	/**
	 * Indicates whether some other object is "equal to" this one.
	 * @param obj the object passed for comparison
	 * @return a boolean value (<b>true</b> or <b>false</b>) indicating whether the two objects are equal or not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		User<V,E,D> other = (User<V,E,D>) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
}
