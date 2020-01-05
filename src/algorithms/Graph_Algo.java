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
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;


import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import elements.Node;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author Jessica Blau
 *
 */
public class Graph_Algo implements graph_algorithms{
	public graph g;
	/**
	 * 
	 */
	public Graph_Algo() {
		g = new DGraph();
	}
	public Graph_Algo(graph _graph) {
		g = _graph;
	}
	/**
	 * 
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
	 * 
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
	 * 
	 * @param src
	 * @param dest
	 * @param info
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
	 * 
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
				node_data tmp=g.getNode(key);
				list.add(tmp);
			}
			list.add(g.getNode(dest));
			return list;
		}
		else {
			return null;
		}
	}
	/**
	 * 
	 */
	@Override
	public List<node_data> TSP(List<Integer> targets) {
		if((!targets.isEmpty()) && (targets.size() <= g.nodeSize()) && (checkGraph(targets))) {
			List<node_data> list = new ArrayList<>();
			if(targets.size() == 1) {
				list.add(g.getNode(targets.get(0)));
				return list;
			}
			if(shortestPath(targets.get(0),targets.get(1)) != null){
					list.addAll((shortestPath(targets.get(0), targets.get(1))));
			}
			else {
				return null;				
			}
			if(targets.size() == 2)
				return list;
			List<node_data> tmp = new ArrayList<>();
			for (int i = 1; i < targets.size()-1; i++) {
				int j = i+1;
				if(shortestPath(targets.get(i), targets.get(j)) != null) {
					tmp.addAll(shortestPath(targets.get(i), targets.get(j)));
				}
				else {
					return null;						
				}
				if((tmp != null) && checkAnswer(targets, tmp) && tmp.containsAll(list)) {
					return tmp;
				}
				else if(tmp != null && checkAnswer(targets, list) && list.containsAll(tmp)) {
					return list;
				}
				else if((tmp != null)){
					tmp.remove(0);
					list.addAll(tmp);
					tmp.clear();
					if(checkAnswer(targets, list))
						return list;
				}
			}
		}
		return null;
	}
	private boolean checkGraph(List<Integer> targets) {
		for(int a = 0; a < targets.size(); a++) {
			for(int b = 0; b < targets.size(); b++) {
				if(a != b && targets.get(a) == targets.get(b))
					return false;
			}
		}
		int counter = 0;
		for(int i : targets) {
			for(node_data node : g.getV()) {
				if(i == node.getKey())
					counter++;
			}
		}
		return (counter == targets.size());
	}

	private boolean checkAnswer(List<Integer> targets,List<node_data> array) {
		int counter = 0;
		for(Integer i : targets) {
			for(node_data node : array) {
				if(i == node.getKey()) {
					counter++;
					break;
				}
			}
		}
		return (counter == targets.size());
	}
	/**
	 * 
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
