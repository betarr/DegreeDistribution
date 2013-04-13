package sk.sochuliak.barabasi.network;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class NetworkUtilsTest {
	
	private Map<Integer, Map<Integer, Double>> degreeDistributions;
	
	@Before
	public void setUp() {
		Map<Integer, Double> degreeDistribution1 = new HashMap<Integer, Double>();
		degreeDistribution1.put(2, 4.6);
		degreeDistribution1.put(3, 2.3);
		degreeDistribution1.put(4, 3.3);
		
		Map<Integer, Double> degreeDistribution2 = new HashMap<Integer, Double>();
		degreeDistribution2.put(2, 5.2);
		degreeDistribution2.put(3, 1.9);
		
		Map<Integer, Double> degreeDistribution3 = new HashMap<Integer, Double>();
		degreeDistribution3.put(2, 1.2);
		degreeDistribution3.put(3, 2.4);
		degreeDistribution3.put(4, 3.56);
		degreeDistribution3.put(5, 5.12);
		
		this.degreeDistributions = new HashMap<Integer, Map<Integer, Double>>();
		this.degreeDistributions.put(1, degreeDistribution1);
		this.degreeDistributions.put(2, degreeDistribution2);
		this.degreeDistributions.put(3, degreeDistribution3);
	}

	@Test
	@Ignore
	public void testGetAveragedDegreeDistribution() {
		Map<Integer, Double> expectedAveragedDegreeDistribution = new HashMap<Integer, Double>();
		expectedAveragedDegreeDistribution.put(2, 3.66);
		expectedAveragedDegreeDistribution.put(3, 2.2);
		expectedAveragedDegreeDistribution.put(4, 3.43);
		expectedAveragedDegreeDistribution.put(5, 5.12);
		
		assertEquals(
				expectedAveragedDegreeDistribution,
				NetworkUtils
						.getAveragedDegreeDistribution(this.degreeDistributions)
		);
	}
	
	@Test
	public void testGetLowestDegreeOfNode() {
		int expectedMin = 2;
		int actualMin = NetworkUtils.getLowestDegreeOfNode(this.degreeDistributions);
		assertEquals(expectedMin, actualMin);
	}
	
	@Test
	public void testGetHighestDegreeOfNode() {
		int expectedMax = 5;
		int actualMax = NetworkUtils.getHighestDegreeOfNode(this.degreeDistributions);
		assertEquals(expectedMax, actualMax);
	}
}
