package sk.sochuliak.barabasi.network;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class NetworkTest {
	
	private Network network;
	
	@Before
	public void before() {
		this.network = new Network();
		this.network.addNode(1);
		this.network.addNode(2);
		this.network.addNode(3);
		this.network.addNode(4);
		
		this.network.addEdge(1, 2);
		this.network.addEdge(1, 3);
		this.network.addEdge(2, 3);
		this.network.addEdge(3, 4);
	}
	
	@Test
	public void testGetAdjacentNodesIds() {
		assertEquals(Arrays.asList(new Long[]{2L, 3L}), this.network.getAdjacentNodesIds(1));
		assertEquals(Arrays.asList(new Long[]{1L, 3L}), this.network.getAdjacentNodesIds(2));
		assertEquals(Arrays.asList(new Long[]{1L, 2L, 4L}), this.network.getAdjacentNodesIds(3));
		assertEquals(Arrays.asList(new Long[]{3L}), this.network.getAdjacentNodesIds(4));
	}
	
	@Test
	public void testGetAdjacentNodesCount() {
		assertEquals(2, this.network.getAdjacentNodesCount(1));
		assertEquals(2, this.network.getAdjacentNodesCount(2));
		assertEquals(3, this.network.getAdjacentNodesCount(3));
		assertEquals(1, this.network.getAdjacentNodesCount(4));
	}

}
