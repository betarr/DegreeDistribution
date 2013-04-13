package sk.sochuliak.barabasi.network;

import java.util.ArrayList;
import java.util.List;

/**
 * Network class represents network as set of nodes and set of edges.
 * 
 * @author Betarr
 *
 */
public class Network {

	/**
	 * Nodes are represented by ids.
	 */
	private List<Long> nodesIds = new ArrayList<Long>();
	
	/**
	 * Edges are represented as pair of incidences nodes ids.
	 */
	private List<long[]> edges = new ArrayList<long[]>();
	
	public Network() {
	}
	
	public static Network buildNetwork(int edgesCount, long nodesCount) {
		Network network = new Network();
		
		for (long i = 0; i < nodesCount; i++) {
			List<Long> adjacentNodes = network.calculateAdjacentNodes(edgesCount);
			network.addNode(i);
			for (int j = 0; j < adjacentNodes.size(); j++) {
				network.addEdge(i, adjacentNodes.get(j));
			}
		}
		
		return network;
	}
	
	public void addNode(long nodeId) {
		if (this.nodesIds.contains(nodeId)) {
			System.err.println("Adding node: Node with id " + nodeId + " already exists.");
			return;
		}
		this.nodesIds.add(nodeId);			
	}
	
	public void addEdge(long nodeIdFrom, long nodeIdTo) {
		if (!this.nodesIds.contains(nodeIdFrom)) {
			System.err.println("Adding edge: Node with id " + nodeIdFrom + " does not exist");
			return;
		}
		if (!this.nodesIds.contains(nodeIdTo)) {
			System.err.println("Adding edge: Node with id " + nodeIdTo + " does not exist");
			return;
		}
		this.edges.add(new long[]{nodeIdFrom, nodeIdTo});
	}
	
	public List<Long> calculateAdjacentNodes(int edgesCount) {
		List<Long> adjacentNodes = new ArrayList<Long>();
		int nodesCount = this.nodesIds.size();
		
		if (nodesCount <= edgesCount) {
			for (Long nodeId : this.nodesIds) {
				adjacentNodes.add(nodeId);
			}
			return adjacentNodes;
		}
		
		while (adjacentNodes.size() != edgesCount) {
			double randomDouble = Math.random() * nodesCount;
			double iteration = (double) nodesCount / (double) (this.edges.size()*2);
			int areaCounter = 0;
			for (int i = 0; i < this.nodesIds.size(); i++) {
				long candidateNode = this.nodesIds.get(i);
				int adjacentNodesCount = this.getAdjacentNodesCount(candidateNode);
				double rangeFrom = areaCounter*iteration;
				double rangeTo = (areaCounter+adjacentNodesCount)*iteration;
				if (randomDouble >= rangeFrom && randomDouble < rangeTo) {
					if (!adjacentNodes.contains(candidateNode)) {
						adjacentNodes.add(candidateNode);
						break;
					}
				}
				areaCounter += adjacentNodesCount;
			}
		}
		
		return adjacentNodes;
	}
	
	public List<Long> getAdjacentNodesIds(long nodeId) {
		if (!this.nodesIds.contains(nodeId)) {
			System.err.println("Get nodes adjacent nodes ids: Node with id: " + nodeId + " does not exist");
			return null;
		}
		List<Long> adjacentNodes = new ArrayList<Long>();
		for (long[] edge : this.edges) {
			if (edge[0] == nodeId) {
				adjacentNodes.add(edge[1]);
			}
			if (edge[1] == nodeId) {
				adjacentNodes.add(edge[0]);
			}
		}
		return adjacentNodes;
	}
	
	public int getAdjacentNodesCount(long nodeId) {
		List<Long> adjacentNodesIds = this.getAdjacentNodesIds(nodeId);
		return adjacentNodesIds != null ? adjacentNodesIds.size() : 0;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getClass().getName()).append("\n");
		sb.append("Nodes: ").append(this.nodesIds).append("\n");
		sb.append("Edges: ").append("\n");
		for (int i = 0; i < this.edges.size(); i++) {
			long[] edge = this.edges.get(i);
			sb.append("\t").append("[ ").append(edge[0]).append(" - ").append(edge[1]).append(" ]\n");
		}
		return sb.toString();
	}

	public List<Long> getNodesIds() {
		return nodesIds;
	}

	public List<long[]> getEdges() {
		return edges;
	}
}
