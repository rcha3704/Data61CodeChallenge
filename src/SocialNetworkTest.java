import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

public class SocialNetworkTest {

	/* Tests with dummy dataset */
	
	@Test
	public void testLoadsDataSuccessfully() {
		SocialNetwork network = new SocialNetwork();
		boolean res = network.loadData("sample.json");
		assertTrue(res);
	}
	
	@Test
	public void testReturnsCorrectNumberOfUsers() {
		SocialNetwork network = new SocialNetwork();
		network.loadData("sample.json");
		assertEquals(network.numUsers(), new Long(network.getMap().numVertices()));
	}
	
	@Test
	public void testPathWithNullSource() {
		SocialNetwork network = new SocialNetwork();
		network.loadData("sample.json");
		User<Long,Double,Double> d = network.getUser(new Long(1));
		assertEquals(network.pathFrom(null, d),null);
	}
	
	@Test
	public void testPathWithNullDestination() {
		SocialNetwork network = new SocialNetwork();
		network.loadData("sample.json");
		User<Long,Double,Double> s = network.getUser(new Long(1));
		assertEquals(network.pathFrom(s, null),null);
	}
	
	@Test
	public void testPathWithSourceDestinationBothNull() {
		SocialNetwork network = new SocialNetwork();
		network.loadData("sample.json");
		assertEquals(network.pathFrom(null, null),null);
	}

	
	@Test
	public void testDistanceIsUpdated() {
		SocialNetwork network = new SocialNetwork();
		network.loadData("sample.json");
		
		Vertex<Long,Double,Double> s = network.getUser(new Long(1));
		
		s.setDistance(new Double(0));
		
		assertEquals(s.getDistance(),new Double(0));
		
		Vertex<Long,Double,Double> d = network.getUser(new Long(4));
		
		assertEquals(d.getDistance(), new Double(Double.MAX_VALUE));
	}
	
	@Test
	public void testFriendsDistancesAreUpdated() {
		SocialNetwork network = new SocialNetwork();
		network.loadData("sample.json");
		
		User<Long,Double,Double> s = network.getUser(new Long(1));
		s.setDistance(new Double(0));
		
		network.updateFriends(new Entry<Long,Double>(s.getId(),s.getDistance()));
		
		assertEquals(s.getDistance(),new Double(0));
		
		for (Long f : s.getFriends()) {
			User<Long,Double,Double> friend = network.getUser(f);
			assertEquals(friend.getDistance(), new Double(s.getDistance()+friend.getSkills()));
		}
	}
	
	@Test
	public void testPredecessorIsUpdated() {
		SocialNetwork network = new SocialNetwork();
		network.loadData("sample.json");
		
		User<Long,Double,Double> s = network.getUser(new Long(1));
		
		// set s distance to 0
		s.setDistance(new Double(0));

		// update friends of s
		network.updateFriends(new Entry<Long,Double>(s.getId(),s.getDistance()));
		
		// update distance for each friend from s
		for (Long f : s.getFriends()) {
			User<Long,Double,Double> friend = network.getUser(f);
			// check that the predecessor of the friends is set to s
			assertEquals(friend.getPredecessor(), s);
		}
		
		// check that the predecessor for friend is not yet set
		User<Long,Double,Double> v = network.getUser(new Long(4));
		assertNotEquals(v.getPredecessor(),network.getUser(new Long(3)));
		
		// update the friends 
		User<Long,Double,Double> t = network.getUser(new Long(3));
		network.updateFriends(new Entry<Long,Double>(t.getId(),t.getDistance()));
		
		// check that the predecessor is set to null
		assertEquals(v.getPredecessor(),null);
		
		// update friends of vertex with id: 2 
		t = network.getUser(new Long(2));
		network.updateFriends(new Entry<Long,Double>(t.getId(),t.getDistance()));
		
		// ensure that the predecessor of 4 is now set to 2
		assertEquals(v.getPredecessor(),network.getUser(new Long(2)));
	}
	
	
	@Test
	public void testfindShortestPathBetween1and4() {
		SocialNetwork network = new SocialNetwork();
		network.loadData("sample.json");
		
		User<Long,Double,Double> s = network.getUser(new Long(1));
		User<Long,Double,Double> d = network.getUser(new Long(4));
		
		ArrayList<Long> path = (ArrayList<Long>) network.findShortestPathBetween(s, d);
		
		assertEquals(path, new ArrayList<Long>(Arrays.asList(new Long(1),new Long(2),new Long(4))));
		
	}
	
	@Test
	public void testfindShortestPathToItself() {
		SocialNetwork network = new SocialNetwork();
		network.loadData("sample.json");
		
		User<Long,Double,Double> s = network.getUser(new Long(4));
		User<Long,Double,Double> d = network.getUser(new Long(4));
		
		ArrayList<Long> path = (ArrayList<Long>) network.findShortestPathBetween(s, d);
		
		assertEquals(path, null);
	}
	
	@Test
	public void testfindShortesPathBetween5and8() {
		SocialNetwork network = new SocialNetwork();
		network.loadData("sample.json");
		
		User<Long,Double,Double> s = network.getUser(new Long(5));
		User<Long,Double,Double> d = network.getUser(new Long(8));
		
		ArrayList<Long> path = (ArrayList<Long>) network.findShortestPathBetween(s, d);
		
		assertEquals(path, new ArrayList<Long>(Arrays.asList(new Long(5),new Long(7),new Long(8))));	
	
	}	
	
	@Test
	public void testFindShortestPathBetween13and16() {
		SocialNetwork network = new SocialNetwork();
		network.loadData("sample.json");
		
		User<Long,Double,Double> s = network.getUser(new Long(13));
		User<Long,Double,Double> d = network.getUser(new Long(16));
		
		ArrayList<Long> path = (ArrayList<Long>) network.findShortestPathBetween(s, d);
		
		assertEquals(path, new ArrayList<Long>(Arrays.asList(new Long(13),new Long(14),new Long(16))));	
	}
	
	/* Tests with full dataset */
	
	@Test
	public void testfindShortestPathBetween4and772233() {
		SocialNetwork network = new SocialNetwork();
		network.loadData("task.json");
		
		User<Long,Double,Double> s = network.getUser(new Long(4));
		User<Long,Double,Double> d = network.getUser(new Long(772233));
		
		ArrayList<Long> path = (ArrayList<Long>) network.findShortestPathBetween(s, d);

		assertEquals(path, new ArrayList<Long>(Arrays.asList(new Long(4),new Long(772233))));
		
	}

	@Test
	public void testfindShortestPathBetween4and3306() {
		SocialNetwork network = new SocialNetwork();
		network.loadData("task.json");
		
		User<Long,Double,Double> s = network.getUser(new Long(4));
		User<Long,Double,Double> d = network.getUser(new Long(3306));
		
		ArrayList<Long> path = (ArrayList<Long>) network.findShortestPathBetween(s, d);

		assertEquals(path, new ArrayList<Long>(Arrays.asList(new Long(4),new Long(772233),new Long(3306))));
		
	}	
	
	@Test
	public void testfindShortestPathBetween17and11270() {
		SocialNetwork network = new SocialNetwork();
		network.loadData("task.json");
		
		User<Long,Double,Double> s = network.getUser(new Long(17));
		User<Long,Double,Double> d = network.getUser(new Long(11270));
		
		ArrayList<Long> path = (ArrayList<Long>) network.findShortestPathBetween(s, d);
		
		assertEquals(path, new ArrayList<Long>(Arrays.asList(new Long(17),new Long(996936),new Long(62202),new Long(11270))));	
	}
	
	@Test
	public void testShortestPathDoesNotExistBetween1and2() {
		SocialNetwork network = new SocialNetwork();
		network.loadData("task.json");
		
		User<Long,Double,Double> s = network.getUser(new Long(1));
		User<Long,Double,Double> d = network.getUser(new Long(2));
		
		ArrayList<Long> path = (ArrayList<Long>) network.findShortestPathBetween(s, d);
		
		assertEquals(path, null);	
	}
	
	@Test
	public void testFindShortestPathBetween17and66741() {
		SocialNetwork network = new SocialNetwork();
		network.loadData("task.json");
		
		User<Long,Double,Double> s = network.getUser(new Long(17));
		User<Long,Double,Double> d = network.getUser(new Long(66741));
		
		ArrayList<Long> path = (ArrayList<Long>) network.findShortestPathBetween(s, d);
		
		assertEquals(path, new ArrayList<Long>(Arrays.asList(new Long(17),new Long(333621),new Long(66741))));	
	}
}
