package sk.sochuliak.barabasi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sk.sochuliak.barabasi.graph.Graph;
import sk.sochuliak.barabasi.graph.GraphConfiguration;
import sk.sochuliak.barabasi.network.Network;
import sk.sochuliak.barabasi.network.NetworkAnalyse;
import sk.sochuliak.barabasi.network.NetworkUtils;

public class Barabasi {

	public static final int ITERATIONS = 1;
	public static final int NODES = 500;
	public static final int EDGES = 2;
	
	private Map<Integer, Double> averagedDegreeDistribution;
	
	public static void main(String[] args) {
		long startTime = new Date().getTime();
		Barabasi barabasi = new Barabasi(Barabasi.ITERATIONS, Barabasi.NODES, Barabasi.EDGES);
		barabasi.showDegreeDistrubutionsGraph();
		long endTime = new Date().getTime();
		long time = endTime - startTime;
		System.out.println("It tooks " + time + " ms.");
	}
	
	public Barabasi(int iterations, int nodes, int edges) {
		Map<Integer, Map<Integer, Double>> degreeDistributions = new HashMap<Integer, Map<Integer, Double>>();
		
		for (int i = 0; i < iterations; i++) {
			Network network = Network.buildNetwork(edges, nodes);
			NetworkAnalyse na = new NetworkAnalyse(network);
			degreeDistributions.put(i, na.getStandardizedDegreeDistribution());
//			System.out.println(na.getStandardizedDegreeDistribution());
		}
		this.averagedDegreeDistribution = NetworkUtils.getAveragedDegreeDistribution(degreeDistributions);
//		System.out.println(this.averagedDegreeDistribution);
	}
	
	public void showDegreeDistrubutionsGraph() {
		Set<Integer> degrees = this.averagedDegreeDistribution.keySet();
		List<Integer> degreesList = new ArrayList<Integer>(degrees);
		Collections.sort(degreesList);
		
		List<double[]> points = new ArrayList<double[]>();
		for (int i = 0; i < degreesList.size(); i++) {
			double x = (double) degreesList.get(i);
			double y = this.averagedDegreeDistribution.get(degreesList.get(i));
			double logx = (x == 0) ? 0 : Math.log(x);
			double logy = (y == 0) ? 0 : Math.log(y);
			points.add(new double[]{logx, logy});
		}
		
		Map<String, List<double[]>> data = new HashMap<String, List<double[]>>();
		data.put("Distribucia stupna uzlov", points);
		
		GraphConfiguration config = GraphConfiguration.createConfiguration()
			.setTitle("Distribucia stupna uzlov")
			.setxAxisLabel("Stupen vrchola")
			.setyAxisLabel("Normovany #uzlov stupna k")
			.setData(data);
		Graph.showGraph(config);
	}

}
