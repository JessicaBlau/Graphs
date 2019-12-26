package dataStructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import elements.Node;


public class DGraph implements graph{
	private HashMap<Integer,node_data> listNodes = new HashMap<Integer, node_data>();
	private HashMap<Integer,edge_data> listEdgesSrc = new HashMap<Integer, edge_data>();
	private HashMap<Integer,edge_data> listEdgesDest = new HashMap<Integer, edge_data>();
	/**
	 * This constructor returns the node by the key that is received.
	 * @return the correct node if key is same and null if none match.
	 */
	@Override
	public node_data getNode(int key) {
		if(listNodes.containsKey(key)) {
			node_data ans = listNodes.get(key);
			return ans;
		}
		return null;
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		return null;
	}

	@Override
	public void addNode(node_data n) {
		listNodes.putIfAbsent(n.getKey(), n);
	}

	@Override
	public void connect(int src, int dest, double w) {
		
	}

	@Override
	public Collection<node_data> getV() {
		return listNodes.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		return null;
	}

	@Override
	public node_data removeNode(int key) {
		if(listNodes.containsKey(key)) {
			listNodes.remove(key);
			
			return null;
		}
		return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		if(listEdgesDest.containsKey(dest) && listEdgesSrc.containsKey(src)) {
			listEdgesDest.remove(dest);
			return listEdgesSrc.remove(src);
		}
		return null;
	}

	@Override
	public int nodeSize() {
		return listNodes.size();
	}

	@Override
	public int edgeSize() {
		int AllEdges = listEdgesSrc.size()+listEdgesDest.size();
		return AllEdges;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}

}

