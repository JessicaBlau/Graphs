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
import java.util.List;


import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import elements.Edge;
import elements.Node;
import test.Gaph_AlgoTest;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author Jessica Blau
 *
 */
public class Graph_Algo implements graph_algorithms{
	public DGraph Graph;
	/**
	 * 
	 */
	public Graph_Algo() {
		Graph = new DGraph();
	}
	/**
	 * 
	 */
	@Override
	public void init(graph g) {
		Graph = (DGraph) g;
	}
	/**
	 * 
	 */
	@Override
	public void init(String file_name) {
		try
		{    
			FileInputStream file = new FileInputStream(file_name); 
			ObjectInputStream in = new ObjectInputStream(file); 

			Graph = (DGraph)in.readObject(); 

			in.close(); 
			file.close(); 
		} 

		catch(IOException | ClassNotFoundException ex) 
		{ 
			throw new RuntimeException("ClassNotFoundException is caught or IOException is caught");
		} 
	}
	/**
	 * 
	 */
	@Override
	public void save(String file_name) {
		try
		{    
			FileOutputStream file = new FileOutputStream(file_name); 
			ObjectOutputStream out = new ObjectOutputStream(file); 

			out.writeObject(Graph); 

			out.close(); 
			file.close(); 
		} 

		catch(IOException ex) 
		{ 
			System.out.println("IOException is caught"); 
		} 

	}
	
	@Override
	public boolean isConnected() {
		if(Graph.nodeSize() <= 1) return true;
		else {
			ZeroTags();
			DFSUtils(FirstNode(), Graph.nodeSize());
			for(node_data node: Graph.getV()) {
				if(node.getTag()!=1)
					return false;
			}
			ZeroTags();
			Graph.Transpose();
			ZeroTags();
			DFSUtils(FirstNode(), Graph.nodeSize());
			for(node_data node: Graph.getV()) {
				if(node.getTag()!=1)
					return false;
			}
			ZeroTags();
			Graph.Transpose();
			return true;
		}
	}
	/**
	 * Function that returns the key of the first node of this graph.
	 * @return - The key of the first node of this graph or 0 if none.
	 */
	private int FirstNode() {
		for (Iterator<node_data> iterator = Graph.getV().iterator(); iterator.hasNext();) {
			return iterator.next().getKey();
		}
		return 0;

	}
	/**
	 * Function that resets all of the tags in the nodes of this graph.
	 */
	private void ZeroTags() {
		for (Iterator<node_data> iterator = Graph.getV().iterator(); iterator.hasNext();) {
			iterator.next().setTag(0);
		}
	}
	/**
	 * This function receives the key of the first node and the amount of nodes in 
	 * the graph and returns its edges.
	 * @param firstNode - The key of the first node of this graph.
	 * @param nodeSize - The amount of nodes.
	 */
	private void DFSUtils(int firstNode, int nodeSize) {
		if(Graph.getNode(firstNode) == null || nodeSize == 0) {
			return;
		}
		for (edge_data edge : Graph.getE(firstNode)) {
			DFSUtils(edge.getDest(), nodeSize--);
		}
	}
	/**
	 * 
	 */
	@Override
	public double shortestPathDist(int src, int dest) {
		if(src == dest) {
			return 0;
		}
		Node source = (Node) Graph.getNode(src);
		source.setWeight(0);
		dijkstra(src, dest);
		if(Graph.getNode(dest).getWeight() != Double.MAX_VALUE) {
			return Graph.getNode(dest).getWeight();
		}
		else {
			System.out.println("There is no path between these 2 nodes");
			return -1;
		}
	}
	void dijkstra(int src, int dest) {
		ZeroTags();
		MaxWeight();
		Node runner = (Node)Graph.getNode(src);
		String str = "";
		int source = FirstNode();
		int nodesSize = Graph.nodeSize();
		if(src == source) {
			nodesSize--;
		}
		if(runner.getTag() == 1 || nodesSize < 0) {
			return;
		}
		Collection<edge_data> edges = Graph.getE(src);
		Iterator<edge_data> iter = edges.iterator();
		while(iter.hasNext()) {
			Edge run = (Edge) iter.next();
			double newW=runner.getWeight()+run.getWeight();
			double oldW=Graph.getNode(run.getDest()).getWeight();
			if(newW < oldW) {
				Graph.getNode(run.getDest()).setWeight(newW);
				Graph.getNode(run.getDest()).setInfo(str+"-"+src);
				dijkstra(run.getDest(), dest);
				runner.setTag(1);
			}
		}
		
	}
	/**
	 * This function goes through all of the nodes in this graph and enters infinity
	 * to the weight of this node.
	 */
	private void MaxWeight() {
		Collection<node_data> nodes = Graph.getV();
		Iterator<node_data> iter = nodes.iterator();
		while(iter.hasNext()) {
			iter.next().setWeight(Double.MAX_VALUE);
		}
	}
	/**
	 * 
	 */
	@Override
	public List<node_data> shortestPath(int src, int dest) {
		ArrayList<node_data> ans = new ArrayList<>(); 
		if(src == dest) {
			ans.add(Graph.getNode(src));
			return ans;
		}
		Node source = (Node) Graph.getNode(src);
		source.setWeight(0);
		dijkstra(src, dest);
		if(Graph.getNode(dest).getWeight() == shortestPathDist(src,dest)) {
			String strAns = Graph.getNode(dest).getInfo();
			strAns = strAns.substring(1);
			String[] strArray = strAns.split("-");
			for (int i = 0; i < strArray.length; i++) {
				int temp=Integer.parseInt(strArray[i]);
				node_data tmp = Graph.getNode(temp);
				ans.add(tmp);
			}
			ans.add(Graph.getNode(dest));
			return ans;
		}
		else {
			System.out.println("There is no path between these nodes");
			return null;
		}
	}
	/**
	 * 
	 */
	@Override
	public List<node_data> TSP(List<Integer> targets) {
		if (!isConnected()) return null;
		if (targets.size() == 0 || targets == null) return null;
		if (targets.size() == 1)
			return shortestPath(targets.get(0), targets.get(0));

		List<node_data> ans = new ArrayList<>();
		for (int i = 0; i < targets.size() - 1; i++) {
			ans.addAll(shortestPath(targets.get(i), targets.get(i + 1)));
		}
		return ans;
	}
	/**
	 * 
	 */
	 @Override
	 public graph copy() {
		 DGraph CopiedGraph = new DGraph();
		 HashMap<Integer, node_data> temp = new HashMap<Integer, node_data>();
		 for (node_data node : Graph.getV()) {
			 temp.put(node.getKey(),node);
			 CopiedGraph.setGraph(temp);
		 }
		 return CopiedGraph;
	 }

}
