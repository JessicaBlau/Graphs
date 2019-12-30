package algorithms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import elements.Node;
import utils.FileUtils;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author Jessica Blau
 *
 */
public class Graph_Algo implements graph_algorithms{
	private DGraph graph;

	@Override
	public void init(graph g) {
		this.graph = (DGraph) g;
	}

	@Override
	public void init(String file_name) {
		try {
			String s = FileUtils.readFile(file_name);
			Gson gson = new Gson();
			graph Graph = gson.fromJson(s, graph.class);
			graph = (DGraph) Graph;
		} catch (IOException e) {
			throw new RuntimeException("Can't read file.");
		}
	}

	@Override
	public void save(String file_name) {
		try {
			FileUtils.writeFile(file_name, toString());
		} catch (IOException e) {
			throw new RuntimeException("File invalid");
		}
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		DGraph CopiedGraph = new DGraph();
		HashMap<Integer, node_data> temp = new HashMap<Integer, node_data>();
		for (node_data node : graph.getV()) {
			temp.put(node.getKey(),node);
			CopiedGraph.setGraph(temp);
		}
		return CopiedGraph;
	}

}


