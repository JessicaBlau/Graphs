package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
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
		if(Graph.nodeSize() == 0) return true;
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
	 * 
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
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 
	 */
	@Override
	public List<node_data> shortestPath(int src, int dest) {
		List<node_data> ans = new ArrayList<node_data>(); 
		if(src == dest) {
			ans.add(Graph.getNode(src));
			return ans;
		}
		return null;
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
