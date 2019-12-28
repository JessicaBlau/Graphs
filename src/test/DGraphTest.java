package test;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import elements.Edge;
import elements.Node;
import dataStructure.*;

public class DGraphTest {
	
	@Test
	void testGetNode() {
		HashMap<Integer,node_data> graph = new HashMap<Integer, node_data>();
		graph.put(1, new Node());
		graph.put(2, new Node());
		graph.put(3, new Node(5));
		graph.put(7, new Node());
		DGraph test = new DGraph();
		test.setGraph(graph);
		System.out.println(test.getNode(3).getKey());// returns the key of the node to see if it is the correct node.
	}
	
	@Test
	void testGetEdge() {
		HashMap<Integer,node_data> graph = new HashMap<Integer, node_data>();
		Edge temp = new Edge(2,3,0);
		Node tempp = new Node(5);
		tempp.AddThisEdge(temp);
		graph.put(1, new Node());
		graph.put(2, tempp);
		graph.put(3, new Node());
		graph.put(7, new Node());
		DGraph test = new DGraph();
		test.setGraph(graph);
		System.out.println(test.getEdge(2,3).getDest());// returns the edges destination to see if it is the correct edge.
	}
	
	@Test
	void testAddNode() {
		HashMap<Integer, node_data> graph = new HashMap<Integer,node_data>();
		Edge temp = new Edge(2,3,0);
		Node tempp = new Node(5);
		tempp.AddThisEdge(temp);
		graph.put(1, new Node());
		graph.put(2, tempp);
		graph.put(3, new Node());
		graph.put(7, new Node());
		DGraph test = new DGraph();
		test.addNode(new Node(8));
		System.out.println(test.getNode(8).getKey());// returns the node's key that was added to make sure it is in the graph.
	}
}
