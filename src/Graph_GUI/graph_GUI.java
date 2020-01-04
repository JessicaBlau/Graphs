package Graph_GUI;

import javax.swing.JFrame;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.graph;

public class graph_GUI extends JFrame {
	graph Graph = new DGraph();
	Graph_Algo GraphAlgo = new Graph_Algo();
	
	public void init(graph g) {
		this.Graph = g;
		GraphAlgo.init(g);
	}
	
	
}
