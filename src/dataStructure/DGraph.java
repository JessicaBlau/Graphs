package dataStructure;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import elements.Edge;
import elements.Node;


public class DGraph implements graph{
	public int counterChanges = 0;
	public int counterEdges = 0;
	private HashMap<Integer,node_data> listNodes = new HashMap<Integer, node_data>();// nodes and there keys.
	/**
	 * 
	 */
	public DGraph() {
		HashMap<Integer, node_data> graph = new HashMap<Integer, node_data>();
		listNodes = graph;
	}
	/**
	 * 
	 * @return
	 */
	public HashMap<Integer, node_data> getGraph(){
		return listNodes;
	}
	/**
	 * 
	 * @param graph
	 */
	public void setGraph(HashMap<Integer, node_data> graph) {
		listNodes = graph;
		for(Map.Entry<Integer, node_data> entry : listNodes.entrySet()) {
		   Node temp = (Node) entry.getValue();
		   counterEdges += temp.getNumberOfEdges();
		}
	}
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
	/**
	 * 
	 */
	@Override
	public edge_data getEdge(int src, int dest) {
		Node temp = (Node) listNodes.get(src);
		if(listNodes.containsKey(src)&&listNodes.containsKey(dest)) {
			return temp.getAllEdges().get(dest);
		}
		return null;
	}
	/**
	 * 
	 */
	@Override
	public void addNode(node_data n) {
		Node temp = (Node) n;
		if(!listNodes.containsValue(n)) {
			listNodes.putIfAbsent(n.getKey(), n);
			counterEdges += temp.getNumberOfEdges();
			counterChanges++;
		}
		else {
			System.out.println("Node is in this graph already.");
		}
	}
	/**
	 * 
	 */
	@Override
	public void connect(int src, int dest, double w) {
		if(listNodes.containsKey(src)&&listNodes.containsKey(dest)) {
			Node tempsrc = (Node) listNodes.get(src);
			if(!tempsrc.getAllEdges().containsKey(dest)) {
				Edge temp = new Edge(src,dest,w);
				tempsrc.AddThisEdge(temp);
				counterChanges++;
			}
		}
		else System.out.println("This source or destination is invalid");
	}
	/**
	 * 
	 */
	@Override
	public Collection<node_data> getV() {
		return listNodes.values();
	}
	/**
	 * 
	 */
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
	/**
	 * 
	 */
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
	/**
	 * 
	 */
	@Override
	public edge_data removeEdge(int src, int dest) {
		Node temp = (Node) listNodes.get(src);
		if(listNodes.containsKey(dest) && listNodes.containsKey(src)
				&&temp.getAllEdges().containsKey(dest)) {
			counterChanges++;
			counterEdges--;
			return temp.getAllEdges().remove(dest);
		}
		return null;
	}
	/**
	 * 
	 */
	@Override
	public int nodeSize() {
		return listNodes.size();
	}
	/**
	 * 
	 */
	@Override
	public int edgeSize() {
		return counterEdges;
	}
	/**
	 * 
	 */
	@Override
	public int getMC() {
		return counterChanges;
	}
	/**
	 * Function that reverses the graph.
	 */
	public void Transpose() {
		noTagEdges();
		for (node_data node : getV()) {
			for (edge_data edge : getE(node.getKey())) {
				if(isTwoWayGraph(edge.getSrc(), edge.getDest())) {
					Edge src = (Edge) getEdge(edge.getSrc(), edge.getDest());
					Node temp = (Node) getNode(edge.getDest());
					Edge dest = (Edge) temp.getAllEdges().get(edge.getSrc());
					if(src.getTag() == 0 && dest.getTag() == 0) {
						double _temp = src.getWeight();
						src.setWeight(dest.getWeight());
						dest.setWeight(_temp);
						src.setTag(1);;
					}
				}
				else {
					if(edge.getTag() == 0) {
						connect(edge.getDest(),edge.getSrc(),edge.getWeight());
						edge.setTag(1);
						removeEdge(edge.getSrc(), edge.getDest());
					}
				}
			}
		}
	}
	/**
	 * Function that resets all the edges tags of this graph.
	 */
	private void noTagEdges() {
		for (Iterator<node_data> node = getV().iterator(); node.hasNext();) {
			for (edge_data edge : getE(node.next().getKey())) {
				edge.setTag(0);
			}
		}
	}
	/**
	 * Function that can determine if this graph is a two-way graph or not.
	 * @param src - The source of this edge.
	 * @param dest - the destination of this edge.
	 * @return true or false.
	 */
	private boolean isTwoWayGraph(int src, int dest) {
		Node temp = (Node) getNode(dest);
		if(temp.getAllEdges().containsKey(src)) {
			return true;
		}
		return false;
	}
}

