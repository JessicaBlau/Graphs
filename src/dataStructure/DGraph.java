package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import elements.Edge;
import elements.Node;


public class DGraph implements graph{
	private static int counterChanges = 0;
	private static int counterEdges = 0;
	private HashMap<Integer,node_data> listNodes = new HashMap<Integer, node_data>();
	/**
	 * This constructor returns the node by the key that is received.
	 * @return the correct node if key is same and null if none match.
	 */
	@Override
	public node_data getNode(int key) {
		if(listNodes.containsKey(key)) {
			return listNodes.get(key);
		}
		return null;
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		Node temp = (Node) listNodes.get(src);
		if(listNodes.containsKey(src)&&listNodes.containsKey(dest)
				&&temp.getAllEdges().containsKey(dest)) {
			return temp.getAllEdges().get(dest);
		}
		return null;
	}

	@Override
	public void addNode(node_data n) {
		counterChanges++;
		listNodes.putIfAbsent(n.getKey(), n);
	}

	@Override
	public void connect(int src, int dest, double w) {
		if(listNodes.containsKey(src)&&listNodes.containsKey(dest)) {
			Node tempsrc = (Node) listNodes.get(src);
			Node tempdest = (Node) listNodes.get(dest);
			if(!tempsrc.getAllEdges().containsKey(dest)) {
				Edge temp = new Edge(src,dest,w);
				tempdest.AddThisEdge(temp);
				counterChanges++;
				counterEdges++;
			}
		}
		else System.out.println("This source or destination is invalid");
	}

	@Override
	public Collection<node_data> getV() {
		return listNodes.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		Collection<edge_data> EdgesOfThisNode = new ArrayList<>();
		if(listNodes.containsKey(node_id)) {
			Node temp = (Node) listNodes.get(node_id);
			EdgesOfThisNode.addAll(temp.getAllEdges().values());
			return EdgesOfThisNode;
		}
		return null;
	}

	@Override
	public node_data removeNode(int key) {
		if(listNodes.containsKey(key)) {
			Node temp = (Node) listNodes.get(key);
			temp.getAllEdges().remove(key);
			counterChanges++;
			return listNodes.remove(key);
		}
		return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		Node temp = (Node) listNodes.get(src);
		if(listNodes.containsKey(dest) && listNodes.containsKey(src)
				&&temp.getAllEdges().containsKey(dest)) {
			counterChanges++;
			return temp.getAllEdges().remove(dest);
		}
		return null;
	}

	@Override
	public int nodeSize() {
		return listNodes.size();
	}

	@Override
	public int edgeSize() {
		
		return counterEdges;
	}

	@Override
	public int getMC() {
		return counterChanges;
	}

}

