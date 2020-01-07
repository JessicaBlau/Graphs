package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;


import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import elements.Node;
/**
 * This class is that algorithm class that are implied onto a graph. 
 * Within this class there are many functions that can help define a graph for example,
 * to find the shortest path getting from one node to another.
 * @author Jessica Blau
 *
 */
public class Graph_Algo implements graph_algorithms{
	public graph g;
	/**
	 *Default constructor for this graph.
	 */
	public Graph_Algo() {
		g = new DGraph();
	}
	/**
	 * Constructor that receives a graph and enters it to this graph.
	 * @param _graph - the graph that is received.
	 */
	public Graph_Algo(graph _graph) {
		g = _graph;
	}
	/**
	 * A function that receives a graph and enters it to this graph.
	 */
	@Override
	public void init(graph g) {
		this.g = g;
	}
	/**
	 * Function that receives file.
	 */
	@Override
	public void init(String file_name) {
		try
		{    
			FileInputStream file = new FileInputStream(file_name); 
			ObjectInputStream in = new ObjectInputStream(file); 

			g = (DGraph)in.readObject(); 

			in.close(); 
			file.close(); 
		} 

		catch(IOException | ClassNotFoundException ex) 
		{ 
			throw new RuntimeException("ClassNotFoundException is caught or IOException is caught");
		} 
	}
	/**
	 * Function that saves file.
	 */
	@Override
	public void save(String file_name) {
		try
		{    
			FileOutputStream file = new FileOutputStream(file_name); 
			ObjectOutputStream out = new ObjectOutputStream(file); 

			out.writeObject(g); 

			out.close(); 
			file.close(); 
		} 

		catch(IOException ex) 
		{ 
			System.out.println("IOException is caught"); 
		} 

	}
	/**
	 * A function that return true or false if this graph is connected or not. 
	 * Checks that you can reach each node in this graph.
	 */
	@Override
	public boolean isConnected() {
		if(g.nodeSize() <= 1) {
			return true;
		}
		Queue<Node> qu = new ArrayBlockingQueue<Node>(g.nodeSize());
		ZeroTags();;
		for (node_data nodes : g.getV() ) {
			Node node = (Node) nodes;
			if (node.getAllEdges().values() == null) {
				return false;
			}
			qu.add(node);
			node.setTag(1);
			while (!qu.isEmpty()) {
				for (edge_data edge : qu.peek().getAllEdges().values()) {
					Node dest = (Node) g.getNode(edge.getDest());
					if(dest.getTag() == 0) {
						dest.setTag(1);
						qu.add(dest);
					}
				}
				qu.remove();
			} 
			for (node_data nodes1 : g.getV()) {
				if (nodes1.getTag() == 0) {
					return false;
				}
				else nodes1.setTag(0);
			}
		}
		return true;
	}
	/**
	 * Function that resets all of the tags in the nodes of this graph.
	 */
	private void ZeroTags() {
		for (node_data nodes : g.getV()) {
			nodes.setTag(0);
		}
	}
	/**
	 * Function that receives the key to a source and a destinations and returns the 
	 * number length of the shortest path between them. 
	 * @return the weight of this path.
	 */
	@Override
	public double shortestPathDist(int src, int dest) {
		if(src == dest) {
			return 0;
		}
		ZeroTags();
		MaxWeight();
		String str = "";
		g.getNode(src).setWeight(0);
		dijkstra(src, dest, str);
		if(g.getNode(dest).getWeight() != Double.MAX_VALUE) {
			return g.getNode(dest).getWeight();
		}
		else {
			return -1;
		}
	}
	/**
	 * This is a known algorithm that uses the nodes and weights of the edges to find the shortest path between 
	 * two nodes. It finds the edge with the smallest weight each time and adds it to the sum weight so far 
	 * within each node that it arrives at until it reaches the destination node.
	 * @param src - the key of the source node.
	 * @param dest - the key of the destination node.
	 * @param info - the path of the nodes from src to dest. (1-2-3)
	 */
	private void dijkstra(int src, int dest, String info) {
		if(g.getNode(src).getTag() == 1 && g.getNode(src) == g.getNode(dest)) {
			return;
		}
		for (edge_data edge : g.getE(src)) {
			double oldW = g.getNode(edge.getDest()).getWeight();
			double newW = edge.getWeight() + g.getNode(edge.getSrc()).getWeight();
			if(newW < oldW) {
				g.getNode(edge.getDest()).setWeight(newW);
				g.getNode(edge.getDest()).setInfo(info + "-" + src);
				g.getNode(edge.getSrc()).setTag(1);
				dijkstra(edge.getDest(), dest, info + "-" + src);
			}	
		}
	}
	/**
	 * This function goes through all of the nodes in this graph and enters infinity
	 * to the weight of this node.
	 */
	private void MaxWeight() {
		Collection<node_data> nodes = g.getV();
		Iterator<node_data> iter = nodes.iterator();
		while(iter.hasNext()) {
			iter.next().setWeight(Double.MAX_VALUE);
		}
	}
	/**
	 * Function that receives the key of a source node and destination node and 
	 * returns the shortest path between them as a list of nodes. This list 
	 * is actually the path that was taken.
	 */
	@Override
	public List<node_data> shortestPath(int src, int dest) {
		String str = "";
		int key;
		ZeroTags();
		MaxWeight();
		Node source = (Node)g.getNode(src);
		source.setWeight(0);
		ArrayList<node_data> list = new ArrayList<>();
		dijkstra(src, dest, str);
		if(g.getNode(dest).getWeight() == shortestPathDist(src,dest)) {
			String ans = g.getNode(dest).getInfo();
			ans = ans.substring(1);
			String[] strArray = ans.split("-");
			for (int i = 0; i < strArray.length; i++) {
				key = Integer.parseInt(strArray[i]);
				node_data temp = g.getNode(key);
				list.add(temp);
			}
			list.add(g.getNode(dest));
			return list;
		}
		else {
			return null;
		}
	}
	/**
	 * Function that will receive the targets and return the shortest paths 
	 * between these targets.
	 */
	@Override
	public List<node_data> TSP(List<Integer> targets) {
		if (!isConnected() || targets.size() == 0 || targets == null)
            return null;
        if (targets.size() == 1)
            return shortestPath(targets.get(0), targets.get(0));

        List<node_data> list = new LinkedList<>();
        for (int i = 0; i < targets.size() - 1; i++) {
            list.addAll(shortestPath(targets.get(i), targets.get(i + 1)));
        }
        return list;
	}
	/**
	 * This function copies this graph.
	 */
	 @Override
	 public graph copy() {
		 DGraph CopiedGraph = new DGraph();
		 HashMap<Integer, node_data> temp = new HashMap<Integer, node_data>();
		 for (node_data node : g.getV()) {
			 temp.put(node.getKey(),node);
			 CopiedGraph.setGraph(temp);
		 }
		 return CopiedGraph;
	 }

}
