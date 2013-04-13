package sk.sochuliak.barabasi.network;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkAnalyse {

	private Network network;

	public NetworkAnalyse(Network network) {
		this.network = network;
	};
	
	public Map<Integer, List<Long>> getDegreeDistribution() {
		Map<Integer, List<Long>> result = new HashMap<Integer, List<Long>>();
		
		List<Long> nodes = this.network.getNodesIds();
		
		for (Long nodeId : nodes) {
			int adjacentNodeCount = this.network.getAdjacentNodesCount(nodeId);
			if (result.get(adjacentNodeCount) == null) {
				result.put(adjacentNodeCount, new ArrayList<Long>());
			}
			result.get(adjacentNodeCount).add(nodeId);
		}
		
		return result;
	}
	
	public Map<Integer, Double> getStandardizedDegreeDistribution() {
		Map<Integer, List<Long>> rozdelenieStupnaUzlov = this.getDegreeDistribution();
		
		double nodesCount = (double) this.network.getNodesIds().size();
		
		Map<Integer, Double> result = new HashMap<Integer, Double>();
		for (Integer nodeDegree : rozdelenieStupnaUzlov.keySet()) {
			result.put(nodeDegree, (double) rozdelenieStupnaUzlov.get(nodeDegree).size() / nodesCount);
		}
		return result;
	}
	
	public Map<Integer, Double> getFullStandardizedDegreeDistribution() {
		Map<Integer, Double> degreeDistribution = this.getStandardizedDegreeDistribution();
		
		Map<Integer, Double> result = new HashMap<Integer, Double>();
		int maxDegree = Collections.max(degreeDistribution.keySet());
		int minDegree = Collections.min(degreeDistribution.keySet());
		for (int i = minDegree; i <= maxDegree; i++) {
			Double degree = degreeDistribution.get(i);
			result.put(i, degree==null ? 0 : degree);
		}
		
		return result;
	}
}
