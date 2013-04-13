package sk.sochuliak.barabasi.network;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NetworkUtils {

	public static Map<Integer, Double> getAveragedDegreeDistribution(
			Map<Integer, Map<Integer, Double>> degreeDistributions) {
		Map<Integer, Double> result = new HashMap<Integer, Double>();
		
		int lowestDegreeOfNode = NetworkUtils.getLowestDegreeOfNode(degreeDistributions);
		int highestDegreeOfNode = NetworkUtils.getHighestDegreeOfNode(degreeDistributions);
		for (int i = lowestDegreeOfNode; i <= highestDegreeOfNode; i++) {
			int counter = 0;
			Double sumPk = 0d;
			Set<Integer> keySet = degreeDistributions.keySet();
			for (Integer key : keySet) {
				Double pk = degreeDistributions.get(key).get(i);
				if (pk != null && pk != 0.0) {
					sumPk += pk;
					counter++;
				}
			}
			double averagedPk = (counter == 0) ? 0 : (sumPk / (double) counter);
			if (counter != 0) {
				result.put(i, averagedPk);
			}
		}
		return result;
	}

	public static int getLowestDegreeOfNode(
			Map<Integer, Map<Integer, Double>> degreeDistributions) {
		int lowestDegree = Integer.MAX_VALUE;
		
		for (Integer key : degreeDistributions.keySet()) {
			Map<Integer, Double> degreeDistribution = degreeDistributions.get(key);
			int lowestDegreeLocal = Collections.min(degreeDistribution.keySet());
			if (lowestDegreeLocal < lowestDegree) {
				lowestDegree = lowestDegreeLocal;
			}
		}
		return lowestDegree != Integer.MAX_VALUE ? lowestDegree : 0;
	}

	public static int getHighestDegreeOfNode(
			Map<Integer, Map<Integer, Double>> degreeDistributions) {
		int highestDegree = 0;
		
		for (Integer key : degreeDistributions.keySet()) {
			Map<Integer, Double> degreeDistribution = degreeDistributions.get(key);
			int highestDegreeLocal = Collections.max(degreeDistribution.keySet());
			if (highestDegreeLocal > highestDegree) {
				highestDegree = highestDegreeLocal;
			}
		}
		return highestDegree;
	}

}
