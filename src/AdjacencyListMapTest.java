import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class AdjacencyListMapTest {

	@Test
	public void testInsertVertexAndNumVertices() {
		Graph<Long,Double,Double> graph = new AdjacencyListMap<Long,Double,Double>();
		graph.insertVertex(new Long(1), new Double(0.0), new HashSet<Long>());
		assertEquals(graph.numVertices(), 1);
		graph.insertVertex(new Long(2), new Double(0.0), new HashSet<Long>());
		assertEquals(graph.numVertices(), 2);
	}
	
	@Test
	public void testGetFriends() {
		Graph<Long,Double,Double> graph = new AdjacencyListMap<Long,Double,Double>();
		Set<Long> hs = new HashSet<Long>();
		hs.add(new Long(2));
		hs.add(new Long(3));
		graph.insertVertex(new Long(1), new Double(0.0),hs);
		assertEquals(graph.getFriends(new Long(1)),hs);
		graph.insertVertex(new Long(2), new Double(0.0),null);
		assertNull(graph.getFriends(new Long(2)));
		hs = new HashSet<Long>();
		hs.add(new Long(1));
		graph.insertVertex(new Long(3), new Double(0.0),hs);
		assertEquals(graph.getFriends(new Long(3)),hs);
	}
	
	@Test
	public void testGetVertex() {
		Graph<Long,Double,Double> graph = new AdjacencyListMap<Long,Double,Double>();
		assertNull(graph.getVertex(new Long(1)));
		graph.insertVertex(new Long(1), new Double(0.0),null);
		assertEquals(graph.getVertex(new Long(1)).getId(), new Long(1));
		graph.insertVertex(new Long(2), new Double(0.0),null);
		assertEquals(graph.getVertex(new Long(2)).getId(), new Long(2));
	}

	@Test
	public void testDistance() {
		Graph<Long,Double,Double> graph = new AdjacencyListMap<Long,Double,Double>();
		graph.insertVertex(new Long(1), new Double(0.0),null);
		User<Long,Double,Double> f = (User<Long, Double, Double>) graph.getVertex(new Long(1)); 
		assertEquals(f.getDistance(), new Double(Double.MAX_VALUE));
		f.setDistance(new Double(1.25));
		assertEquals(f.getDistance(), new Double(1.25));
		f.setDistance(null);
		assertNull(f.getDistance());
	}

	@Test
	public void testPredecessor() {
		Graph<Long,Double,Double> graph = new AdjacencyListMap<Long,Double,Double>();
		graph.insertVertex(new Long(1),new Double(1.0), null);
		assertNull(graph.getVertex(new Long(1)).getPredecessor());
		graph.insertVertex(new Long(2),new Double(0.5), null);
		graph.getVertex(new Long(2)).setPredecessor(graph.getVertex(new Long(1)));
		assertEquals(graph.getVertex(new Long(2)).getPredecessor(),graph.getVertex(new Long(1)));
		graph.insertVertex(new Long(3),new Double(0.25), null);
		graph.getVertex(new Long(2)).setPredecessor(graph.getVertex(new Long(3)));
		assertEquals(graph.getVertex(new Long(2)).getPredecessor(),graph.getVertex(new Long(3)));
	}
	

	@Test
	public void testSkills() {
		Graph<Long,Double,Double> graph = new AdjacencyListMap<Long,Double,Double>();
		graph.insertVertex(new Long(1),new Double(0.0), null);
		graph.insertVertex(new Long(2),new Double(0.5), null);
		graph.insertVertex(new Long(3),new Double(0.33), null);
		graph.insertVertex(new Long(4),new Double(0.25), null);
		
		assertEquals(graph.getVertex(new Long(1)).getSkills(), new Double(0.0));
		assertEquals(graph.getVertex(new Long(2)).getSkills(), new Double(0.5));
		assertEquals(graph.getVertex(new Long(3)).getSkills(), new Double(0.33));
		assertEquals(graph.getVertex(new Long(4)).getSkills(), new Double(0.25));
		
		graph.getVertex(new Long(1)).setSkills(1.1);
		graph.getVertex(new Long(2)).setSkills(1.2);
		graph.getVertex(new Long(3)).setSkills(1.3);
		graph.getVertex(new Long(4)).setSkills(1.4);
		
		assertEquals(graph.getVertex(new Long(1)).getSkills(), new Double(1.1));
		assertEquals(graph.getVertex(new Long(2)).getSkills(), new Double(1.2));
		assertEquals(graph.getVertex(new Long(3)).getSkills(), new Double(1.3));
		assertEquals(graph.getVertex(new Long(4)).getSkills(), new Double(1.4));
		
	}

	@Test
	public void testGetAllVertices() {
		Graph<Long,Double,Double> graph = new AdjacencyListMap<Long,Double,Double>();
		graph.insertVertex(new Long(1),new Double(0.0), null);
		graph.insertVertex(new Long(2),new Double(0.5), null);
		graph.insertVertex(new Long(3),new Double(0.33), null);
		graph.insertVertex(new Long(4),new Double(0.25), null);
		
		assertEquals(graph.getAllVertices().size(),4);
		
		int id = 1;
		for ( Vertex<Long,Double,Double> v : graph.getAllVertices()) {
			assertEquals(v.getId(), new Long(id++));
		}
		
		graph.remove(new Long(3));
		
		assertEquals(graph.getAllVertices().size(),3);
		assertNull(graph.getVertex(new Long(3)));
		
		assertEquals(graph.getVertex(new Long(1)).getId(),new Long(1));
		assertEquals(graph.getVertex(new Long(2)).getId(),new Long(2));
		assertEquals(graph.getVertex(new Long(4)).getId(),new Long(4));
		
		graph.remove(new Long(1));
		graph.remove(new Long(2));
		graph.remove(new Long(4));
		
		assertEquals(graph.getAllVertices().size(),0);
	}

}
