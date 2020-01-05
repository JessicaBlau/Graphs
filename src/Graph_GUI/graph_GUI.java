package Graph_GUI;

import javax.swing.JFrame;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.graph;
import utils.StdDraw;

public class Graph_GUI extends JFrame{
	
	public Graph_Algo graphAlgo;
	
	public Graph_GUI() {
		graphAlgo = new Graph_Algo();
	}

	public void drawDGraph() {
		try {
			if(graphAlgo.g.getV() != null) {
				StdDraw.setGui(this);
				PageSize();
				drawEdges();
				drawNodes();
			}
		}catch(Exception e){
			throw new RuntimeException("No Graph to draw");
		}
		
	}

	private void drawNodes() {
		// TODO Auto-generated method stub
		
	}

	private void drawEdges() {
		// TODO Auto-generated method stub
		
	}

	private void PageSize() {
		// TODO Auto-generated method stub
		
	}

	public void init(graph g) {
		graphAlgo.init(g);;
	}
	
	public void deleteGraph() {
		StdDraw.clear();
		graphAlgo.g = new DGraph();
	}

}
